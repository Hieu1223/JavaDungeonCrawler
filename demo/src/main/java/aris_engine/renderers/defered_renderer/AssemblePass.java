package aris_engine.renderers.defered_renderer;

import aris_engine.rendering.RenderingPipeline.Context;
import aris_engine.utils.MyPath;
import aris_engine.utils.Primitives;

import static org.lwjgl.opengl.GL41.*;

import aris_engine.rendering.Mesh;
import aris_engine.rendering.RenderTexture;
import aris_engine.rendering.Renderer;
import aris_engine.rendering.RenderingPipelineStage;
import aris_engine.rendering.Shader;

public class AssemblePass implements RenderingPipelineStage{
    Mesh scrMesh;
    Shader shader;
    @Override
    public void SetUp() {
        scrMesh = Primitives.square;
        String folder =  MyPath.folderPath + "\\assets\\shader\\";
        shader = new Shader(folder + "fullscreen.frag", folder + "fullscreen.vert");
    }

    @Override
    public Context Update(Context context) {
        RenderTexture mainTex = context.renderTextures.get(0);
        mainTex.BindTexture(0);
        glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
        Renderer.DrawSimple(shader, scrMesh);
        mainTex.UnBindTexture();
        return context;
    }

}