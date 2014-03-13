package ca.codybanman.brickbreaker;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 400;
	public static final int HEIGHT = 550;
	public static final String NAME = "BrickBreak!";
	static Graphics g;

	public static Color background = Color.LIGHT_GRAY;

	private JFrame frame;

	public boolean running = false;
	public Ball ball;
	public Paddle paddle;

	public Game() {
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		frame = new JFrame(NAME);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		ball = new Ball(100, 100);
		paddle = new Paddle((WIDTH+10)/2, HEIGHT);

	}

	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}

	public synchronized void stop() {
		running = false;
	}

	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;

		int ticks = 0;
		int frames = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true; // set to false to limit FPS to 60

			while (delta >= 1) {
				ticks++;
				tick();
				delta--;
				shouldRender = true;
			}

			// this is for testing purposes only. Remove before distribution.
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println(ticks + " ticks, " + frames + " frames");
				frames = 0;
				ticks = 0;
			}

		}
	}

	public void tick() {
		ball.update();
		paddle.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();
		g.setColor(background);
		g.fillRect(0, 0, getWidth(), getHeight());
		drawBall(g);
		drawPaddle(g);

		g.dispose();
		bs.show();
	}

	private void drawBall(Graphics g2) {
		g.setColor(ball.getColor());
		g.fillOval(ball.getCenterX() - ball.getRADIUS(), ball.getCenterY()
				- ball.getRADIUS(), ball.getRADIUS() * 2, ball.getRADIUS() * 2);
		g.setColor(Color.BLACK);
		g.drawOval(ball.getCenterX() - ball.getRADIUS(), ball.getCenterY()
				- ball.getRADIUS(), ball.getRADIUS() * 2, ball.getRADIUS() * 2);

	}
	
	private void drawPaddle(Graphics g2) {
		g.setColor(paddle.getColor());
		g.fillRect(paddle.getCenterX() - paddle.getWidth()/2, paddle.getCenterY()
				- paddle.getWidth()/2, paddle.getWidth(), paddle.getHEIGHT());
		g.setColor(Color.black);
		g.drawRect(paddle.getCenterX() - paddle.getWidth()/2, paddle.getCenterY()
				- paddle.getWidth()/2, paddle.getWidth(), paddle.getHEIGHT());
	}

	public static void main(String args[]) {
		new Game().start();
	}
}
