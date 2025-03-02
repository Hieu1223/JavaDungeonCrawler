package aris_engine.renderers.defered_renderer;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGetError;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.GL_TEXTURE1;
import static org.lwjgl.opengl.GL13.GL_TEXTURE2;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL41.*;

import aris_engine.core.Scene;
import aris_engine.rendering.Camera;
import aris_engine.rendering.Light;
import aris_engine.rendering.RenderBuffer;
import aris_engine.rendering.Renderer;
import aris_engine.rendering.Shader;
import aris_engine.utils.MyPath;
import aris_engine.utils.Primitives;

public class LightPass {
    Shader shader =  new Shader(MyPath.folderPath + "\\assets\\shader\\defered\\lighting_pass.frag",MyPath.folderPath + "\\assets\\shader\\fullscreen.vert");
    public LightPass(){
        shader.Bind();
        shader.SetInt("gPosition", 0);
        shader.SetInt("gNormal", 1);
        shader.SetInt("gAlbedoSpec", 2);
        shader.Unbind();
    }
    
    public void Execute(Scene.RenderContext renderContext, RenderBuffer gBuffer){
        shader.Bind();
                
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D,gBuffer.textureIDs.get(0));
        glActiveTexture(GL_TEXTURE1);
        glBindTexture(GL_TEXTURE_2D,gBuffer.textureIDs.get(1));
        glActiveTexture(GL_TEXTURE2);
        glBindTexture(GL_TEXTURE_2D,gBuffer.textureIDs.get(2));

        float[] viewPos = {Camera.main.transform.localPos.x,Camera.main.transform.localPos.y,Camera.main.transform.localPos.z};

        for(int i = 0; i< renderContext.lights.size(); i++){
            Light light = renderContext.lights.get(i);
            float[] pos = {light.transform.localPos.x,light.transform.localPos.y,light.transform.localPos.z};
            shader.SetVec3("lights[" + i + "].Position", pos);
            shader.SetVec3("lights[" + i + "].Color", light.color);
        }
        shader.SetVec3("viewPos", viewPos);
        Renderer.DrawSimple(shader, Primitives.square);
        gBuffer.UnBindTexture();
        shader.Unbind();
    }
}
