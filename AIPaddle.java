package Pong;

import java.awt.Color;
import java.awt.Graphics;

public class AIPaddle implements Paddle{
	final double GRAVITY = 0.84;
	double y, yVel;
	boolean upAccel, downAccel;
	int player, x;
	Ball ball;
	int rand;
	
	public AIPaddle (int player, Ball b){ //player is 1 or 2
		upAccel = false; downAccel = false;
		ball = b;
		y = 210; yVel = 0;
		if (player == 1)
			x = 20; //left side
		else
			x = 660; //right side
		rand = (int)(Math.random()*3 + 1);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, (int)y, 20, 80);
	}

	@Override
	public void move() {
		
		if (rand == 1)
			y = moveBadly();
		else
			y = ball.getY() - 40;
		
		if (y < 0)
			y = 0;
		if (y > 420)
			y = 420;
	}
	
	public int moveBadly() {
		return ball.getY() - 100;
	}

	
	@Override
	public int getY() {
		return (int)y;
	}

}
