package ca.codybanman.brickbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends GameObject {

	private final int RADIUS = 5;
	private final int MAXSPEEDX = 12;
	private int speedX = 5;
	private int speedY = 5;

	private boolean collision = false;

	public Rectangle collisionBox = new Rectangle(0, 0, 0, 0);

	public Ball(int centerX, int centerY) {
		super(centerX, centerY);
		updateCollisionBox();
		this.color = Color.red;
	}

	@Override
	public void update() {

		centerX += speedX;
		centerY += speedY;
		updateCollisionBox();

		wallCollisions();
		paddleCollision();
		brickCollision();

		collision = false;

	}

	private void wallCollisions() {
		if (centerX - RADIUS <= 0) {
			speedX = -speedX;
			centerX = 0 + RADIUS;
		} else if (centerX + RADIUS >= Game.WIDTH + 10) {
			speedX = -speedX;
			centerX = Game.WIDTH + 10 - RADIUS;
		}
		if (centerY - RADIUS <= 0) {
			speedY = -speedY;
			centerY = 0 + RADIUS;
		} else if (centerY + RADIUS >= Game.HEIGHT + RADIUS * 2 + 10) {
			speedY = -speedY;
			centerY = Game.HEIGHT + RADIUS + 10;
		}
	}

	private void paddleCollision() {

		if (checkCollision(Game.paddle.getCollisionBoxLeft())) {
			// speedY = -speedY;
			speedX = -speedX;
			centerX = (int) (Game.paddle.getCollisionBoxLeft().getX() - RADIUS);

		} // hits left side
		else if (checkCollision(Game.paddle.getCollisionBoxRight())) {
			// speedY = -speedY;
			speedX = -speedX;
			centerX = (int) (Game.paddle.getCollisionBoxRight().getX() + RADIUS);
		} // hits right side
		else if (checkCollision(Game.paddle.getCollisionBoxTop())) {
			speedY = -speedY;
			if (Math.abs(speedX) <= MAXSPEEDX) {
				speedX += Game.paddle.getSpeedX()/3;
			}
			if (speedX > MAXSPEEDX) {
				speedX = MAXSPEEDX;
			} else if (speedX < -MAXSPEEDX) {
				speedX = -MAXSPEEDX;
			}
			centerY = (int) (Game.paddle.getCollisionBoxTop().getY() - RADIUS);
		} // hits top
	}

	private void brickCollision() {
		for (int i = 0; i < Game.brickArray.size(); i++) {
			if (checkCollision(Game.brickArray.get(i).collisionBox)) {
				if (checkCollision(Game.brickArray.get(i).getCollisionBoxTop())
						&& !collision) {
					speedY = -speedY;
					centerY = (int) (Game.brickArray.get(i)
							.getCollisionBoxTop().getY() - RADIUS);
					Game.brickArray.get(i).hit();
					collision = true;
				} // top collision
				else if (checkCollision(Game.brickArray.get(i)
						.getCollisionBoxBottom()) && !collision) {
					speedY = -speedY;
					centerY = (int) (Game.brickArray.get(i)
							.getCollisionBoxBottom().getY() + RADIUS);
					Game.brickArray.get(i).hit();
					collision = true;
				} // bottom collision
				else if (checkCollision(Game.brickArray.get(i)
						.getCollisionBoxLeft()) && !collision) {
					speedX = -speedX;
					centerX = (int) (Game.brickArray.get(i)
							.getCollisionBoxLeft().getX() - RADIUS);
					Game.brickArray.get(i).hit();
					collision = true;
				} // left collision
				else if (checkCollision(Game.brickArray.get(i)
						.getCollisionBoxRight()) && !collision) {
					speedX = -speedX;
					centerX = (int) (Game.brickArray.get(i)
							.getCollisionBoxRight().getX() + RADIUS);
					Game.brickArray.get(i).hit();
					collision = true;
				} // right collision

			} // if intersects
		}
	}

	@Override
	public boolean checkCollision(Rectangle collisionBox) {
		return this.collisionBox.intersects(collisionBox);

	}

	private void updateCollisionBox() {
		collisionBox.setBounds(centerX - RADIUS, centerY - RADIUS, RADIUS * 2,
				RADIUS * 2);
	}

	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.fillOval(this.getCenterX() - this.getRADIUS(), this.getCenterY()
				- this.getRADIUS(), this.getRADIUS() * 2, this.getRADIUS() * 2);
		g.setColor(Color.BLACK);
		g.drawOval(this.getCenterX() - this.getRADIUS(), this.getCenterY()
				- this.getRADIUS(), this.getRADIUS() * 2, this.getRADIUS() * 2);
	}

	public int getX() {
		return centerX - RADIUS;
	}

	public int getY() {
		return centerY - RADIUS;
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
