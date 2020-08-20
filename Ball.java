package Pong;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	double x, y, xVel, yVel; 
	int ballFactor;
	
	public Ball() {
		ballFactor = 1;
		x = 350;
		y = 250;
		xVel = getRandomSpeed()*getRandomDirection();
		yVel = getRandomSpeed()*getRandomDirection();
	}
	
	public double getRandomSpeed() {
		return Math.random()*3 + 2;
	}
	
	public int getRandomDirection() {
		int rand = (int)(Math.random() * 2);
		if (rand == 1)
			return 1;
		else
			return -1;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int)x - 10, (int)y - 10, 20, 20);
		
	}
	
	public void checkPaddleCollision(Paddle p1, Paddle p2) {
		if (x <=50) {
			if (y >= p1.getY() && y <=p1.getY() + 80) {
				xVel = -xVel;
				ballFactor++;
			}
		} else if (x >= 650) {
			if (y >= p2.getY() && y <=p2.getY() + 80) {
				xVel = -xVel;
				ballFactor++;
			}
		}
	}
	public void move() {
		if (ballFactor >= 6)
			ballFactor = 6;
		
		if (y < 10) {
			yVel = -yVel;
		}
		if (y > 490) {
			yVel = -yVel;
		}
		
		x += 0.4*(ballFactor)*xVel;
		y += 0.4*(ballFactor)*yVel;
	
 	}
	
	public int getX() {
		return (int) x;
	}
	
	public int getY() {
		return (int) y;
	}
	
	public void setX(int xPos) {
		x = xPos;
	}
	
	public void setY(int yPos) {
		y = yPos;
	}
	
	public void resetSpeed() {
		xVel = 0;
		yVel = 0;
	}
	
}
