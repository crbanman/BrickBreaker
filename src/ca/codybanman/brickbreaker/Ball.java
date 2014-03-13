package ca.codybanman.brickbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends GameObject {

	private final int RADIUS = 10;
	private int speedX = 5;
	private int speedY = 5;

	public Rectangle collisionBox = new Rectangle(0, 0, 0, 0);

	public Ball(int centerX, int centerY) {
		super(centerX, centerY);
		collisionBox.setBounds(centerX - RADIUS, centerY - RADIUS, RADIUS,
				RADIUS);
		this.color = Color.red;
	}

	@Override
	public void update() {
		if (centerX - RADIUS <= 0 || centerX + RADIUS >= Game.WIDTH + 10)
			speedX = speedX * -1;
		if (centerY - RADIUS <= 0
				|| centerY + RADIUS >= Game.HEIGHT + RADIUS * 2 + 10)
			speedY = speedY * -1;
		centerX += speedX;
		centerY += speedY;
		collisionBox.setBounds(centerX - RADIUS, centerY - RADIUS, RADIUS * 2,
				RADIUS * 2);
	}

	@Override
	public boolean checkCollision(Rectangle collisionBox) {
		return false;

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
