#version 410
layout (location = 0) in vec3 aPos; // the position variable has attribute position 0
layout (location = 1) in vec2 aUV;
layout (location = 2) in vec3 aNormal;
layout (location = 3) in float instanceMatrix;
uniform mat4 mView;
uniform mat4 mModel;
uniform mat4 mProjection;
uniform int particleCount;


out vec2 texCoord;
out vec4 vertColor;
void main()
{
    vec4 pos = mProjection *mView * mModel  * vec4(aPos.x+ instanceMatrix *100 , aPos.y, gl_InstanceID, 1.0);
    texCoord = aUV;
    vertColor = vec4(vec3(1,1,1) * instanceMatrix,1);
    gl_Position = pos;
}