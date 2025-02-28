package aris_engine.rendering;
import java.util.LinkedList;
public class RenderingPipeline {
    static RenderingPipeline instance;
    public LinkedList<RenderingPipelineStage> stages;
    public RenderingPipeline getInstance(){
        return instance;
    }
    public RenderingPipeline AddStage(RenderingPipelineStage stage){
        stages.add(stage);
        return this;
    }
    public void Execute(){
        for (RenderingPipelineStage stage : stages) {
            
        }
    }
}
