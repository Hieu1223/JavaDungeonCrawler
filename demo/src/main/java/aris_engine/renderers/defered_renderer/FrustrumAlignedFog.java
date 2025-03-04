package aris_engine.renderers.defered_renderer;

import static org.lwjgl.opengl.GL46.*;

import aris_engine.core.Scene;
import aris_engine.misc.ComputeShader;
import aris_engine.misc.Texture3D;
import aris_engine.rendering.Texture;
import aris_engine.utils.ArisTime;
import aris_engine.utils.MyPath;

public class FrustrumAlignedFog {
    ComputeShader computeShader;
    String folder = MyPath.folderPath + "\\assets\\compute_shader";
    Texture destTexture;
    int voxelGridTexture;
    public FrustrumAlignedFog(){
        computeShader = new ComputeShader(folder + "\\test.comp");
        destTexture = new Texture3D(190, 90, 128);
    }
    public void Execute(Scene.RenderContext renderContext){
        glBindImageTexture(0, destTexture.texID, 0, false, 0, GL_READ_WRITE, GL_RGBA32F);
        computeShader.Bind();
        computeShader.SetFloat("t",(float)ArisTime.GetUptime());
        glDispatchCompute(190,90,128);
        glMemoryBarrier(GL_SHADER_IMAGE_ACCESS_BARRIER_BIT);
    }
}
