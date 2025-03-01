#version 410
out vec4 FragColor;

in vec2 texCoord;

uniform sampler2D mainTex;
void main()
{
    vec4 col = texture(mainTex,texCoord);
    FragColor = vec4(col.xyz ,1);
    //FragColor = vec4(1,1,1 ,1);
}