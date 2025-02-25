package aris_engine.utils;

import aris_engine.rendering.Material;
import aris_engine.rendering.Shader;
import aris_engine.rendering.Texture;

public class DefaultShaders {
    public static String defaultVert = "#version 410 \n" +
            "layout (location = 0) in vec2 aPos; // the position variable has attribute position 0\n" +
            "layout (location = 1) in vec2 aUV;\n" +
            "uniform mat2 aRot;\n" +
            "uniform mat2 aScale;\n" +
            "uniform vec2 entityPos;\n" +
            "out vec2 uv;\n"+
            "out vec4 vertexColor; // specify a color output to the fragment shader\n" +
            "void main()\n" +
            "{\n" +
            "    gl_Position = vec4(entityPos + aRot* aScale*  aPos,1.0, 1.0);\n" +
            "    uv = aUV;\n" +
            "}";
    public static String defaultFrag = "#version 410\n" +
            "out vec4 FragColor;\n" +
            "  \n" +
            "in vec4 vertexColor; // the input variable from the vertex shader (same name and same type)  \n" +
            "in vec2 uv;\n" +
            "uniform sampler2D mainTex;\n" +
            "void main()\n" +
            "{\n" +
            "    vec4 col = texture(mainTex,uv);\n" +
            "    FragColor = vec4(col.xyz ,1);\n" +
            "} ";
        public static Texture[] empty = { new Texture(MyPath.folderPath + "\\assets\\witch.jpeg")};
        public static Shader defaultShader = new Shader(defaultFrag,defaultVert);
        public static Material defaultMaterial = new Material(defaultShader,empty );
}
