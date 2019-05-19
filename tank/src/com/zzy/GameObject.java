package com.zzy;

import java.awt.Graphics;

public abstract class GameObject {
	int x, y;

 	public abstract void paint(Graphics g);

	public int getWidth() {
		return 0;
	}

	public int getHeight() {
		return 0;
	}
}
