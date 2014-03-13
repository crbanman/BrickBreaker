package ca.codybanman.brickbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected int centerX, centerY;
	protected Color color;

	public GameObject(int centerX, int centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
	}

	public abstract void update();

	public abstract boolean checkCollision(Rectangle collisionBox);

	public abstract void draw(Graphics g);

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
