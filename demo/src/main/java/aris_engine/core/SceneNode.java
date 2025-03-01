package aris_engine.core;


import aris_engine.rendering.Mesh;
import aris_engine.rendering.Renderer;
import dev.dominion.ecs.api.Entity;

public class SceneNode {
    SceneNode parent;
    GameObject go = new GameObject();
    public static SceneNode Root(){
        SceneNode root = new SceneNode();
        root.go.transform = new Transform();
        return root;
    }
    public SceneNode NewChild(Transform localTransform, Entity comp){
        SceneNode newNode = new SceneNode();
        go.transform.children.add(localTransform);
        newNode.parent = this;
        newNode.go.transform = localTransform;
        localTransform.parent = this.go.transform;
        newNode.go.comps = comp;
        localTransform.gameObject = newNode.go;
        return newNode;
    }
    public SceneNode WithMesh(Mesh mesh){
        go.meshFilter = mesh;
        return this;
    }
    public SceneNode WithRenderer(Renderer renderer){
        go.renderer = renderer;
        renderer.gameObject = go;
        renderer.transform = go.transform;
        renderer.type = Renderer.class;
        return this;
    }
    public <T extends Component> SceneNode WithComponent(T comp){
        this.go.comps.add(comp);
        this.go.components.add(comp);
        comp.type = comp.getClass();
        comp.gameObject = this.go;
        comp.transform = go.transform;
        return this;
    }
    public SceneNode Up(){
        return this.parent;
    }
    public SceneNode EndChild(){
        return this.parent;
    }
}
