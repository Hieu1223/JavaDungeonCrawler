package aris_engine.utils;

import aris_engine.rendering.Material;
import aris_engine.rendering.Shader;
import aris_engine.rendering.Texture;

public class DefaultShaders {
        public static Texture[] empty = { new Texture(MyPath.folderPath + "\\assets\\witch.jpeg")};
        public static Shader defaultShader = new Shader(MyPath.folderPath + "\\assets\\shader\\default.frag",MyPath.folderPath + "\\assets\\shader\\default.vert");
        public static Material defaultMaterial = new Material(defaultShader,empty );
}
