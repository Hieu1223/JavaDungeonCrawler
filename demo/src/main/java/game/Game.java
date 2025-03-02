package game;
import static org.lwjgl.opengl.GL41.*;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import aris_engine.Engine;
import aris_engine.core.Scene;
import aris_engine.core.SceneNode;
import aris_engine.core.Transform;
import aris_engine.modules.particle_system.ParticelMaterial;
import aris_engine.modules.particle_system.ParticleRenderer;
import aris_engine.modules.particle_system.ParticleSystem;
import aris_engine.renderers.defered_renderer.*;
import aris_engine.rendering.*;
import aris_engine.utils.*;
import dev.dominion.ecs.api.Dominion;
public class Game extends Engine {
    Scene mainScene;
    RenderingPipeline renderPipeline;
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
                new Vector3f(0,0,0),
                QuatUtils.EulerToQuat((float)Math.toRadians(-90), 0f, 0f),
                new Vector3f(100)
            ), ecsWorld.createEntity("Plane"))
            .WithMesh(Primitives.square)
            .WithRenderer(new Renderer(new Renderer.Params().WithMaterial(DefaultShaders.defaultDefererdMaterial)))
            .EndChild()

            .NewChild(new Transform(
                new Vector3f(0,4,0),
                QuatUtils.EulerToQuat((float)Math.toRadians(-90), 0f, 0f),
                new Vector3f(1)
            ), ecsWorld.createEntity("Light"))
            .WithComponent(new Light())
            .WithMesh(Primitives.square)
            .WithRenderer(new Renderer(new Renderer.Params().WithMaterial(DefaultShaders.defaultDefererdMaterial)))
            .EndChild()

            .NewChild(new Transform(
                new Vector3f(30,7,0),
                QuatUtils.EulerToQuat((float)Math.toRadians(-90), 0f, 0f),
                new Vector3f(1)
            ), ecsWorld.createEntity("Light"))
            .WithComponent(new Light())
            .WithMesh(Primitives.square)
            .WithRenderer(new Renderer(new Renderer.Params().WithMaterial(DefaultShaders.defaultDefererdMaterial)))
            .EndChild()

            .NewChild(new Transform(
                new Vector3f(100,5,20),
                QuatUtils.EulerToQuat((float)Math.toRadians(-90), 0f, 0f),
                new Vector3f(1)
            ), ecsWorld.createEntity("Light"))
            .WithComponent(new Light())
            .WithMesh(Primitives.square)
            .WithRenderer(new Renderer(new Renderer.Params().WithMaterial(DefaultShaders.defaultDefererdMaterial)))
            .EndChild()

            .NewChild(new Transform(
                new Vector3f(1,4,6),
                QuatUtils.EulerToQuat((float)Math.toRadians(-90), 0f, 0f),
                new Vector3f(1)
            ), ecsWorld.createEntity("Light"))
            .WithComponent(new Light())
            .WithMesh(Primitives.square)
            .WithRenderer(new Renderer(new Renderer.Params().WithMaterial(DefaultShaders.defaultDefererdMaterial)))
            .EndChild()


            .NewChild(new Transform(
                new Vector3f(20,5,30),
                QuatUtils.EulerToQuat((float)Math.toRadians(-90), 0f, 0f),
                new Vector3f(1)
            ), ecsWorld.createEntity("Light"))
            .WithComponent(new Light())
            .WithMesh(Primitives.square)
            .WithRenderer(new Renderer(new Renderer.Params().WithMaterial(DefaultShaders.defaultDefererdMaterial)))
            .EndChild()
/*
            .NewChild(new Transform(
                new Vector3f(0,0,0),
                QuatUtils.EulerToQuat(0f, 0f, 0f),
                new Vector3f(1)
            ), ecsWorld.createEntity("ParticleSystem"))
            .WithMesh(Primitives.cube)
            .WithRenderer(new ParticleRenderer(new ParticelMaterial()))
            .WithComponent(new ParticleSystem())
            .EndChild()
 */          

            .NewChild(new Transform(
                new Vector3f(0,3,-10),
                new Quaternionf().rotationXYZ(0, 0, (float)Math.toRadians(60.0)),
                new Vector3f(1)
            ),ecsWorld.createEntity("Player"))
            .WithComponent(new PlayerMovement())
            .WithRenderer(new Renderer(new Renderer.Params().WithMaterial(DefaultShaders.defaultDefererdMaterial)))
            .WithMesh(Primitives.square)

                .NewChild(new Transform(
                    new Vector3f(0,0,-5),
                    new Quaternionf().rotationXYZ(0, 0, (float)Math.toRadians(60.0)),
                    new Vector3f(1)
                ),ecsWorld.createEntity("Player1"))
                .WithRenderer(new Renderer(new Renderer.Params().WithMaterial(DefaultShaders.defaultDefererdMaterial)))
                .WithMesh(Primitives.cube)
                .EndChild()
            
            .EndChild();
        mainScene = Scene.fromTree(builder, ecsWorld);
        renderPipeline =  new DeferedRenderingPipeline();
        mainScene.Start();
        renderPipeline.Init();
    }
    @Override
    public void Update() {
        mainScene.Update();
        renderPipeline.Execute(mainScene.GetRenderContext());
    }

}