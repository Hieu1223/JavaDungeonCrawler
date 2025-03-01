package aris_engine.rendering;

import aris_engine.core.Transform;

public class Light {
    public enum Type{
        POINT,DIRECTION
    }
    public Transform transform;
    public boolean castShadow = true;
}
