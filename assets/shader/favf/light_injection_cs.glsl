#version 460 core

#define VOXEL_GRID_SIZE_X 160
#define VOXEL_GRID_SIZE_Y 90
#define VOXEL_GRID_SIZE_Z 128
#define BLUE_NOISE_TEXTURE_SIZE 128
#define LOCAL_SIZE_X 8
#define LOCAL_SIZE_Y 8
#define LOCAL_SIZE_Z 1
#define M_PI 3.14159265359
#define EPSILON 0.0001f

layout (local_size_x = 1, local_size_y = 1, local_size_z = 1) in;

layout(binding = 0, rgba32f) uniform writeonly image3D i_VoxelGrid;


layout(std140, binding = 0) uniform Uniforms
{
    mat4  view;
    mat4  projection;
    mat4  view_proj;
    mat4  prev_view_proj;
    mat4  light_view_proj;
    mat4  inv_view_proj;
    vec4  light_direction;
    vec4  light_color;
    vec4  camera_position;
    vec4  bias_near_far_pow;
    vec4  aniso_density_scattering_absorption;
    vec4  time;
    ivec4 width_height;
};

uniform sampler2DShadow s_ShadowMap;
uniform sampler2D s_BlueNoise;
uniform sampler3D s_History;

uniform bool u_Accumulation;

float exp_01_to_linear_01_depth(float z, float n, float f)
{
    float z_buffer_params_y = f / n;
    float z_buffer_params_x = 1.0f - z_buffer_params_y;

    return 1.0f / (z_buffer_params_x * z + z_buffer_params_y);
}

// ------------------------------------------------------------------

float linear_01_to_exp_01_depth(float z, float n, float f)
{
    float z_buffer_params_y = f / n;
    float z_buffer_params_x = 1.0f - z_buffer_params_y;

    return (1.0f / z - z_buffer_params_y) / z_buffer_params_x;
}


vec3 uv_to_ndc(vec3 uv, float n, float f, float depth_power)
{
    vec3 ndc;
        
    ndc.x = 2.0f * uv.x - 1.0f;
    ndc.y = 2.0f * uv.y - 1.0f;
    ndc.z = 2.0f * linear_01_to_exp_01_depth(uv.z, n, f) - 1.0f;
        
    return ndc;
}


vec3 ndc_to_world(vec3 ndc, mat4 inv_vp)
{
    vec4 p = inv_vp * vec4(ndc, 1.0f);
        
    p.x /= p.w;
    p.y /= p.w;
    p.z /= p.w;
        
    return p.xyz;
}


vec3 id_to_uv(ivec3 id, float n, float f)
{
    // Exponential View-Z
    float view_z = n * pow(f / n, (float(id.z) + 0.5f) / float(VOXEL_GRID_SIZE_Z));

    return vec3((float(id.x) + 0.5f) / float(VOXEL_GRID_SIZE_X),
                (float(id.y) + 0.5f) / float(VOXEL_GRID_SIZE_Y),
                view_z / f);
}

vec3 id_to_world(ivec3 id, float n, float f, float depth_power, mat4 inv_vp)
{
    vec3 uv = id_to_uv(id, n, f);
    vec3 ndc = uv_to_ndc(uv, n, f, depth_power);
    return ndc_to_world(ndc, inv_vp);
}

vec3 id_to_uv_with_jitter(ivec3 id, float n, float f, float jitter)
{
    // Exponential View-Z
    float view_z = n * pow(f / n, (float(id.z) + 0.5f + jitter) / float(VOXEL_GRID_SIZE_Z));

    return vec3((float(id.x) + 0.5f) / float(VOXEL_GRID_SIZE_X),
                (float(id.y) + 0.5f) / float(VOXEL_GRID_SIZE_Y),
                view_z / f);
}


vec3 id_to_world_with_jitter(ivec3 id, float jitter, float n, float f, float depth_power, mat4 inv_vp)
{
    vec3 uv = id_to_uv_with_jitter(id, n, f, jitter);
    vec3 ndc = uv_to_ndc(uv, n, f, depth_power);
    return ndc_to_world(ndc, inv_vp);
}


float sample_shadow_map(vec2 coord, float z)
{
    float current_depth = z;
    float bias = bias_near_far_pow.x;

    return texture(s_ShadowMap, vec3(coord, current_depth - bias));
}


float visibility(vec3 p)
{
    // Transform frag position into Light-space.
    vec4 light_space_pos = light_view_proj * vec4(p, 1.0);

    // Perspective divide
    vec3 proj_coords = light_space_pos.xyz / light_space_pos.w;

    // Transform to [0,1] range
    proj_coords = proj_coords * 0.5 + 0.5;

    if (any(greaterThan(proj_coords.xy, vec2(1.0f))) || any(lessThan(proj_coords.xy, vec2(0.0f))))
        return 1.0f;

    return sample_shadow_map(proj_coords.xy, proj_coords.z);
}

// ------------------------------------------------------------------
// Henyey-Greenstein
float phase_function(vec3 Wo, vec3 Wi, float g)
{
    float cos_theta = dot(Wo, Wi);
    float denom     = 1.0f + g * g + 2.0f * g * cos_theta;
    return (1.0f / (4.0f * M_PI)) * (1.0f - g * g) / max(pow(denom, 1.5f), EPSILON);
}

// ------------------------------------------------------------------

float z_slice_thickness(int z)
{
    //return 1.0f; //linear depth
    return exp(-float(VOXEL_GRID_SIZE_Z - z - 1) / float(VOXEL_GRID_SIZE_Z));
}

float sample_blue_noise(ivec3 coord)
{
    ivec2 noise_coord = (coord.xy + ivec2(0, 1) * coord.z * BLUE_NOISE_TEXTURE_SIZE) % BLUE_NOISE_TEXTURE_SIZE;
    return texelFetch(s_BlueNoise, noise_coord, 0).r;
}

void main(){
    ivec3 coord = ivec3(gl_GlobalInvocationID.xyz);

    if (all(lessThan(coord, ivec3(VOXEL_GRID_SIZE_X, VOXEL_GRID_SIZE_Y, VOXEL_GRID_SIZE_Z))))
    {
        // Get jitter for the current pixel, remapped to -0.5 to +0.5 range.
        float jitter = (sample_blue_noise(coord) - 0.5f) * 0.999f;

        // Get the world position of the current voxel.
        vec3 world_pos = id_to_world_with_jitter(coord, jitter, bias_near_far_pow.y, bias_near_far_pow.z, bias_near_far_pow.w, inv_view_proj);
        vec3 Wo = normalize(camera_position.xyz - world_pos);

        // Density and coefficient estimation.
        float thickness  = z_slice_thickness(coord.z);
        float density    = aniso_density_scattering_absorption.y;
        
        // Perform lighting.
        vec3 lighting = light_color.rgb * light_color.a;

        float visibility_value = visibility(world_pos);
        if (visibility_value > EPSILON)
            lighting += visibility_value * light_color.xyz * phase_function(Wo, -light_direction.xyz, aniso_density_scattering_absorption.x);
        vec4 color_and_density = vec4(lighting * density, density);
        
        imageStore(i_VoxelGrid, coord, color_and_density);
    }
}