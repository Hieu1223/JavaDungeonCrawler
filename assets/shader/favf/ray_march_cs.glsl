#define LOCAL_SIZE_X 8
#define LOCAL_SIZE_Y 8
#define LOCAL_SIZE_Z 1
#define M_PI 3.14159265359
#define EPSILON 0.0001f
#define VOXEL_GRID_SIZE_X 160
#define VOXEL_GRID_SIZE_Y 90
#define VOXEL_GRID_SIZE_Z 128
#define BLUE_NOISE_TEXTURE_SIZE 128

// ------------------------------------------------------------------
// INPUTS -----------------------------------------------------------
// ------------------------------------------------------------------

layout(local_size_x = LOCAL_SIZE_X, local_size_y = LOCAL_SIZE_Y, local_size_z = LOCAL_SIZE_Z) in;

// ------------------------------------------------------------------
// OUTPUT -----------------------------------------------------------
// ------------------------------------------------------------------

layout(binding = 0, rgba32f) uniform writeonly image3D i_VoxelGrid;

// ------------------------------------------------------------------
// UNIFORMS ---------------------------------------------------------
// ------------------------------------------------------------------

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

uniform sampler3D s_VoxelGrid;


float slice_distance(int z)
{
    float n = bias_near_far_pow.y;
    float f = bias_near_far_pow.z;

    return n * pow(f / n, (float(z) + 0.5f) / float(VOXEL_GRID_SIZE_Z));
}

// ------------------------------------------------------------------

float slice_thickness(int z)
{
    return abs(slice_distance(z + 1) - slice_distance(z));
}

// ------------------------------------------------------------------

// https://github.com/Unity-Technologies/VolumetricLighting/blob/master/Assets/VolumetricFog/Shaders/Scatter.compute
vec4 accumulate(int z, vec3 accum_scattering, float accum_transmittance, vec3 slice_scattering, float slice_density)
{
    const float thickness = slice_thickness(z);
    const float slice_transmittance = exp(-slice_density * thickness * 0.01f);

    vec3 slice_scattering_integral = slice_scattering * (1.0 - slice_transmittance) / slice_density;

    accum_scattering += slice_scattering_integral * accum_transmittance;
    accum_transmittance *= slice_transmittance;

    return vec4(accum_scattering, accum_transmittance);
}