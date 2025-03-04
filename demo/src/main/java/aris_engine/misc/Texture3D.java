package aris_engine.misc;

import static org.lwjgl.opengl.GL46.*;
import aris_engine.rendering.Texture;

public class Texture3D extends Texture {
    public Texture3D(int x,int y,int z){
        this.texID = glGenTextures();
        glBindTexture(GL_TEXTURE_3D, this.texID);
        float[] empty = null;
        glTexImage3D(GL_TEXTURE_3D, 0,GL_RGBA32F,x,y,z,0,GL_RGBA, GL_FLOAT,empty);
        glTexParameteri(GL_TEXTURE_3D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_3D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_3D, GL_TEXTURE_MAG_FILTER,GL_LINEAR);
        glTexParameteri(GL_TEXTURE_3D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glBindTexture(GL_TEXTURE_3D, 0);
    }
    @Override
    public void Bind(int slot) {
        glActiveTexture(GL_TEXTURE0+ slot);
        glBindTexture(GL_TEXTURE_3D, texID);
    }
}
