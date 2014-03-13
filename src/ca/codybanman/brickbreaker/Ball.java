package ca.codybanman.brickbreaker;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class Ball extends GameObject {
	
	private final int RADIUS = 10;
	private int speedX = 5;
	private int speedY = 5;
	
	
	public Ellipse2D.Double collisionBox = new Ellipse2D.Double();

	public Ball(int centerX, int centerY) {
		super(centerX, centerY);
		collisionBox.setFrame(centerX - RADIUS, centerY - RADIUS, RADIUS, RADIUS);
		this.color = Color.red;
	}

	@Override
	public void update() {
		if(centerX - RADIUS <= 0 || centerX + RADIUS >= Game.WIDTH + 10) speedX = speedX * -1;
		if(centerY - RADIUS <= 0 || centerY + RADIUS >= Game.HEIGHT + RADIUS * 2  + 10) speedY = speedY * -1;
		centerX += speedX;
		centerY += speedY;
		collisionBox.setFrame(centerX - RADIUS, centerY - RADIUS, RADIUS, RADIUS);
	}

	@Override
	public void checkCollision() {
		// TODO Auto-generated method stub
		
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public Ellipse2D.Double getCollisionBox() {
		return collisionBox;
	}

	public void setCollisionBox(Ellipse2D.Double collisionBox) {
		this.collisionBox = collisionBox;
	}

	public int getRADIUS() {
		return RADIUS;
	}

}
