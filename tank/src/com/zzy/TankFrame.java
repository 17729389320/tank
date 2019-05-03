package com.zzy;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;




/**
 * @author zhaozhanyang
 *主面板
 */
public class TankFrame extends Frame{
	Dir dir=Dir.LEFT;
	Tank myTank = new Tank(200, 400, Dir.DOWN,this, Group.GOOD);
	List<Tank> tanks = new ArrayList<>();
	static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
//	Bullet b = new Bullet(200,200,Dir.DOWN,this);
	List<Bullet> bullets = new ArrayList<>();
	Explode e = new Explode(100, 100, this);
	public TankFrame() {
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setResizable(false);
		setTitle("tank war");
		setVisible(true);
		this.addKeyListener(new MyKeyListener());
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {  
				System.exit(0);
			}
			
		});
		
		
	}
	//解决双缓冲问题
	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量:" + bullets.size(), 10, 60);
		g.drawString("敌人的数量:" + tanks.size(), 10, 80);
		if(!myTank.isLiving())g.drawString("按c键复活", 10, 100);
		g.setColor(c);
		//画出主战坦克
		myTank.setGroup(Group.GOOD);
		if(myTank.isLiving()) {
			myTank.paint(g);
		} 
		//画出主战坦克的子弹
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).paint(g);
		}
		//画出敌人坦克
		for (int i = 0; i < tanks.size(); i++) {
			tanks.get(i).paint(g);
		}
//		打掉敌人坦克，需要碰撞检测过程，两个list每一颗子弹每一个坦克都要做碰撞检测，如果撞上了，坦克死，子弹死
		for(int i=0; i<bullets.size(); i++) {
			for(int j = 0; j<tanks.size(); j++) 
				bullets.get(i).collideWith(tanks.get(j));
		}
		//主角光环，敌人子弹与主战坦克做碰撞检测
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).collideWith(myTank);
		}
		
		
		//画出爆炸效果
//		e.paint(g);
	}


	class MyKeyListener extends KeyAdapter{
		boolean bL = false;
		boolean bU = false;
		boolean bR = false;
		boolean bD = false;
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL=true;
				break;
			case KeyEvent.VK_RIGHT:
				bR=true;
				break;
			case KeyEvent.VK_UP:
				bU=true;
				break;
			case KeyEvent.VK_DOWN:
				bD=true;
				break;
			default:
				break;
			}
			setMainTankDir();
		}
		

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL=false;
				break;
			case KeyEvent.VK_RIGHT:
				bR=false;
				break;
			case KeyEvent.VK_UP:
				bU=false;
				break;
			case KeyEvent.VK_DOWN:
				bD=false;
				break;
			case KeyEvent.VK_ALT:
				myTank.fire();
				break;
			case KeyEvent.VK_C:
				if(!myTank.isLiving()) {
					myTank.setLiving(true);
				}
				break;
			default:
				break;
			}
			setMainTankDir();
		}
		private void setMainTankDir() {
			if(bL||bR||bD||bU) {
				myTank.setMoving(true);
				if(bL)myTank.setDir(Dir.LEFT);
				if(bR)myTank.setDir(Dir.RIGHT);
				if(bD)myTank.setDir(Dir.DOWN);
				if(bU)myTank.setDir(Dir.UP);
			}else {
				myTank.setMoving(false);
			}
			
		}
		
	}
	
	

}
