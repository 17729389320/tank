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
//	Tank myTank = new Tank(200, 400, Dir.DOWN,this, Group.GOOD);
//	List<Tank> tanks = new ArrayList<>();
	static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
//	Bullet b = new Bullet(200,200,Dir.DOWN,this);
	List<Bullet> bullets = new ArrayList<>();
//	Explode e = new Explode(100, 100, this);
//	List<Explode> explodes = new ArrayList<>();
	GameModel gm = new GameModel();
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
		 
		
		
		//画出爆炸效果
		gm.paint(g);
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
			case KeyEvent.VK_X:
				gm.getMainTank().fire();
				break;
			case KeyEvent.VK_C:
				if(!gm.getMainTank().isLiving()) {
					gm.getMainTank().setLiving(true);
				}
				break;
			default:
				break;
			}
			setMainTankDir();
		}
		private void setMainTankDir() {
			if(bL||bR||bD||bU) {
				gm.getMainTank().setMoving(true);
				if(bL)gm.getMainTank().setDir(Dir.LEFT);
				if(bR)gm.getMainTank().setDir(Dir.RIGHT);
				if(bD)gm.getMainTank().setDir(Dir.DOWN);
				if(bU)gm.getMainTank().setDir(Dir.UP);
			}else {
				gm.getMainTank().setMoving(false);
			}
			
		}
		
	}
	
	

}
