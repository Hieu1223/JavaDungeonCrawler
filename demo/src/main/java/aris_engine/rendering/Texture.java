package aris_engine.rendering;
import org.lwjgl.stb.*;
import static org.lwjgl.opengl.GL41.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class Texture {
    int texID;
    public Texture(String path){
        texID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texID);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);
        ByteBuffer image = STBImage.stbi_load(path,width, height, channels, 4);
        if(image == null)
            throw new Error("Texture not found");
        glTexImage2D(GL_TEXTURE_2D, GL_TEXTURE_BASE_LEVEL,GL_RGBA, width.get(0), height.get(0), 0,GL_RGBA, GL_UNSIGNED_BYTE,image);
        STBImage.stbi_image_free(image);
    }
    public void Bind(){
        glBindTexture(GL_TEXTURE_2D, texID);
    }
    public void Unbind(){
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
