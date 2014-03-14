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

	public Rectangle collisionBoxTop = new Rectangle(0, 0, 0, 0);
	public Rectangle collisionBoxLeft = new Rectangle(0, 0, 0, 0);
	public Rectangle collisionBoxRight = new Rectangle(0, 0, 0, 0);

	public Paddle(int centerX, int centerY) {
		super(centerX, centerY);
		collisionBoxTop.setBounds(this.getCenterX() - this.getWidth() / 2 + 3,
				this.getCenterY(), this.getWidth(), 1);
		collisionBoxLeft.setBounds(this.getCenterX() - this.getWidth() / 2,
				this.getCenterY(), 3, this.getHEIGHT());
		collisionBoxRight.setBounds(this.getCenterX() + this.getWidth() / 2,
				this.getCenterY(), 3, this.getHEIGHT());
		color = new Color(0, 128, 255);
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

		collisionBoxTop.setBounds(this.getCenterX() - this.getWidth() / 2 + 1,
				this.getCenterY(), this.getWidth() - 1, 1);
		collisionBoxLeft.setBounds(this.getCenterX() - this.getWidth() / 2,
				this.getCenterY(), 2, this.getHEIGHT());
		collisionBoxRight.setBounds(this.getCenterX() + this.getWidth() / 2,
				this.getCenterY(), 2, this.getHEIGHT());

	}

	@Override
	public boolean checkCollision(Rectangle collisionBox) {
		if (this.collisionBoxTop.intersects(collisionBox.getBounds())
				|| this.collisionBoxLeft.intersects(collisionBox.getBounds())
				|| this.collisionBoxRight.intersects(collisionBox.getBounds())) {
			return true;
		}
		return false;
	}

	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.fillOval(this.getCenterX() - this.getWidth() / 2, this.getCenterY(),
				this.getWidth(), this.getHEIGHT());
		g.setColor(Color.black);
		g.drawOval(this.getCenterX() - this.getWidth() / 2, this.getCenterY(),
				this.getWidth(), this.getHEIGHT());
		g.setColor(this.getColor());
		g.fillRect(this.getCenterX() - this.getWidth() / 2, this.getCenterY()
				+ this.getHEIGHT() / 2, this.getWidth(), this.getHEIGHT() / 2 + 4);
		g.setColor(Color.black);
		g.drawRect(this.getCenterX() - this.getWidth() / 2, this.getCenterY()
				+ this.getHEIGHT() / 2, this.getWidth(), this.getHEIGHT() / 2 + 4);
		g.setColor(this.getColor());
		g.drawLine(this.getCenterX() - this.getWidth() / 2, this.getCenterY()
				+ this.getHEIGHT() / 2, this.getCenterX() + this.getWidth()/2, this.getCenterY()
				+ this.getHEIGHT() / 2);
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

	public Rectangle getCollisionBoxTop() {
		return collisionBoxTop;
	}

	public void setCollisionBoxTop(Rectangle collisionBoxTop) {
		this.collisionBoxTop = collisionBoxTop;
	}

	public Rectangle getCollisionBoxLeft() {
		return collisionBoxLeft;
	}

	public void setCollisionBoxLeft(Rectangle collisionBoxLeft) {
		this.collisionBoxLeft = collisionBoxLeft;
	}

	public Rectangle getCollisionBoxRight() {
		return collisionBoxRight;
	}

	public void setCollisionBoxRight(Rectangle collisionBoxRight) {
		this.collisionBoxRight = collisionBoxRight;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

}
