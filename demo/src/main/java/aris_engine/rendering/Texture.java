package aris_engine.rendering;
import aris_engine.utils.FileUtils.ImageAsset;

import static org.lwjgl.opengl.GL41.*;


public class Texture {
    public int texID;

    public Texture(){

    };

    public Texture(ImageAsset image){
        texID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D,texID);
        glTexImage2D(GL_TEXTURE_2D, 0,GL_RGB, image.width, image.height, 0,GL_RGB, GL_UNSIGNED_BYTE,image.data);
        glGenerateMipmap(GL_TEXTURE_2D);
    }

    public static Texture empty(int width, int height, int format,int internalFormat,int dataType){
        Texture newTex = new Texture();
        newTex.texID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D,newTex.texID);
        float[] empty = null;
        glTexImage2D(GL_TEXTURE_2D, 0,format, width, height, 0,internalFormat, dataType,empty);
        glBindTexture(GL_TEXTURE_2D,0);
        return newTex;
    }

    public void Bind(int slot){
        glActiveTexture(GL_TEXTURE0 + slot);
        glBindTexture(GL_TEXTURE_2D, texID);
    }
    public void Unbind(){
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
