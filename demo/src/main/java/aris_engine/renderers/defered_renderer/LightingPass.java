package aris_engine.renderers.defered_renderer;

import aris_engine.rendering.RenderingPipeline;
import aris_engine.rendering.RenderingPipelineStage;

public class LightingPass implements RenderingPipelineStage {

    @Override
    public void SetUp() {
    }

    @Override
    public RenderingPipeline.Context Update(RenderingPipeline.Context context) {
        return context;
    }
    
}
