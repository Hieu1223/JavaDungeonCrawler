package aris_engine.misc;

import static org.lwjgl.opengl.GL46.*;

import aris_engine.rendering.Shader;
import aris_engine.utils.FileUtils;

public class ComputeShader extends Shader {

    public ComputeShader(String path) {
        String source = FileUtils.readFile(path);
        int computeShaderID = Shader.createShader(source,GL_COMPUTE_SHADER);
        
        this.programId = glCreateProgram();
        glAttachShader(programId,computeShaderID);
        glLinkProgram(programId);
    }
}
