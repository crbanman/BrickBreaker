package ca.codybanman.brickbreaker;

import java.awt.Rectangle;

public class Brick extends GameObject{
	
	private final int WIDTH = 50;
	private final int HEIGHT = 20;
	
	private int health;
	
	public Rectangle collisionBox = new Rectangle(0, 0, 0, 0);
	
	public Brick(int centerX, int centerY, int health) {
		super(centerX, centerY);
		this.health = health;
		collisionBox.setBounds(centerX - WIDTH, centerY - HEIGHT, centerX + WIDTH, centerY + HEIGHT);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void checkCollision() {
		// TODO Auto-generated method stub
		
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
