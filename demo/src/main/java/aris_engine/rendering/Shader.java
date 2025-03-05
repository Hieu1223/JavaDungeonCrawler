package aris_engine.rendering;


import static org.lwjgl.opengl.GL46.*;

import aris_engine.utils.FileUtils;

public class Shader {
    protected int programId;

    protected Shader(){}

    public Shader(String fragPath, String vertPath) {
        //System.out.println("Begin Shader " +  glGetError());
        String vertSource = FileUtils.readFile(vertPath);
        String fragSource = FileUtils.readFile(fragPath);
        int vertId = createShader(vertSource,GL_VERTEX_SHADER);
        int fragId = createShader(fragSource,GL_FRAGMENT_SHADER);
        programId = glCreateProgram();
        glAttachShader(programId, vertId);
        glAttachShader(programId, fragId);
        glLinkProgram(programId);
        int[] success = new int[1];
        glGetProgramiv(programId, GL_LINK_STATUS, success);
        if(success[0] != GL_TRUE){
            System.out.println("Linking Error: " + glGetProgramInfoLog(programId));
        }
        glDeleteShader(vertId);
        glDeleteShader(fragId);
        
        //System.out.println("End shader " +  glGetError());

    }
    public static int createShader(String source, int type) {
        int id = glCreateShader(type);
        glShaderSource(id,source);
        glCompileShader(id);
        int[] success = {0};
        glGetShaderiv(id, GL_COMPILE_STATUS, success);
        if (success[0] == 0) {
            String err =  glGetProgramInfoLog(id);
            System.out.println("Shader Compile Error: " + err);
        }

        return id;
    }
    public void Bind(){
        glUseProgram(programId);
        
    }
    public void Unbind(){
        glUseProgram(0);
    }
    public void SetInt(String name, int value){
        glUniform1i(glGetUniformLocation(programId, name), value);
    }
    public void SetFloat(String name, float value){
        glUniform1f(glGetUniformLocation(programId, name), value);
    }
    public void SetBoolean(String name, boolean value){
        glUniform1i(glGetUniformLocation(programId, name), value ? 1 : 0);
    }
    public void SetMat2(String name, float[] value){
        glUniformMatrix2fv(glGetUniformLocation(programId, name), false, value);
    }
    public void SetVec2(String name, float[] value){
        glUniform2fv(glGetUniformLocation(programId, name), value);
    }
    public void SetMat3(String name, float[] value){
        glUniformMatrix3fv(glGetUniformLocation(programId, name), false, value);
    }
    public void SetVec3(String name, float[] value){
        glUniform3fv(glGetUniformLocation(programId, name), value);
    }
    public void SetMat4(String name, float[] value){
        glUniformMatrix4fv(glGetUniformLocation(programId, name), false, value);
    }
    public void SetVec4(String name, float[] value){
        glUniform4fv(glGetUniformLocation(programId, name), value);
    }
    
}
