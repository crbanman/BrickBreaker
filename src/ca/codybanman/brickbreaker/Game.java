package ca.codybanman.brickbreaker;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 400;
	public static final int HEIGHT = 550;
	public static final String NAME = "BrickBreak!";
	static Graphics g;

	public static Color background = Color.LIGHT_GRAY;

	private JFrame frame;

	public boolean running = false;
	public static Ball ball;
	public static Paddle paddle;

	public static ArrayList<Brick> brickArray = new ArrayList<Brick>();

	public Game() {
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		addKeyListener(this);

		frame = new JFrame(NAME);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		paddle = new Paddle((WIDTH + 10) / 2, 525);
		ball = new Ball((WIDTH + 10) / 2, 500);

		try {
			loadLevel("res/level01.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

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
			boolean shouldRender = false; // set to false to limit FPS to 60

			while (delta >= 1) {
				ticks++;
				tick();
				delta--;
				shouldRender = true;
			}

//			// this is for testing purposes only. Remove before distribution.
//			try {
//				Thread.sleep(2);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}

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
		for (int i = 0; i < brickArray.size(); i++) {
			if (brickArray.get(i).getHealth() == 0) {
				brickArray.remove(i);
			}
		}
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

		for (int i = 0; i < brickArray.size(); i++) {
			brickArray.get(i).draw(g);
		}

		paddle.draw(g);
		ball.draw(g);

		g.dispose();
		bs.show();
	}

	private void loadLevel(String filename) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		int width = 8;
		int height = 0;

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		while (true) {
			String line = reader.readLine();
			// no more lines to read
			if (line == null) {
				reader.close();
				break;
			}

			if (!line.startsWith("!")) {
				lines.add(line);
			}
		}
		height = lines.size();

		for (int j = 0; j < height; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {
				if (i < line.length()) {
					if (Character.getNumericValue(line.charAt(i)) != 0) {
						char ch = line.charAt(i);
						if (ch == 'M') {
							Brick b = new Brick(25 + i * 50, 10 + j * 20, -1);
							brickArray.add(b);
						} else {
							Brick b = new Brick(25 + i * 50, 10 + j * 20,
									Character.getNumericValue(ch));
							brickArray.add(b);
						}
					}
				}
			}
		}

	}

	public static void main(String args[]) {
		new Game().start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			paddle.moveLeft();
			paddle.setMovingLeft(true);
			break;
		case KeyEvent.VK_RIGHT:
			paddle.moveRight();
			paddle.setMovingRight(true);
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			paddle.stopLeft();
			break;
		case KeyEvent.VK_RIGHT:
			paddle.stopRight();
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
