package aris_engine.scene;

import aris_engine.core.Transform;

public abstract class Component {
    public GameObject gameObject;
    public Transform transform;
    public abstract void Start();
    public abstract void Update();
}
