package aris_engine.modules.particle_system;
import java.util.Random;

import org.joml.*;

import aris_engine.core.Component;
import aris_engine.core.Transform;

public class ParticleSystem extends Component {
    ParticleRenderer partRenderer;
    public int particleCount = 10000;
    public Vector3f volume = new Vector3f(400);
    @Override
    public void Start() {
        partRenderer = (ParticleRenderer)gameObject.getRenderer();
        
        float[] matricesData = new float[16*particleCount];
        int currentCount = 0;
        Random randomGen =  new Random();
        float[] data = new float[16];
        for(int i = 0 ; i< particleCount; i++){
            Vector3f localScale = new Vector3f(0.5f + randomGen.nextFloat());
            Vector3f localPos = new Vector3f(randomGen.nextFloat() * volume.x - volume.x/2, randomGen.nextFloat() * volume.y-volume.y/2,randomGen.nextFloat() * volume.z-volume.z/2);
            Quaternionf localRot = new Quaternionf().rotateXYZ(randomGen.nextFloat() * 3.14f, randomGen.nextFloat() * 3.14f,randomGen.nextFloat() * 3.14f);
            Matrix4f transformMat = new Matrix4f().identity();
            transformMat = transformMat.translate(localPos);
            transformMat =  transformMat.rotate(localRot);
            transformMat = transformMat.scale(localScale);
            transformMat.get(data);
            for(float item : data){
                matricesData[currentCount] = item;
                currentCount++;
            }
        }
        partRenderer.SetUpParticleData(matricesData);
    }

    @Override
    public void Update() {

    }
    
}
