package ca.codybanman.brickbreaker;

import java.awt.geom.Ellipse2D;

public class Ball extends GameObject {
	
	private final int RADIUS = 10;
	private int speedX, speedY;
	
	public boolean visible;
	
	public Ellipse2D.Double collisionBox = new Ellipse2D.Double();

	public Ball(int centerX, int centerY) {
		super(centerX, centerY);
		collisionBox.setFrame(centerX - RADIUS, centerY - RADIUS, RADIUS, RADIUS);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
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

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
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
