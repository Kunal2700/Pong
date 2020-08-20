package Pong;

import java.awt.Color;
import java.awt.Graphics;

public class HumanPaddle implements Paddle{
	final double GRAVITY = 0.84;
	double y, yVel;
	boolean upAccel, downAccel;
	int player, x;
	
	
	public HumanPaddle (int player){ //player is 1 or 2
		upAccel = false; downAccel = false;
		y = 210; yVel = 0;
		if (player == 1)
			x = 20; //left side
		else
			x = 660; //right side
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, (int)y, 20, 80);
	}

	@Override
	public void move() {
		if (upAccel) {	//(0, 0) is in top left	
			yVel -= 2; 
		} else if (downAccel) {
			yVel += 2;
		} else if (!upAccel && !downAccel) {
			yVel *= GRAVITY;
		}
		
		if (yVel >= 5)
			yVel = 5;
		else if (yVel <= -5)
			yVel = -5;
		
		y += yVel;
		
		if (y < 0)
			y = 0;
		if (y > 420)
			y = 420;
	}

	public void setUpAccel(boolean input) {
		upAccel = input;
	}
	
	public void setDownAccel(boolean input) {
		downAccel = input;
	}
	
	@Override
	public int getY() {
		return (int)y;
	}

}
