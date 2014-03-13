package ca.codybanman.brickbreaker;

public abstract class GameObject {
	
	private int centerX, centerY;
	
	public GameObject(int centerX, int centerY){
		this.centerX = centerX;
		this.centerY = centerY;
	}
	
	public abstract void update();
	
	public abstract void checkCollision();

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

}
