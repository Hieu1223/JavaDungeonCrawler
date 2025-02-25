package game;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;

import org.lwjgl.opengl.GL41;

import aris_engine.Engine;
import aris_engine.utils.DefaultShaders;
import aris_engine.utils.Primitives;
public class Game extends Engine {
    Player player = new Player();
    public Game() {
        super(new Params());
    }
    
    @Override
    public void Start() {
        player.Start();
    }

    @Override
    public void Update() {
        GL41.glClear(GL_COLOR_BUFFER_BIT);
        player.Update();
        renderer.Render(Primitives.square, player.transform,DefaultShaders.defaultMaterial);
    }

}