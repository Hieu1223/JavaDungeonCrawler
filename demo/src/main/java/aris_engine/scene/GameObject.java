package aris_engine.scene;


import java.util.LinkedList;
import java.util.List;

import aris_engine.core.Transform;
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
    List<Component> components = new LinkedList<>();
}
