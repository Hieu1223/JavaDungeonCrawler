package game;
import static org.lwjgl.opengl.GL41.*;

import org.lwjgl.opengl.GL41;

import aris_engine.Engine;
import aris_engine.rendering.Camera;
import aris_engine.utils.DefaultShaders;
import aris_engine.utils.Primitives;
public class Game extends Engine {
    Player player = new Player();
    Camera cam = new Camera(new Camera.Params());
    public Game() {
        super(new Params());
    }
    
    @Override
    public void Start() {
        glClearColor(0.2f, 0.7f, 0.4f, 1f);
        player.Start();
    }

    @Override
    public void Update() {
        GL41.glClear(GL_COLOR_BUFFER_BIT);
        player.Update();
        renderer.Render(Primitives.square, player.transform,DefaultShaders.defaultMaterial);
    }

}