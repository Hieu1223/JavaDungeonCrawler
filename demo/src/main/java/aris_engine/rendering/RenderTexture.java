package aris_engine.rendering;
import static org.lwjgl.opengl.GL41.*;

public class RenderTexture {
    public static class Params {
        public int glFormat = GL_RGBA,
                    dataType = GL_UNSIGNED_BYTE,
                    internalDataType = GL_RGBA;
        public boolean withDepthAndStencil = true;
    }

    public int fboID;
    public int textureID;
    public int rboID;
    public RenderTexture(int height, int width, Params params){ 
        if(params.withDepthAndStencil){
            rboID = glGenRenderbuffers();
            glBindRenderbuffer(GL_RENDERBUFFER, rboID); 
            glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH24_STENCIL8, width, height);  
            glBindRenderbuffer(GL_RENDERBUFFER, 0);
            glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_STENCIL_ATTACHMENT, GL_RENDERBUFFER, rboID);
        }
        if(glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE)
	        System.err.println("ERROR::FRAMEBUFFER:: Framebuffer is not complete!");  
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
        glBindTexture(GL_TEXTURE_2D,0);
    }
    public RenderTexture withTexture(int width, int height, Params params){
        glBindFramebuffer(GL_FRAMEBUFFER, fboID);
        textureID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureID);
        float[] empty = null;
        glTexImage2D(GL_TEXTURE_2D, 0,params.glFormat, width, height, 0,params.glFormat, params.dataType,empty);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);  
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, textureID, 0);
        return this;
    }
    public void BindFrameBuffer(){
        glBindFramebuffer(GL_FRAMEBUFFER, fboID);
    }
    public void BindTexture(int slot){
        glActiveTexture(GL_TEXTURE0 + slot);
        glBindTexture(GL_TEXTURE_2D, textureID);
    }
    public void UnBindTexture(){
        glBindTexture(GL_TEXTURE_2D, 0);
    }
    public void UnbindFrameBuffer(){
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }
}
