package aris_engine.utils;

import aris_engine.rendering.Mesh;

public class Primitives {
    static double[] squareVerts = {
        -1,-1,0,0,0,0,0,1,
        -1,1,0,0,1,0,0,1,
        1,-1,0,1,0,0,0,1,
        1,1,0,1,1,0,0,1
    };
    static int[] squareIndices = {
        0,1,2,
        1,2,3
    };
    public static Mesh square = new Mesh(squareVerts, squareIndices);
}
