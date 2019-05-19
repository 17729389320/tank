package com.zzy.cor;

import com.zzy.GameObject;

public interface Collider {
	boolean collide(GameObject o1, GameObject o2);
}
