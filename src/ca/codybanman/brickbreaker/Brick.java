package ca.codybanman.brickbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Brick extends GameObject {

	private static final int WIDTH = 52;
	private static final int HEIGHT = 20;

	private int health;

	public Rectangle collisionBox = new Rectangle(0, 0, 0, 0);

	public Brick(int centerX, int centerY, int health) {
		super(centerX, centerY);

		this.health = health;

		collisionBox.setBounds((int) (this.getCenterX() - this.getWIDTH() / 2),
				(int) (this.getCenterY() - this.getHEIGHT() / 2),
				this.getWIDTH(), this.getHEIGHT());

		updateColor();
	}

	private void updateColor() {
		switch (health) {
		case 1:
			color = new Color(0, 128, 255);
			break;
		case 2:
			color = new Color(0, 255, 128);
			break;
		case 3:
			color = new Color(255, 0, 128);
			break;
		case 4:
			color = new Color(128, 0, 255);
			break;
		default:
			color = new Color(72, 81, 90);
			;
		}
	}

	@Override
	public void update() {
		updateColor();
	}

	@Override
	public boolean checkCollision(Rectangle collisionBox) {
		return this.collisionBox.intersects(collisionBox);

	}

	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.fillRect((int) (this.getCenterX() - this.getWIDTH() / 2),
				(int) (this.getCenterY() - this.getHEIGHT() / 2),
				this.getWIDTH(), this.getHEIGHT());
		g.setColor(Color.black);
		g.drawRect((int) (this.getCenterX() - this.getWIDTH() / 2),
				(int) (this.getCenterY() - this.getHEIGHT() / 2),
				this.getWIDTH(), this.getHEIGHT());
		g.drawString("" + health, (int)centerX, (int)centerY);
	}

	public void hit() {
		health--;
		Game.score += 10;
		updateColor();
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public Rectangle getCollisionBox() {
		return collisionBox;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

}
