#version 410
layout (location = 0) in vec3 aPos; // the position variable has attribute position 0
layout (location = 1) in vec2 aUV;

out vec2 texCoord;
out vec4 vertColor;

void main()
{
    vec4 pos = vec4(aPos.xy,0, 1.0);
    texCoord = aUV;
    gl_Position = pos;
}