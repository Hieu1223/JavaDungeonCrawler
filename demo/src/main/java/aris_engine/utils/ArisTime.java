package aris_engine.utils;
import java.time.Duration;
import java.time.Instant;

public class ArisTime {
    static ArisTime instance;
    Instant start;
    Instant begin;
    double _deltaTime = 0.001f;
    double _timeScale = 1;
    public static void Init(){
        instance = new ArisTime();
        instance.begin = Instant.now();
    }
    public static double deltaTime(){
        return instance._deltaTime * instance._timeScale;
    }
    public static double unscaledDeltaTime(){
        return instance._deltaTime;
    }
    public static double timeScale(){
        return instance._timeScale;
    }
    public static void SetTimeScale(double timeScale){
        instance._timeScale = timeScale;
    }
    public static void ProbeStart(){
        instance.start = Instant.now();
    }
    public static void ProbeEnd(){
        instance._deltaTime = Duration.between(instance.start, Instant.now()).toMillis()/1000.0;
    }
    public static double GetUptime(){
        return Duration.between(instance.begin, Instant.now()).toMillis()/1000.0;
    }
}

