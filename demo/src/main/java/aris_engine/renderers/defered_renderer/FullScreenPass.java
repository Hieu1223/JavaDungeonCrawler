package aris_engine.renderers.defered_renderer;

import aris_engine.core.Scene;
import aris_engine.rendering.Renderer;
import aris_engine.rendering.Shader;
import aris_engine.utils.MyPath;
import aris_engine.utils.Primitives;

public class FullScreenPass {
    Shader fullscreenShader = new Shader(MyPath.folderPath + "\\assets\\shader\\fullscreen.frag",MyPath.folderPath + "\\assets\\shader\\fullscreen.vert");
    public void Update(Scene.RenderContext renderContext){
        Renderer.DrawSimple(fullscreenShader,Primitives.square);
    }
}
