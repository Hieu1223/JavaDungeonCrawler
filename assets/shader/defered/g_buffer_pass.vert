#version 410
layout (location = 0) in vec3 aPos; // the position variable has attribute position 0
layout (location = 1) in vec2 aUV;
layout (location = 2) in vec3 aNormal;

uniform mat4 mView;
uniform mat4 mModel;
uniform mat4 mProjection;

out vec3 vertPos;
out vec3 vertNormal;
out vec2 texCoord;

void main()
{
    vec4 worlPosition = mModel * vec4(aPos, 1.0);
    vertPos = worlPosition.xyz;
    vertNormal =normalize((mModel * vec4(aNormal,0)).xyz);
    texCoord = aUV;
    gl_Position = mProjection *mView* worlPosition;
}