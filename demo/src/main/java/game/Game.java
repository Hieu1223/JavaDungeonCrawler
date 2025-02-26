package game;
import static org.lwjgl.opengl.GL41.*;

import org.lwjgl.opengl.GL41;

import aris_engine.Engine;
import aris_engine.core.Transform;
import aris_engine.rendering.Camera;
import aris_engine.rendering.Renderer;
import aris_engine.scene.Scene;
import aris_engine.scene.SceneNode;
import aris_engine.utils.Primitives;
import dev.dominion.ecs.api.Dominion;
public class Game extends Engine {
    Camera cam = new Camera(new Camera.Params());
    Scene mainScene;
    public Game() {
        super(new Params());
    }
    
    @Override
    public void Start() {
        glClearColor(0.2f, 0.7f, 0.4f, 1f);
        Dominion ecsWorld = Dominion.create("World");
        SceneNode builder = SceneNode
        .Root()
            .NewChild(new Transform(),ecsWorld.createEntity("Player"))
            .WithComponent(new PlayerMovement())
            .WithRenderer(new Renderer(new Renderer.Params()))
            .WithMesh(Primitives.square)
            .EndChild();
        mainScene = Scene.fromTree(builder, ecsWorld);
        mainScene.Start();
    }

    @Override
    public void Update() {
        GL41.glClear(GL_COLOR_BUFFER_BIT);
        mainScene.Update();
        mainScene.RenderScene();
    }

}