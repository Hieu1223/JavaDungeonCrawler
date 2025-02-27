#version 410
out vec4 FragColor;

in vec2 texCoord;
in vec4 vertColor;
uniform sampler2D mainTex;
void main()
{
    vec4 col = texture(mainTex,texCoord);
    //FragColor = vec4(col.xyz ,1);
    FragColor = vertColor;
}