package aris_engine.rendering;

import aris_engine.core.Transform;

public class Material {
    Shader shader;
    Texture[] textures = {};
    public Material(Shader shader, Texture[] textures){
        this.shader = shader;
        this.textures = textures;
    }
    public void Bind(Transform transform){
        shader.Bind();
        shader.SetMat2("aRot",transform.GetRotationMatrix());
        shader.SetMat2("aScale",transform.GetScaleMatrix());
        shader.SetVec2("entityPos", transform.GetPositionVector());
        for(int i = 0 ; i < textures.length; i++){
            textures[i].Bind();
        }
    }
    public void UnBind(){
        shader.Unbind();
    }
}
