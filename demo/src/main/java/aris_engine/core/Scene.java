package aris_engine.core;
import java.util.LinkedList;
import java.util.List;

import aris_engine.rendering.Light;
import aris_engine.rendering.Renderer;
import dev.dominion.ecs.api.Dominion;

public class Scene {
    public static class RenderContext {
        public List<Light> lights;
        public List<Renderer> renderers;
    }
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
    void RenderSceneHelper(Transform node){
        for(Transform child: node.children){
            if(child.gameObject.renderer != null)
                child.gameObject.renderer.Update();
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
    public RenderContext GetRenderContext(){
        RenderContext renderContext = new RenderContext();
        renderContext.lights = new LinkedList<>();
        renderContext.renderers = new LinkedList<>();
        GetRenderContextHelper(renderContext, root);
        return renderContext;
    }
    void GetRenderContextHelper(RenderContext data, Transform node){
        for(Transform child: node.children){
            if(!child.gameObject.active)
                continue;
            child.Update();
            for(Component comp: child.gameObject.components)
            {
                if(Light.class.isAssignableFrom(comp.type)){
                    data.lights.add((Light)comp);
                }     
            }
            if(child.gameObject.renderer != null){
                data.renderers.add(child.gameObject.renderer);
            }
            GetRenderContextHelper(data, child);
        }
    }
}
