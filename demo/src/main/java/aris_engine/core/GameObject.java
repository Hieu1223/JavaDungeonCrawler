package aris_engine.core;


import java.util.LinkedList;
import java.util.List;

import aris_engine.rendering.Mesh;
import aris_engine.rendering.Renderer;
import dev.dominion.ecs.api.Entity;
public class GameObject {
    public Scene scene;
    public Entity comps;
    public Transform transform;
    public Renderer renderer;
    public Mesh meshFilter;
    public boolean active = true;
    List<Component> components = new LinkedList<Component>();
    public <T extends Component> T GetComponent(Class<T> compType){
        for(Component component : components){
            if(component.type == compType)
                return (T)component;
        }
        return null;
    }
    public Renderer getRenderer(){
        return this.renderer;
    }
    public Mesh getMesh(){
        return this.meshFilter;
    }
    public Scene getScene(){
        return this.scene;
    }
}
