package ca.codybanman.brickbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected double centerX, centerY;
	protected Color color;

	public GameObject(double centerX, double centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
	}

	public abstract void update();

	public abstract boolean checkCollision(Rectangle collisionBox);

	public abstract void draw(Graphics g);

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
