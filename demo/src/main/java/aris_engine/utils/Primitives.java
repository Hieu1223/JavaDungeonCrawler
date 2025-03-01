package aris_engine.utils;

import aris_engine.rendering.Mesh;

public class Primitives {
    static double[] squareVerts = {
        -1,-1,0,    0,0,    0,0,1,
        -1,1,0,     0,1,    0,0,1,
        1,-1,0,     1,0,    0,0,1,
        1,1,0,      1,1,    0,0,1
    };
    static int[] squareIndices = {
        0,1,2,
        1,2,3
    };
    static double[] cubeVerts = {
        -1, -1,  1, 0,0,   0,0,1, //0
        1, -1,  1,  0,1,   0,0,1, //1
       -1,  1,  1,  1,0,   0,0,1, //2
        1,  1, 1,   1,1,   0,0,1, //3
       -1, -1, -1,  0,0,   0,0,1, //4
        1, -1, -1,  0,1,   0,0,1, //5
       -1,  1, -1,  1,0,   0,0,1, //6
        1,  1, -1,  1,1,   0,0,1//7
    };
    static int[] cubeIndices = {
        2, 6, 7,
        2, 3, 7,

        //Bottom
        0, 4, 5,
        0, 1, 5,

        //Left
        0, 2, 6,
        0, 4, 6,

        //Right
        1, 3, 7,
        1, 5, 7,

        //Front
        0, 2, 3,
        0, 1, 3,

        //Back
        4, 6, 7,
        4, 5, 7
    };
    public static float[] skyBox = {
        // positions          
        -1.0f,  1.0f, -1.0f,
        -1.0f, -1.0f, -1.0f,
         1.0f, -1.0f, -1.0f,
         1.0f, -1.0f, -1.0f,
         1.0f,  1.0f, -1.0f,
        -1.0f,  1.0f, -1.0f,
    
        -1.0f, -1.0f,  1.0f,
        -1.0f, -1.0f, -1.0f,
        -1.0f,  1.0f, -1.0f,
        -1.0f,  1.0f, -1.0f,
        -1.0f,  1.0f,  1.0f,
        -1.0f, -1.0f,  1.0f,
    
         1.0f, -1.0f, -1.0f,
         1.0f, -1.0f,  1.0f,
         1.0f,  1.0f,  1.0f,
         1.0f,  1.0f,  1.0f,
         1.0f,  1.0f, -1.0f,
         1.0f, -1.0f, -1.0f,
    
        -1.0f, -1.0f,  1.0f,
        -1.0f,  1.0f,  1.0f,
         1.0f,  1.0f,  1.0f,
         1.0f,  1.0f,  1.0f,
         1.0f, -1.0f,  1.0f,
        -1.0f, -1.0f,  1.0f,
    
        -1.0f,  1.0f, -1.0f,
         1.0f,  1.0f, -1.0f,
         1.0f,  1.0f,  1.0f,
         1.0f,  1.0f,  1.0f,
        -1.0f,  1.0f,  1.0f,
        -1.0f,  1.0f, -1.0f,
    
        -1.0f, -1.0f, -1.0f,
        -1.0f, -1.0f,  1.0f,
         1.0f, -1.0f, -1.0f,
         1.0f, -1.0f, -1.0f,
        -1.0f, -1.0f,  1.0f,
         1.0f, -1.0f,  1.0f
    };
    public static Mesh square = new Mesh(squareVerts, squareIndices);
    public static Mesh cube = new Mesh(cubeVerts, cubeIndices);
}
