package Pong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PongGame extends Applet implements Runnable, KeyListener {

	final int WIDTH = 700, HEIGHT = 500;
	Thread thread;
	HumanPaddle p1;// p2;
	Ball ball;
	AIPaddle AI;
	boolean gameStarted;
	boolean goal;
	
	int p1_score=0;
	int p2_score=0;
	String score;
	
	Graphics gfx;
	Image img;

	public void init() {
		this.resize(WIDTH, HEIGHT);
		goal = false;
		gameStarted = false;
		this.addKeyListener(this);
		
		p1 = new HumanPaddle(1);
		//p2 = new HumanPaddle(2);
		ball = new Ball();
		AI = new AIPaddle(2, ball);
		
		img = createImage(WIDTH, HEIGHT);
		gfx = img.getGraphics();
		
		thread = new Thread(this);
		thread.start();
	}

	public void paint(Graphics g) {
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, WIDTH, HEIGHT);
		
		if (ball.getX() < 0 || ball.getX() > 700) {
			g.setColor(Color.RED);
			g.drawString("GOAL", 350,  250);
			
			if (ball.getX() < 0) {
				p2_score++;
			} else if (ball.getX() > 700) {
				p1_score++;
			}
			
			goal = true;
		}
		else {
			p1.draw(gfx);
			AI.draw(gfx);
			//p2.draw(gfx);
			ball.draw(gfx);
		}
		
		if (goal) {
			gfx.setColor(Color.white);
			gfx.drawString("GOAL", 320,  200);
			gfx.drawString("Hit ENTER to start", 292, 130);
			
			ball = new Ball();
			AI = new AIPaddle(2, ball);
		}
		if (!gameStarted) {
			gfx.setColor(Color.white);
			gfx.drawString("Pong Game", 315, 100);
			gfx.drawString("Hit ENTER to start", 292, 130);
		}
		score = p1_score+" "+p2_score;
		
		gfx.drawString(score, 335, 50);
		g.drawImage(img, 0, 0, this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void run() {
		while (true) {
			if (gameStarted && !goal) {
				p1.move();
				AI.move();
				//p2.move();
				ball.move();
				ball.checkPaddleCollision(p1, AI);//p2
				
			}
			
			
			
			repaint();

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		/*if (e.getKeyCode() == KeyEvent.VK_UP) {
			p2.setUpAccel(true);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p2.setDownAccel(true);

		}
		*/
		if (e.getKeyCode() == KeyEvent.VK_W) {
			p1.setUpAccel(true);
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			p1.setDownAccel(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			gameStarted = true;
			goal = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		/*if (e.getKeyCode() == KeyEvent.VK_UP) {
			p2.setUpAccel(false);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p2.setDownAccel(false);
		}
		*/
		if (e.getKeyCode() == KeyEvent.VK_W) {
			p1.setUpAccel(false);
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			p1.setDownAccel(false);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
