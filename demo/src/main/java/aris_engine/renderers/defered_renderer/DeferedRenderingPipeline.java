package aris_engine.renderers.defered_renderer;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import aris_engine.core.Scene.RenderContext;
import aris_engine.rendering.RenderingPipeline;

public class DeferedRenderingPipeline implements RenderingPipeline {
    SkyBoxRenderStage skyBoxRender;
    FullScreenPass fullScreenPass;
    @Override
    public void Init() {
        skyBoxRender = new SkyBoxRenderStage();
        fullScreenPass = new FullScreenPass();
    }

    @Override
    public void Execute(RenderContext renderContext) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        skyBoxRender.Execute(renderContext);
        //fullScreenPass.Update(renderContext);
    }
    
}
