package aris_engine.rendering;

import aris_engine.core.Transform;

import static org.lwjgl.opengl.GL41.*;

public class Material {
    public Shader shader;
    public Texture[] textures = {};
    public Material(Shader shader, Texture[] textures){
        this.shader = shader;
        this.textures = textures;
        //glUniform1i(glGetUniformLocation(shader.programId, "mainTex"), 0);
    }
    public void Bind(Transform transform){
        shader.Bind();
        SetUniform(transform);
        BindTexture();
    }
    public void SetUniform(Transform transform){
        shader.SetMat4("mView", Camera.main.viewMatArr);
        shader.SetMat4("mModel", transform.transforMatArr);
        shader.SetMat4("mProjection", Camera.main.projectionMatArr);
    }
    public void BindTexture(){
        for(int i = 0 ; i < textures.length; i++){
            textures[i].Bind(i);
        }
    }
    public void UnBind(){
        shader.Unbind();
    }
}
