package aris_engine.rendering;


import org.lwjgl.opengl.GL41;

import aris_engine.utils.FileUtils;

public class Shader {
    int programId;

    

    public Shader(String fragPath, String vertPath) {
        String vertSource = FileUtils.readFile(vertPath);
        String fragSource = FileUtils.readFile(fragPath);
        int vertId = createShader(vertSource,GL41.GL_VERTEX_SHADER);
        int fragId = createShader(fragSource,GL41.GL_FRAGMENT_SHADER);
        programId = GL41.glCreateProgram();
        GL41.glAttachShader(programId, vertId);
        GL41.glAttachShader(programId, fragId);
        GL41.glLinkProgram(programId);
        int[] success = new int[1];
        GL41.glGetProgramiv(programId, GL41.GL_LINK_STATUS, success);
        if(success[0] != GL41.GL_TRUE){
            System.out.println("Linking Error: " + GL41.glGetProgramInfoLog(programId));
        }
        GL41.glDeleteShader(vertId);
        GL41.glDeleteShader(fragId);

    }
    int createShader(String source, int type) {
        int id = GL41.glCreateShader(type);
        GL41.glShaderSource(id,source);
        GL41.glCompileShader(id);
        int[] success = {0};
        GL41.glGetShaderiv(id, GL41.GL_COMPILE_STATUS, success);
        if (success[0] == 0) {
            System.out.println("Shader Compile Error: " + GL41.glGetProgramInfoLog(id));
        }

        return id;
    }
    public void Bind(){
        GL41.glUseProgram(programId);
    }
    public void Unbind(){
        GL41.glUseProgram(0);
    }
    public void SetInt(String name, int value){
        GL41.glUniform1i(GL41.glGetUniformLocation(programId, name), value);
    }
    public void SetFloat(String name, float value){
        GL41.glUniform1f(GL41.glGetUniformLocation(programId, name), value);
    }
    public void SetBoolean(String name, boolean value){
        GL41.glUniform1i(GL41.glGetUniformLocation(programId, name), value ? 1 : 0);
    }
    public void SetMat2(String name, float[] value){
        GL41.glUniformMatrix2fv(GL41.glGetUniformLocation(programId, name), false, value);
    }
    public void SetVec2(String name, float[] value){
        GL41.glUniform2fv(GL41.glGetUniformLocation(programId, name), value);
    }
    public void SetMat3(String name, float[] value){
        GL41.glUniformMatrix3fv(GL41.glGetUniformLocation(programId, name), false, value);
    }
    public void SetVec3(String name, float[] value){
        GL41.glUniform3fv(GL41.glGetUniformLocation(programId, name), value);
    }
    public void SetMat4(String name, float[] value){
        GL41.glUniformMatrix4fv(GL41.glGetUniformLocation(programId, name), false, value);
    }
    public void SetVec4(String name, float[] value){
        GL41.glUniform4fv(GL41.glGetUniformLocation(programId, name), value);
    }
    
}
