package aris_engine.modules.particle_system;

import java.util.ArrayList;
import java.util.Random;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import aris_engine.core.Component;
import aris_engine.core.Transform;

public class ParticleSystem extends Component {
    ParticleRenderer partRenderer;
    @Override
    public void Start() {
        partRenderer = (ParticleRenderer)gameObject.getRenderer();
        ArrayList<float[]> mats = new ArrayList<float[]>();
        Random randomGen =  new Random();
        float[] data = new float[16];
        for(int i = 0 ; i< 10; i++){
            Transform temp = new Transform();
            temp.localScale = new Vector3f(0.2f + randomGen.nextFloat()/8);
            temp.localPos = new Vector3f(randomGen.nextFloat() * 8, randomGen.nextFloat() * 8,randomGen.nextFloat() * 8);
            temp.localRot = new Quaternionf().rotateXYZ(randomGen.nextFloat() * 8, randomGen.nextFloat() * 8,randomGen.nextFloat() * 8);
            temp.Update();
            temp.transformMat.get(data);
            mats.add(data);
        }

        partRenderer.particleCount = mats.size();
        partRenderer.particleTransformMat = mats;
    }

    @Override
    public void Update() {

    }
    
}
