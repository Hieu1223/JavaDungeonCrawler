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
        shader.SetMat2("aRot",transform.GetRotationMatrix());
        shader.SetMat2("aScale",transform.GetScaleMatrix());
        shader.SetVec2("entityPos", transform.GetPositionVector());
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
