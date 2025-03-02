package aris_engine.rendering;

import aris_engine.core.Component;
public class Light extends Component{
    public enum Type{
        POINT,DIRECTION
    }
    public Type type;
    public boolean castShadow = true;
    public float[] color = {0.1f,0.9f,0.5f};
    @Override
    public void Start() {
    }
    @Override
    public void Update() {
    }
}
