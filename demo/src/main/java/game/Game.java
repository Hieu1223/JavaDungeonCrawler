package game;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL41.*;

import org.joml.Quaternionf;
import org.joml.Vector3f;
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
            .NewChild(new Transform(
                new Vector3f(0,0,10),
                new Quaternionf().rotationXYZ(0, 0, 0),
                new Vector3f(1)
            ), ecsWorld.createEntity("Camera"))
            .WithComponent(new Camera(new Camera.Params()))
            .WithComponent(new CameraMovement())
            .EndChild()
            .NewChild(new Transform(
                new Vector3f(0,0,-10),
                new Quaternionf().rotationXYZ(0, 0, (float)Math.toRadians(60.0)),
                new Vector3f(1)
            ),ecsWorld.createEntity("Player"))
            .WithComponent(new PlayerMovement())
            .WithRenderer(new Renderer(new Renderer.Params()))
            .WithMesh(Primitives.square)
                .NewChild(new Transform(
                    new Vector3f(0,0,-5),
                    new Quaternionf().rotationXYZ(0, 0, (float)Math.toRadians(60.0)),
                    new Vector3f(1)
                ),ecsWorld.createEntity("Player1"))
                .WithRenderer(new Renderer(new Renderer.Params()))
                .WithMesh(Primitives.square)
                .Up()
            .Up();
        mainScene = Scene.fromTree(builder, ecsWorld);
        mainScene.Start();
    }
    @Override
    public void Update() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        mainScene.Update();
        mainScene.RenderScene();
    }

}