package aris_engine.rendering;

import aris_engine.core.Scene;

public interface RenderingPipeline {
    public void Init();
    public void Execute(Scene.RenderContext renderContext);
}
