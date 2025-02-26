package aris_engine.rendering;

import aris_engine.core.Transform;

import static org.lwjgl.opengl.GL41.*;

public class Material {
    Shader shader;
    Texture[] textures = {};
    public Material(Shader shader, Texture[] textures){
        this.shader = shader;
        this.textures = textures;
        glUniform1i(glGetUniformLocation(shader.programId, "mainTex"), 0);
    }
    public void Bind(Transform transform){
        shader.Bind();
        float[] viewMat = new float[16];
        float[] modelMat = new float[16];
        float[] projectionMat = new float[16];
        transform.transformMat.get(modelMat);
        Camera.main.projectionMat.get(projectionMat);
        Camera.main.viewMat.get(viewMat);
        shader.SetMat4("mView", viewMat);
        shader.SetMat4("mModel", modelMat);
        shader.SetMat4("mProjection", projectionMat);
        for(int i = 0 ; i < textures.length; i++){
            glActiveTexture(GL_TEXTURE0);
            textures[i].Bind();
        }
        //System.out.println(glGetError());
    }
    public void UnBind(){
        shader.Unbind();
    }
}
