package com.zzy;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;





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
	//是否活着
	private boolean living = true;
	private boolean moving = true;
	private Group group = Group.BAD;
	private Random random = new Random();

	public Tank(int x, int y, Dir dir,TankFrame tf,Group group) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf=tf;
		this.group=group;
	}

	public void paint(Graphics g) {

		if(this.group==Group.GOOD) {
			Color c = g.getColor();
			g.setColor(Color.YELLOW);
			g.fillRect(x, y, 50, 50);
			g.setColor(c);
		}
		if(!living) {
			tf.tanks.remove(this);
			tf.e.paint(g);
		}
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
		//出边界改变方向为相反方向继续运动
		if(x < 0 || 
		   y-HEIGHT/2 < 0 || 
		   x > TankFrame.GAME_WIDTH-WIDTH || 
		   y > TankFrame.GAME_HEIGHT-HEIGHT) {
			switch (dir) {
			case LEFT:
				dir=Dir.RIGHT;
				break;
			case RIGHT:
				dir=Dir.LEFT;
				break;
			case DOWN:
				dir=Dir.UP;
				break;
			case UP:
				dir=Dir.DOWN;
				break;
			default:
				break;
			}
		}
		//敌机随机发射子弹
		if(random.nextInt(10) > 8&&this.getGroup()==Group.BAD) this.fire();
		
	}
	//发射子弹
	public void fire() {
		if(!living) return;
//		tf.b=new Bullet(this.x,this.y,this.dir);
//		tf.bullets.add(new Bullet(this.x, this.y, this.dir,tf));
//		tf.bullets.add(new Bullet(this.x+WIDTH/2-Bullet.getWIDTH()/2, this.y+HEIGHT/2-Bullet.getHEIGHT()/2, this.dir, this.tf));
		int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		tf.bullets.add(new Bullet(bX, bY, this.dir, this.tf,this.group));
	}

	public void die(int x,int y) {
		this.living = false;
		tf.e.setX(x);
		tf.e.setY(y);
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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

	public boolean isLiving() {
		return living;
	}

	public void setLiving(boolean living) {
		this.living = living;
	}

	public TankFrame getTf() {
		return tf;
	}

	public void setTf(TankFrame tf) {
		this.tf = tf;
	}

	public static int getSpeed() {
		return SPEED;
	}

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
	
	

}
