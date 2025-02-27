#version 410
layout (location = 0) in vec3 aPos; // the position variable has attribute position 0
layout (location = 1) in vec2 aUV;
layout (location = 3) in mat4 instanceMatrix;
uniform mat4 mView;
uniform mat4 mModel;
uniform mat4 mProjection;
uniform int particleCount;


out vec2 texCoord;
out vec4 vertColor;
void main()
{
    vec4 pos = mProjection *mView* mModel * instanceMatrix * vec4(aPos, 1.0);
    texCoord = aUV;
    gl_Position = pos;
}