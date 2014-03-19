package ca.codybanman.brickbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends GameObject {

	private final int RADIUS = 5;
	private final int MAXSPEEDX = 11;
	private int speedX = 3;
	private int speedY = 5;

	private int startingX;
	private int startingY;

	private boolean collision = false;

	public Rectangle collisionBox = new Rectangle(0, 0, 0, 0);

	public Ball(double centerX, double centerY) {
		super(centerX, centerY);
		startingX = (int) centerX;
		startingY = (int) centerY;
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
		if (centerY - RADIUS <= 30) {
			speedY = -speedY;
			centerY = 30 + RADIUS;
		} else if (centerY + RADIUS >= Game.HEIGHT + RADIUS * 2 + 10) {
			speedY = -speedY;
			centerY = Game.HEIGHT + RADIUS + 10;
//			reset();
//			Game.paddle.reset();
		}
	}

	private void paddleCollision() {
		if (checkCollision(Game.paddle.getCollisionBox())) {
			Rectangle box = Game.paddle.getCollisionBox();
			handlePaddleCollision(box);
		}
	}

	private void handlePaddleCollision(Rectangle box) {
		if (collisionTop(box) && !collision) {
			if (speedY > 0) {
				speedY = -speedY;
				if (speedX >= MAXSPEEDX) {
					speedX = MAXSPEEDX / 2;
				} else if (speedX <= -MAXSPEEDX) {
					speedX = -MAXSPEEDX / 2;
				}
				if (Math.abs(speedX) <= MAXSPEEDX) {
					speedX += Game.paddle.getSpeedX() / 2;
				}
			} else {
				// Should have actually hit left or right
				speedX = -speedX + Game.paddle.getSpeedX() / 2;
				if (speedX > 0) {
					centerX = box.getMaxX() + RADIUS;
				} else {
					centerX = box.getMinX() - RADIUS;
				}
			}
			centerY = box.getMinY() - RADIUS;
			collision = true;
		} // top collision
		
		else if (collisionBottom(box) && !collision) {
			if (speedY < 0) {
				speedY = -speedY;
			} else {
				speedX = -speedX; // Should have actually hit left or right
				if (speedX > 0) {
					centerX = box.getMaxX() + RADIUS;
				} else {
					centerX = box.getMinX() - RADIUS;
				}
			}
			centerY = box.getMaxY() + RADIUS;
			collision = true;
		} // bottom collision

		else if (collisionLeft(box) && !collision || collisionBox.getCenterX() < box.getCenterX() && ! collision) {
			if (speedX > 0) {
				speedX = -speedX + Game.paddle.getSpeedX() / 2;
			} else {
				speedY = -speedY; // Should have actually hit the top
				if (speedY < 0) {
					if (speedX >= MAXSPEEDX) {
						speedX = MAXSPEEDX / 2;
					} else if (speedX <= -MAXSPEEDX) {
						speedX = -MAXSPEEDX / 2;
					}
					if (Math.abs(speedX) <= MAXSPEEDX) {
						speedX += Game.paddle.getSpeedX() / 2;
					}
				}
			}
			centerX = box.getX() - RADIUS;
			collision = true;
		} // left collision

		else if (!collision) {
			if (speedX < 0) {
				speedX = -speedX + Game.paddle.getSpeedX() / 2;
			} else {
				speedY = -speedY; // Should have actually hit the top
				if (speedY < 0) {
					if (speedX >= MAXSPEEDX) {
						speedX = MAXSPEEDX / 2;
					} else if (speedX <= -MAXSPEEDX) {
						speedX = -MAXSPEEDX / 2;
					}
					if (Math.abs(speedX) <= MAXSPEEDX) {
						speedX += Game.paddle.getSpeedX() / 2;
					}
				}
			}
			centerX = box.getMaxX() + RADIUS;
			collision = true;
		} // right collision
	}

	private void brickCollision() {
		for (int i = 0; i < Game.brickArray.size(); i++) {
			if (checkCollision(Game.brickArray.get(i).collisionBox)) {
				if (i < Game.brickArray.size() - 1
						&& Game.brickArray.get(i + 1).collisionBox.getX() < collisionBox
								.getCenterX()
						&& Game.brickArray.get(i + 1).collisionBox.getX() > Game.brickArray
								.get(i).collisionBox.getX()) {
					handleBrickCollision(Game.brickArray.get(i + 1).collisionBox);
					Game.brickArray.get(i + 1).hit();
				} else {
					handleBrickCollision(Game.brickArray.get(i).collisionBox);
					Game.brickArray.get(i).hit();
				}

				break;
			} // if intersects
		}
	}

	private void handleBrickCollision(Rectangle box) {
		if (collisionTop(box) && !collision) {
			if (speedY > 0) {
				speedY = -speedY;
			} else {
				speedX = -speedX; // Should have actually hit left or right
				if (speedX > 0) {
					centerX = box.getMaxX() + RADIUS;
				} else {
					centerX = box.getMinX() - RADIUS;
				}
			}
			centerY = box.getMinY() - RADIUS;
			collision = true;
		} // top collision

		else if (collisionBottom(box) && !collision) {
			if (speedY < 0) {
				speedY = -speedY;
			} else {
				speedX = -speedX; // Should have actually hit left or right
				if (speedX > 0) {
					centerX = box.getMaxX() + RADIUS;
				} else {
					centerX = box.getMinX() - RADIUS;
				}
			}
			centerY = box.getMaxY() + RADIUS;
			collision = true;
		} // bottom collision

		else if (collisionLeft(box) && !collision || collisionBox.getCenterX() < box.getCenterX() && ! collision) {
			if (speedX > 0) {
				speedX = -speedX;
			} else {
				speedY = -speedY; // Should have actually hit the top or bottom
				if (speedY < 0) {
					centerY = box.getMinY() - RADIUS;
				} else {
					centerY = box.getMaxY() + RADIUS;
				}
			}
			centerX = box.getX() - RADIUS;
			collision = true;
		} // left collision

		else if (!collision) {
			if (speedX < 0) {
				speedX = -speedX;
			} else {
				speedY = -speedY; // Should have actually hit the top or bottom
				if (speedY < 0) {
					centerY = box.getMinY() - RADIUS;
				} else {
					centerY = box.getMaxY() + RADIUS;
				}
			}
			centerX = box.getMaxX() + RADIUS;
			collision = true;
		} // right collision
	}

	private boolean collisionTop(Rectangle box) {
		System.out.println("Top");
		return collisionBox.getCenterY() < box.getCenterY()
				&& collisionBox.getCenterX() > box.getMinX()
				&& collisionBox.getCenterX() < box.getMaxX();
	}

	private boolean collisionBottom(Rectangle box) {
		System.out.println("Bottom");
		return collisionBox.getCenterY() > box.getCenterY()
				&& collisionBox.getCenterX() > box.getMinX()
				&& collisionBox.getCenterX() < box.getMaxX();
	}

	private boolean collisionLeft(Rectangle box) {
		System.out.println("Left");
		return collisionBox.getCenterX() < box.getCenterX()
				&& collisionBox.getCenterY() > box.getMinY()
				&& collisionBox.getCenterY() < box.getMaxY();
	}

	private boolean collisionRight(Rectangle box) {
		System.out.println("Right");
		return collisionBox.getCenterX() > box.getCenterX()
				&& collisionBox.getCenterY() > box.getMinY()
				&& collisionBox.getCenterY() < box.getMaxY();
	}

	@Override
	public boolean checkCollision(Rectangle collisionBox) {
		return this.collisionBox.intersects(collisionBox);

	}

	private void updateCollisionBox() {
		collisionBox.setBounds((int) (centerX - RADIUS + 1), (int) (centerY
				- RADIUS + 1), RADIUS * 2 - 1, RADIUS * 2 - 1);
	}

	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.fillOval((int) this.getCenterX() - this.getRADIUS(),
				(int) this.getCenterY() - this.getRADIUS(),
				this.getRADIUS() * 2, this.getRADIUS() * 2);
		g.setColor(Color.BLACK);
		g.drawOval((int) this.getCenterX() - this.getRADIUS(),
				(int) this.getCenterY() - this.getRADIUS(),
				this.getRADIUS() * 2, this.getRADIUS() * 2);
	}

	public void reset() {
		centerX = startingX;
		centerY = startingY;
	}

	public double getX() {
		return centerX - RADIUS;
	}

	public double getY() {
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
