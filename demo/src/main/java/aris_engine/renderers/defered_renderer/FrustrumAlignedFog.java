package aris_engine.renderers.defered_renderer;

import static org.lwjgl.opengl.GL46.*;

import aris_engine.core.Scene;
import aris_engine.misc.ComputeShader;
import aris_engine.misc.Texture3D;
import aris_engine.rendering.Light;
import aris_engine.rendering.Texture;
import aris_engine.utils.MyPath;

public class FrustrumAlignedFog {
    ComputeShader lightInjection;
    ComputeShader rayMarch;
    String folder = MyPath.folderPath + "\\assets\\shader\\favf";
    Texture destTexture;
    public FrustrumAlignedFog(){
        lightInjection = new ComputeShader(folder + "\\light_injection_cs.glsl");
        rayMarch = new ComputeShader(folder+ "\\ray_march_cs.glsl");
        destTexture = new Texture3D(190, 90, 128);
    }
    public void Execute(Scene.RenderContext renderContext){
        glBindImageTexture(0,destTexture.texID,0,false,0, GL_WRITE_ONLY,GL_RGBA32F);
        lightInjection.Bind();
        glDispatchCompute(160,90,128);
        
    }
}
