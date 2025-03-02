package aris_engine.renderers.defered_renderer;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL41;

import aris_engine.core.Scene;
import aris_engine.rendering.Camera;
import aris_engine.rendering.CubeMap;
import aris_engine.rendering.Shader;
import aris_engine.utils.DefaultShaders;
import aris_engine.utils.MyPath;
import aris_engine.utils.Primitives;

public class SkyBoxRenderStage {
    Shader skyBoxShader;
    CubeMap skyBox;
    public SkyBoxRenderStage(){
        String folder =  MyPath.folderPath + "\\assets\\shader\\";
        skyBox = new CubeMap(DefaultShaders.defaultImage);
        skyBoxShader = new Shader(folder + "skybox.frag", folder + "skybox.vert");
        skyBoxShader.Bind();
        skyBoxShader.SetInt("skybox", 0);
        skyBoxShader.Unbind();
    }
    public void Execute(Scene.RenderContext renderContext){
        float[] viewMat = new float[16];
        viewMat[15] = 1;
        for(int i =0 ; i< 3; i++){
            for(int k = 0; k<3 ; k++){
                viewMat[4*i+k] = Camera.main.viewMat.get(i,k);
            }
        }
        glDepthFunc(GL_LEQUAL);
        skyBoxShader.Bind();
        Primitives.cube.Bind();
        skyBox.Bind();
        
        skyBoxShader.SetMat4("mView",viewMat);
        skyBoxShader.SetMat4("mProjection",Camera.main.projectionMatArr);
        
        
        
        GL41.glDrawElements(GL_TRIANGLES,Primitives.cube.indices.length, GL_UNSIGNED_INT,0);
        Primitives.cube.Unbind();
        skyBoxShader.Unbind();
        skyBox.Unbind();
        glDepthFunc(GL_LESS);
    }
}
