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
            "out vec4 vertexColor; // specify a color output to the fragment shader\n" +
            "void main()\n" +
            "{\n" +
            "    gl_Position = vec4(entityPos + aRot* aScale*  aPos,1.0, 1.0); // see how we directly give a vec3 to vec4's constructor\n" +
            "   vertexColor = vec4(aUV,1,1);// set the output variable to a dark-red color\n" +
            "}";
    public static String defaultFrag = "#version 410\n" +
            "out vec4 FragColor;\n" +
            "  \n" +
            "in vec4 vertexColor; // the input variable from the vertex shader (same name and same type)  \n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    FragColor = vertexColor;\n" +
            "} ";
        public static Texture[] empty = {};
        public static Shader defaultShader = new Shader(defaultFrag,defaultVert);
        public static Material defaultMaterial = new Material(defaultShader,empty );
}
