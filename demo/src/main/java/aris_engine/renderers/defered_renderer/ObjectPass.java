package aris_engine.renderers.defered_renderer;

import aris_engine.rendering.RenderingPipeline.Context;

import static org.lwjgl.opengl.GL41.*;

import aris_engine.rendering.RenderTexture;
import aris_engine.rendering.Renderer;
import aris_engine.rendering.RenderingPipelineStage;
import aris_engine.rendering.Window;

public class ObjectPass implements RenderingPipelineStage {
    RenderTexture gBuffer;
    @Override
    public void SetUp() {
        gBuffer = new RenderTexture(Window.main.height, Window.main.width, new RenderTexture.Params());
    }

    @Override
    public Context Update(Context context) {
        context.renderTextures.add(gBuffer);
        gBuffer.BindFrameBuffer();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glEnable(GL_DEPTH_TEST);
        for (Renderer renderer : context.activeRenderers) {
            renderer.Update();
        }
        gBuffer.UnbindFrameBuffer();
        return context;
    }
    
}
