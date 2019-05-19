package com.zzy;

import java.util.Random;

/**
 * @author zhaozhanyang
 *坦克入口类
 */
public class Main {
	static Random random = new Random();
	public static void main(String[] args) throws InterruptedException {
		TankFrame tf = new TankFrame();
//		randomCreteBadTank(tf);
		while(true) {
			Thread.sleep(50);
			tf.repaint();
		}
	}

	private static void randomCreteBadTank(TankFrame tf) {
		
		Dir d = Dir.DOWN;
		for(int i=0; i<10; i++) {
			int dir = random.nextInt(4);
			switch (dir) {
			case 0:
				d=Dir.DOWN;
				break;
			case 1:
				d=Dir.UP;
				break;
			case 2:
				d=Dir.LEFT;
				break;
			case 3:
				d=Dir.RIGHT;
				break;
			default:
				break;
			}
//			tf.tanks.add(new Tank(random.nextInt(600)+200, random.nextInt(400)+200 , d, tf, Group.BAD));
		}
	}
}

