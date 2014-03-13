package ca.codybanman.brickbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Brick extends GameObject {

	private final int WIDTH = 50;
	private final int HEIGHT = 20;

	private int health;

	public Rectangle collisionBox = new Rectangle(0, 0, 0, 0);

	public Brick(int centerX, int centerY, int health) {
		super(centerX, centerY);
		this.health = health;
		collisionBox.setBounds(centerX - WIDTH, centerY - HEIGHT, centerX
				+ WIDTH, centerY + HEIGHT);
		updateColor();
	}

	private void updateColor() {
		switch (health) {
		case -1:
			color = new Color(128, 128, 128);
			break;
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
		default:
			color = new Color(0, 51, 102);
		}
	}

	@Override
	public void update() {
		updateColor();
	}

	@Override
	public boolean checkCollision(Rectangle collisionBox) {
		return false;

	}
	
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.fillRect(this.getCenterX() - this.getWIDTH()/2, this.getCenterY()
				- this.getWIDTH()/2, this.getWIDTH(), this.getHEIGHT());
		g.setColor(Color.black);
		g.drawRect(this.getCenterX() - this.getWIDTH()/2, this.getCenterY()
				- this.getWIDTH()/2, this.getWIDTH(), this.getHEIGHT());
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

	public void setCollisionBox(Rectangle r) {
		collisionBox = r;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

}
