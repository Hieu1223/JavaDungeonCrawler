package aris_engine.utils;

import aris_engine.rendering.Material;
import aris_engine.rendering.Shader;
import aris_engine.rendering.Texture;
import aris_engine.utils.FileUtils.ImageAsset;


public class DefaultShaders {
        static String folder =  MyPath.folderPath + "\\assets\\shader\\";
        static String deferedFolder = folder + "defered\\";
        public static ImageAsset defaultImage = FileUtils.LoadImage(MyPath.folderPath + "\\assets\\witch.jpeg");
        public static Texture[] empty = {new Texture(defaultImage)};
        public static Shader defaultShader = new Shader(MyPath.folderPath + "\\assets\\shader\\default.frag",MyPath.folderPath + "\\assets\\shader\\default.vert");
        public static Shader defaultDefered = new Shader(deferedFolder + "g_buffer_pass.frag", deferedFolder + "g_buffer_pass.vert");
        public static Material defaultMaterial = new Material(defaultShader,empty );
        public static Material defaultDefererdMaterial = new Material(defaultDefered, empty);
}
