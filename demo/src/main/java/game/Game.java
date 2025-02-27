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
import aris_engine.core.Scene;
import aris_engine.core.SceneNode;
import aris_engine.core.Transform;
import aris_engine.modules.particle_system.ParticelMaterial;
import aris_engine.modules.particle_system.ParticleRenderer;
import aris_engine.modules.particle_system.ParticleSystem;
import aris_engine.renderers.renderer0.Renderer0;
import aris_engine.rendering.Camera;
import aris_engine.rendering.Renderer;
import aris_engine.utils.Primitives;
import aris_engine.utils.QuatUtils;
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
                new Vector3f(27,46,49),
                QuatUtils.EulerToQuat(-0.57f, 0.461f, 0f),
                new Vector3f(1)
            ), ecsWorld.createEntity("Camera"))
            .WithComponent(new Camera(new Camera.Params()))
            .WithComponent(new CameraMovement())
            .EndChild()

            .NewChild(new Transform(
                new Vector3f(0,-5,0),
                QuatUtils.EulerToQuat((float)Math.toRadians(90), 0f, 0f),
                new Vector3f(100)
            ), ecsWorld.createEntity("Plane"))
            .WithMesh(Primitives.square)
            .WithRenderer(new Renderer(new Renderer.Params()))
            .EndChild()
 
            .NewChild(new Transform(
                new Vector3f(0,3,0),
                new Quaternionf().rotationXYZ((float)Math.toRadians(90), 0,0),
                new Vector3f(1)
            ), ecsWorld.createEntity("ParticleSystem"))
            .WithMesh(Primitives.square)
            .WithRenderer(new ParticleRenderer(new ParticelMaterial()))
            .WithComponent(new ParticleSystem())
            .EndChild()
            

            .NewChild(new Transform(
                new Vector3f(0,3,-10),
                new Quaternionf().rotationXYZ(0, 0, (float)Math.toRadians(60.0)),
                new Vector3f(1)
            ),ecsWorld.createEntity("Player"))
            .WithComponent(new PlayerMovement())
            .WithRenderer(new Renderer0())
            .WithMesh(Primitives.square)

                .NewChild(new Transform(
                    new Vector3f(0,0,-5),
                    new Quaternionf().rotationXYZ(0, 0, (float)Math.toRadians(60.0)),
                    new Vector3f(1)
                ),ecsWorld.createEntity("Player1"))
                .WithRenderer(new Renderer(new Renderer.Params()))
                .WithMesh(Primitives.square)
                .EndChild()
            
            .EndChild();
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