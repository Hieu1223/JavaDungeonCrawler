package aris_engine.core;

public abstract class Component implements Cloneable {
    public Class<?> type;
    public GameObject gameObject;
    public Transform transform;
    public boolean active = true;
    public abstract void Start();
    public abstract void Update();
}
