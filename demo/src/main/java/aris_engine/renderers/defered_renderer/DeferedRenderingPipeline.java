package aris_engine.renderers.defered_renderer;


import static org.lwjgl.opengl.GL41.*;
import aris_engine.core.Scene.RenderContext;
import aris_engine.rendering.RenderBuffer;
import aris_engine.rendering.Renderer;
import aris_engine.rendering.RenderingPipeline;
import aris_engine.rendering.Window;

public class DeferedRenderingPipeline implements RenderingPipeline {
    SkyBoxRenderStage skyBoxRender;
    FullScreenPass fullScreenPass;
    LightPass lightPass;
    RenderBuffer gBuffer;
    @Override
    public void Init() {
        skyBoxRender = new SkyBoxRenderStage();
        fullScreenPass = new FullScreenPass();
        lightPass = new LightPass();
        gBuffer = RenderBuffer.CreateGBuffer();
    }

    @Override
    public void Execute(RenderContext renderContext) {
        //G buffer pass
        gBuffer.BindFrameBuffer();
        glClearColor(0, 0,0,0);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        /* */
        for(Renderer renderer : renderContext.renderers){
            renderer.Update();
        }
        gBuffer.UnbindFrameBuffer();

        //light pass to main screen buffer
        lightPass.Execute(renderContext,gBuffer);

        //Blit depth
        glClear(GL_DEPTH_BUFFER_BIT);
        glBindFramebuffer(GL_READ_FRAMEBUFFER, gBuffer.fboID);
        glBindFramebuffer(GL_DRAW_FRAMEBUFFER, 0);
        int SCR_WIDTH = Window.main.width;
        int SCR_HEIGHT = Window.main.height;
        glBlitFramebuffer(
            0, 0, SCR_WIDTH, SCR_HEIGHT, 0, 0, SCR_WIDTH, SCR_HEIGHT, GL_DEPTH_BUFFER_BIT, GL_NEAREST
        );

        


        skyBoxRender.Execute(renderContext);

        

        //System.err.println(glGetError());
    }
    
}
