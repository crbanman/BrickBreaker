package ca.codybanman.brickbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle extends GameObject {

	private final int HEIGHT = 15;
	
	private int startingX;
	private int startingY;

	private int width = 70;
	private int speedX = 0;
	private int speedY = 0;
	private int moveSpeed = 8;

	private boolean movingLeft = false;
	private boolean movingRight = false;

	public Rectangle collisionBoxTop = new Rectangle(0, 0, 0, 0);
	public Rectangle collisionBoxLeft = new Rectangle(0, 0, 0, 0);
	public Rectangle collisionBoxRight = new Rectangle(0, 0, 0, 0);

	public Paddle(int centerX, int centerY) {
		super(centerX, centerY);
		startingX = centerX;
		startingY = centerY;
		updateCollisionBox();
		color = new Color(0, 128, 255);
	}

	@Override
	public void update() {
		centerX += speedX;

		if (centerX <= width / 2) {
			centerX = width / 2 + 1;
		}
		if (centerX + width / 2 >= Game.WIDTH + 10) {
			centerX = Game.WIDTH + 10 - width / 2 - 1;
			speedX = 0;
			movingRight = false;
		}

		updateCollisionBox();

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
	
	private void updateCollisionBox(){
		collisionBoxTop.setBounds((int)(this.getCenterX() - this.getWidth() / 2),
				(int)this.getCenterY(), this.getWidth(), 5);
		collisionBoxLeft.setBounds((int)(this.getCenterX() - this.getWidth() / 2),
				(int)this.getCenterY()+5, 1, this.getHEIGHT()-5);
		collisionBoxRight.setBounds((int)(this.getCenterX() + this.getWidth() / 2),
				(int)this.getCenterY()+5, 1, this.getHEIGHT()-5);
	}

	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.fillRect((int)(this.getCenterX() - this.getWidth() / 2), (int)this.getCenterY(),
				this.getWidth(), this.getHEIGHT());
		g.setColor(Color.black);
		g.drawRect((int)(this.getCenterX() - this.getWidth() / 2), (int)this.getCenterY(),
				this.getWidth(), this.getHEIGHT());
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
	
	public void reset() {
		centerX = startingX;
		centerY = startingY;
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
