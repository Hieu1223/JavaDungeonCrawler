#version 410
layout(location = 0) out vec4 albedo;
layout(location = 1) out vec4 pos;
layout(location = 2) out vec4 normal;

uniform sampler2D mainTex;

in vec3 vertPos;
in vec3 vertNormal;
in vec2 texCoord;

void main()
{
    albedo = texture(mainTex,texCoord);
    pos = vec4(vertPos, 1);
    normal =vec4(vertNormal,0);
}