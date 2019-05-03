package com.zzy;


/**
 * @author zhaozhanyang
 *坦克入口类
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		TankFrame tf = new TankFrame();
		for(int i=0; i<5; i++) {
			tf.tanks.add(new Tank(50 + i*80, 200, Dir.DOWN, tf, Group.BAD));
		}
		while(true) {
			Thread.sleep(50);
			tf.repaint();
		}
		
	}
}

