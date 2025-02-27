package aris_engine.modules.particle_system;

import aris_engine.rendering.*;
import aris_engine.utils.DefaultShaders;
import aris_engine.utils.MyPath;
public class ParticelMaterial extends Material {
    public ParticelMaterial() {
        super(new Shader(MyPath.folderPath + "\\assets\\shader\\particle.frag",MyPath.folderPath + "\\assets\\shader\\particle.vert"),DefaultShaders.empty);
    }
}
