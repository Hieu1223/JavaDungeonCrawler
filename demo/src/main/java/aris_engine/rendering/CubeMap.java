package aris_engine.rendering;


import static org.lwjgl.opengl.GL41.*;

import aris_engine.utils.FileUtils.ImageAsset;

public class CubeMap extends Texture {

    public CubeMap(ImageAsset image) {
        this.texID = glGenTextures();
        glBindTexture(GL_TEXTURE_CUBE_MAP, texID);
        int sideLength = image.width < image.height? image.width : image.height;
        for(int i = 0; i< 6; i++){
            glTexImage2D(GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 
                         0, GL_RGB, image.width, image.width, 0, GL_RGB, GL_UNSIGNED_BYTE, image.data
            );
        }
        glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_R, GL_REPEAT);
        glBindTexture(GL_TEXTURE_CUBE_MAP, 0);  
        System.out.println(glGetError());
    }
    public void Bind(){
        glBindTexture(GL_TEXTURE_CUBE_MAP, texID);   
    }
    @Override
    public void Unbind() {
        glBindTexture(GL_TEXTURE_CUBE_MAP, 0);
    }
}
