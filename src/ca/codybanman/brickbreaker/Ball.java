package ca.codybanman.brickbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends GameObject {

	private final int RADIUS = 10;
	private final int SPEED = 5;
	private int speedX = SPEED;
	private int speedY = SPEED;

	public Rectangle collisionBox = new Rectangle(0, 0, 0, 0);

	public Ball(int centerX, int centerY) {
		super(centerX, centerY);
		collisionBox.setBounds(centerX - RADIUS, centerY - RADIUS, RADIUS,
				RADIUS);
		this.color = Color.red;
	}

	@Override
	public void update() {

		centerX += speedX;
		centerY += speedY;
		collisionBox.setBounds(centerX - RADIUS, centerY - RADIUS, RADIUS * 2,
				RADIUS * 2);

		if (centerX - RADIUS <= 0 || centerX + RADIUS >= Game.WIDTH + 10) {
			speedX = -speedX;
		}
		if (centerY - RADIUS <= 0
				|| centerY + RADIUS >= Game.HEIGHT + RADIUS * 2 + 10) {
			speedY = -speedY;
		}

		// Paddle Collisions

		if (checkCollision(Game.paddle.getCollisionBoxTop())) {
			speedY = -speedY;
		}
		if (checkCollision(Game.paddle.getCollisionBoxLeft())) {
			if (Game.paddle.getSpeedX() < 0) {
				speedX = -SPEED;
			} else {
				speedX = -speedX;
			}
		}
		if (checkCollision(Game.paddle.getCollisionBoxRight())) {
			if (Game.paddle.getSpeedX() > 0) {
				speedX = SPEED;
			} else {
				speedX = -speedX;
			}
		}

		// Brick Collisions

		for (int i = 0; i < Game.brickArray.size(); i++) {
			if (checkCollision(Game.brickArray.get(i).collisionBox)) {
				if ((checkCollision(Game.brickArray.get(i).collisionBoxLeft) && checkCollision(Game.brickArray
						.get(i).collisionBoxTop))
						|| (checkCollision(Game.brickArray.get(i).collisionBoxRight) && checkCollision(Game.brickArray
								.get(i).collisionBoxTop))
						|| (checkCollision(Game.brickArray.get(i).collisionBoxLeft) && checkCollision(Game.brickArray
								.get(i).collisionBoxBottom))
						|| (checkCollision(Game.brickArray.get(i).collisionBoxRight) && checkCollision(Game.brickArray
								.get(i).collisionBoxBottom))) {
					speedX = -speedX;
					speedY = -speedY;
					Game.brickArray.get(i).hit();
				} // if it hits corners
				else if (checkCollision(Game.brickArray.get(i).collisionBoxLeft)
						|| checkCollision(Game.brickArray.get(i).collisionBoxRight)) {
					speedX = -speedX;
					Game.brickArray.get(i).hit();
				}// if hits sides
				else if (checkCollision(Game.brickArray.get(i).collisionBoxTop)
						|| checkCollision(Game.brickArray.get(i).collisionBoxBottom)) {
					speedY = -speedY;
					Game.brickArray.get(i).hit();
				} // hits top or bottom
			} // if intersects
		}

	}

	@Override
	public boolean checkCollision(Rectangle collisionBox) {
		return this.collisionBox.intersects(collisionBox);

	}

	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.fillOval(this.getCenterX() - this.getRADIUS(), this.getCenterY()
				- this.getRADIUS(), this.getRADIUS() * 2, this.getRADIUS() * 2);
		g.setColor(Color.BLACK);
		g.drawOval(this.getCenterX() - this.getRADIUS(), this.getCenterY()
				- this.getRADIUS(), this.getRADIUS() * 2, this.getRADIUS() * 2);
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

	public Rectangle getCollisionBox() {
		return collisionBox;
	}

	public void setCollisionBox(Rectangle collisionBox) {
		this.collisionBox = collisionBox;
	}

	public int getRADIUS() {
		return RADIUS;
	}

}
