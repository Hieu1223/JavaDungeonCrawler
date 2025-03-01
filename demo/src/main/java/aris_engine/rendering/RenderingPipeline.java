package aris_engine.rendering;
import java.util.LinkedList;
import java.util.List;
import aris_engine.core.Scene;
import aris_engine.core.Transform;
public class RenderingPipeline {
    public static class Context{
        public List<RenderTexture> renderTextures = new LinkedList<>();
        public List<Light> lights = new LinkedList<>();
        public List<Renderer> activeRenderers = new LinkedList<>();
    }
    Scene scene;
    public LinkedList<RenderingPipelineStage> stages = new LinkedList<>();
    RenderingPipeline(){

    }
    public static RenderingPipeline Builder(Scene scene){
        RenderingPipeline pipeline =  new RenderingPipeline();
        pipeline.scene = scene;
        return pipeline;
    }
    public void Init(){
        for(RenderingPipelineStage stage : stages){
            stage.SetUp();
        }
    }
    public RenderingPipeline AttachToScene(Scene scene){
        this.scene = scene;
        return this;
    }
    public RenderingPipeline AddStage(RenderingPipelineStage stage){
        stages.add(stage);
        return this;
    }
    public void Execute(){
        Context currentContext = new Context();
        currentContext.activeRenderers =  ActiveRendererInScene(scene);
        for (RenderingPipelineStage stage : stages) {
            currentContext = stage.Update(currentContext);
        }
    }
    protected List<Renderer> ActiveRendererInScene(Scene refScene){
        List<Renderer> arr = new LinkedList<>();
        ActiveRendererInSceneHelper(refScene.root, arr);
        return arr;
    }
    void ActiveRendererInSceneHelper(Transform root, List<Renderer> arr){
        for(Transform child: root.children){
            if(!child.gameObject.active)
                continue;
            if(child.gameObject.renderer != null){
                arr.add(child.gameObject.renderer);
            }
            ActiveRendererInSceneHelper(child,arr);
        }
    }
}
