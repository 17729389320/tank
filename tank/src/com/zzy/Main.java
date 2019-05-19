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
		while(true) {
			Thread.sleep(500);
			tf.repaint();
		}
	}

	 
}

