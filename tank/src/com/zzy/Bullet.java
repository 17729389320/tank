package com.zzy;

import java.awt.Graphics;
import java.awt.Rectangle;




/**
 * @author zhaozhanyang
 *子弹实体类
 */
public class Bullet  extends GameObject{
	//速度
	private static final int SPEED = 10;
	//大小
	public static int WIDTH = ResourceMgr.bulletD.getWidth();
	public static int HEIGHT = ResourceMgr.bulletD.getHeight();
//	private static int WIDTH = 30, HEIGHT = 30;
	//位置
	private int x, y;
	//方向
	private Dir dir;
	//是否活着，删除子弹
	private boolean living = true;
	GameModel gm = null;
	public  Group group = Group.BAD;
	
	
	public Rectangle rect = new Rectangle();
	public Bullet(int x, int y, Dir dir,Group group,GameModel gm) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.gm = gm;
		this.group=group;
		
		rect.x = this.x;
		rect.y = this.y;
		rect.width = WIDTH;
		rect.height = HEIGHT;
		gm.add(this);
	}
	
	public void paint(Graphics g) {
//		Color c = g.getColor();
//		g.setColor(Color.RED);
//		g.fillOval(x, y, WIDTH, HEIGHT);
//		g.setColor(c);
//		if(!living) {
//			tf.bullets.remove(this);			 
//		}			 
		switch (dir) {
		case LEFT:
			g.drawImage(ResourceMgr.bulletL, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.bulletU, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.bulletR, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.bulletD, x, y, null);
			break;
		default:
			break;
		}
		//坦克移动
		move();
		rect.x = this.x;
		rect.y = this.y;
		//移除子弹
		removeBullet();
	}
	
	private void move() {
		
		switch (dir) {
		case LEFT:
			x -= SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		}
		
	}

	private void removeBullet() {
		if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;
		if(!living) {
			gm.remove(this);
		}
	}
	
	//碰撞检测
	public boolean collideWith(Tank tank) {
		//如果是同一个组，无需做碰撞检测； 
		if(this.group == tank.getGroup()) return false;
		if(rect.intersects(tank.rect)) {
			tank.die(tank.getX(),tank.getY());
			this.die();
			int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
			int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
			gm.add(new Explode(eX, eY, gm));	 
			return true;
		}
		return false;

 	}

 	public void die() {
		this.living = false;
	}
 	 
	public static int getWIDTH() {
		return WIDTH;
	}

	public static void setWIDTH(int wIDTH) {
		WIDTH = wIDTH;
	}
	public static int getHEIGHT() {
		return HEIGHT;
	}

	public static void setHEIGHT(int hEIGHT) {
		HEIGHT = hEIGHT;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
}
