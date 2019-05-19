package com.zzy.cor;

import com.zzy.Bullet;
import com.zzy.Explode;
import com.zzy.GameObject;
import com.zzy.Tank;

public class BulletTankCollider implements Collider {

 	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Bullet && o2 instanceof Tank) {
			Bullet b = (Bullet)o1;
			Tank t = (Tank)o2;
			//TODO copy code from method collideWith
			if(b.collideWith(t)) {
				return false;
			}

 		} else if (o1 instanceof Tank && o2 instanceof Bullet) {
			return collide(o2, o1);
		} 

 		return true;

 	}

 }