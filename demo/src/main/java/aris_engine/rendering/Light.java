package aris_engine.rendering;

import aris_engine.core.Component;
public class Light extends Component{
    public enum Type{
        POINT,DIRECTION
    }
    public boolean castShadow = true;
    @Override
    public void Start() {
    }
    @Override
    public void Update() {
    }
}
