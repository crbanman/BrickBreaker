package ca.codybanman.brickbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle extends GameObject {

	private final int HEIGHT = 15;

	private int width = 75;
	private int speedX = 0;
	private int speedY = 0;
	private int moveSpeed = 4;

	private boolean movingLeft = false;
	private boolean movingRight = false;

	public Rectangle collisionBox = new Rectangle(0, 0, 0, 0);

	public Paddle(int centerX, int centerY) {
		super(centerX, centerY);
		collisionBox.setBounds(this.getCenterX() - this.getWidth() / 2,
				this.getCenterY() - this.getWidth() / 2, this.getWidth(), 1);
		color = Color.blue;
	}

	@Override
	public void update() {
		centerX += speedX;

		if (centerX <= width / 2) {
			centerX = width / 2 + 1;
		}
		if (centerX + width / 2 >= Game.WIDTH + 10) {
			centerX = Game.WIDTH + 10 - width / 2;
			speedX = 0;
			movingRight = false;
		}

		collisionBox.setBounds(this.getCenterX() - this.getWidth() / 2,
				this.getCenterY() - this.getWidth() / 2, this.getWidth(), 1);

	}

	@Override
	public boolean checkCollision(Rectangle collisionBox) {
		if (this.collisionBox.intersects(collisionBox.getBounds())) {

			return true;
		}
		return false;
	}

	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.fillRect(this.getCenterX() - this.getWidth() / 2, this.getCenterY()
				- this.getWidth() / 2, this.getWidth(), this.getHEIGHT());
		g.setColor(Color.black);
		g.drawRect(this.getCenterX() - this.getWidth() / 2, this.getCenterY()
				- this.getWidth() / 2, this.getWidth(), this.getHEIGHT());
	}

	public void moveLeft() {
		speedX = -moveSpeed;
	}

	public void moveRight() {
		speedX = moveSpeed;
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}
		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}
		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}
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
