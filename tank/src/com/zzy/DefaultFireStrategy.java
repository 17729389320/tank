package com.zzy;

import com.zzy.decorator.RectDecorator;
import com.zzy.decorator.TailDecorator;

public class DefaultFireStrategy implements FireStrategy{

	@Override
	public void fire(Tank t) {
		int bX = t.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int bY = t.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
//		new Bullet(bX, bY, t.dir, t.group);
 		
		//Bug? new Bullet把自己加了一遍
		GameModel.getInstance().add(
				new RectDecorator(
						new TailDecorator(
						new Bullet(bX, bY, t.dir, t.group))));



	}
}
