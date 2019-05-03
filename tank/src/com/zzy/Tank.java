package com.zzy;

import java.awt.Color;
import java.awt.Graphics;





/**
 * @author zhaozhanyang
 *坦克实体类
 */
public class Tank {
	//位置
	private int x, y;
	//方向
	private Dir dir = Dir.DOWN;
	//速度
	private static final int SPEED = 5;
	public static int WIDTH = ResourceMgr.tankD.getWidth();
	public static int HEIGHT = ResourceMgr.tankD.getHeight();

	private boolean moving = false;
	
	private TankFrame tf = null;
	
	
	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}


	public Tank(int x, int y, Dir dir,TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf=tf;
	}

	public void paint(Graphics g) {
//		Color c = g.getColor();
//		g.setColor(Color.YELLOW);
//		g.fillRect(x, y, 50, 50);
//		g.setColor(c);
		switch (dir) {
		case LEFT:
			g.drawImage(ResourceMgr.tankL, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.tankU, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.tankR, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.tankD, x, y, null);
			break;
		default:
			break;
		}
		move();
		
		
	}

	private void move() {
		if(!moving) return ;
		switch (dir) {
		case LEFT:
			x-=SPEED;
			break;
		case RIGHT:
			x+=SPEED;
			break;
		case DOWN:
			y+=SPEED;
			break;
		case UP:
			y-=SPEED;
			break;
		default:
			break;
		}
		
	}
	//发射子弹
	public void fire() {
//		tf.b=new Bullet(this.x,this.y,this.dir);
//		tf.bullets.add(new Bullet(this.x, this.y, this.dir,tf));
//		tf.bullets.add(new Bullet(this.x+WIDTH/2-Bullet.getWIDTH()/2, this.y+HEIGHT/2-Bullet.getHEIGHT()/2, this.dir, this.tf));
		int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		tf.bullets.add(new Bullet(bX, bY, this.dir, this.tf));
	}

	 
	
	

}
