package ca.codybanman.brickbreaker;

import java.awt.Rectangle;

public class Paddle extends GameObject {

	private final int HEIGHT = 15;
	
	private int width = 75;
	private int speedX = 0;
	private int speedY = 0;
	
	private boolean movingLeft = false;
	private boolean movingRight = false;
	
	public Rectangle collisionBox = new Rectangle(0, 0, 0, 0);
	
	public Paddle(int centerX, int centerY) {
		super(centerX, centerY);
		collisionBox.setBounds(centerX - width, centerY - HEIGHT, centerX + width, centerY + HEIGHT);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkCollision() {
		// TODO Auto-generated method stub

	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
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

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public Rectangle getCollisionBox() {
		return collisionBox;
	}

	public void setCollisionBox(Rectangle collisionBox) {
		this.collisionBox = collisionBox;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}
	

}
