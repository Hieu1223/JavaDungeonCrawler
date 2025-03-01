package aris_engine.rendering;

public interface RenderingPipelineStage {
    public void SetUp();
    public RenderingPipeline.Context Update(RenderingPipeline.Context context); 

}
