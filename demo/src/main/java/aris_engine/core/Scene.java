package aris_engine.core;

import aris_engine.rendering.Material;
import aris_engine.utils.DefaultShaders;
import dev.dominion.ecs.api.Dominion;

public class Scene {
    public Transform root = new Transform();
    Dominion world;
    Scene(){
        world = Dominion.create();
    }
    public static Scene fromTree(SceneNode node, Dominion world){
        Scene scene = new Scene();
        scene.world = world;
        SceneNode root = node;
        while (root.parent != null) {
            root = root.parent;
        }
        scene.root = root.go.transform;
        return scene;
    }
    public void Start(){
        StartHelper(this.root);
    }
    public void Update(){
        UpdateHelper(root);
    }
    public void RenderScene(){
        RenderSceneHelper(root);
    }

    void RenderSceneHelper(Transform node){
        for(Transform child: node.children){
            if(child.gameObject.renderer != null)
                child.gameObject.renderer.Render(child.gameObject.meshFilter, child);
            RenderSceneHelper(child);
        }
    }
    void StartHelper(Transform node){
        for(Transform child: node.children){
            for(Component comp: child.gameObject.components)
                comp.Start();
            StartHelper(child);
        }
    }
    void UpdateHelper(Transform node){
        for(Transform child: node.children){
            if(!child.gameObject.active)
                continue;
            child.Update();
            for(Component comp: child.gameObject.components)
                if(comp.active)
                    comp.Update();
            UpdateHelper(child);
        }
    }
}
