#version 410
layout (location = 0) in vec3 aPos; // the position variable has attribute position 0
uniform mat4 mView;
uniform mat4 mProjection;
out vec3 texCoord;
void main()
{
    gl_Position = mProjection * mView * vec4(aPos, 1);
    gl_Position = gl_Position.xyww;
    texCoord = aPos;
}