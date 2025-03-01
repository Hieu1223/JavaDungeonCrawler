package aris_engine.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.*;
import java.util.Scanner;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;

public class FileUtils {
    public static class ImageAsset{
        public int height,width, channelCount;
        public ByteBuffer data;
        public void Free(){
            STBImage.stbi_image_free(data);
        }
    }
    public static String readFile(String path) {
        String res = "";
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                res += myReader.nextLine();
                res+= "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred when reading " + path);
            e.printStackTrace();
        }
        return res;
    }
    public static ImageAsset LoadImage(String path){
        ImageAsset newImage = new ImageAsset();
        STBImage.stbi_set_flip_vertically_on_load(true);  
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);
        ByteBuffer image = STBImage.stbi_load(path,width, height, channels, 3);
        if(image == null)
            throw new Error("Texture at " + path +  " not found");
        newImage.data = image;
        newImage.height = height.get(0);
        newImage.width = width.get(0);
        newImage.channelCount = channels.get(0);
        return newImage;
    }    
}
