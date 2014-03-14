package ca.codybanman.brickbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Brick extends GameObject {

	private static final int WIDTH = 50;
	private static final int HEIGHT = 20;

	private int health;

	public Rectangle collisionBox = new Rectangle(0, 0, 0, 0);
	public Rectangle collisionBoxTop = new Rectangle(0, 0, 0, 0);
	public Rectangle collisionBoxBottom = new Rectangle(0, 0, 0, 0);
	public Rectangle collisionBoxLeft = new Rectangle(0, 0, 0, 0);
	public Rectangle collisionBoxRight = new Rectangle(0, 0, 0, 0);

	public Brick(int centerX, int centerY, int health) {
		super(centerX, centerY);

		this.health = health;

		collisionBox.setBounds(this.getCenterX() - this.getWIDTH() / 2,
				this.getCenterY() - this.getHEIGHT() / 2, this.getWIDTH(),
				this.getHEIGHT());

		collisionBoxTop.setBounds(this.getCenterX() - this.getWIDTH() / 2 + 2,
				this.getCenterY() - this.getHEIGHT() / 2, this.getWIDTH() - 2,
				1);
		collisionBoxBottom.setBounds(this.getCenterX() - this.getWIDTH() / 2
				+ 2, this.getCenterY() + this.getHEIGHT() / 2,
				this.getWIDTH() - 2, 1);
		collisionBoxLeft.setBounds(this.getCenterX() - this.getWIDTH() / 2,
				this.getCenterY() - this.getHEIGHT() / 2, 1, this.getHEIGHT());
		collisionBoxTop.setBounds(this.getCenterX() + this.getWIDTH() / 2,
				this.getCenterY() - this.getHEIGHT() / 2, 1, this.getHEIGHT());

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
		if (this.collisionBoxTop.intersects(collisionBox.getBounds())
				|| this.collisionBoxBottom.intersects(collisionBox)
				|| this.collisionBoxLeft.intersects(collisionBox.getBounds())
				|| this.collisionBoxRight.intersects(collisionBox.getBounds())) {
			return true;
		}
		return false;

	}

	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.fillRect(this.getCenterX() - this.getWIDTH() / 2, this.getCenterY()
				- this.getHEIGHT() / 2, this.getWIDTH(), this.getHEIGHT());
		g.setColor(Color.black);
		g.drawRect(this.getCenterX() - this.getWIDTH() / 2, this.getCenterY()
				- this.getHEIGHT() / 2, this.getWIDTH(), this.getHEIGHT());
		g.drawString("" + health, centerX, centerY);
	}

	public void hit() {
		health--;
		updateColor();
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public Rectangle getCollisionBoxTop() {
		return collisionBoxTop;
	}

	public void setCollisionBoxTop(Rectangle collisionBoxTop) {
		this.collisionBoxTop = collisionBoxTop;
	}

	public Rectangle getCollisionBoxBottom() {
		return collisionBoxBottom;
	}

	public void setCollisionBoxBottom(Rectangle collisionBoxBottom) {
		this.collisionBoxBottom = collisionBoxBottom;
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

	public int getWIDTH() {
		return WIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

}
