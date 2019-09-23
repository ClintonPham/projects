import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Gamepanel extends JPanel implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 500, HEIGHT = 500;

	private Thread thread;

	private boolean running;
	private boolean right = true, left = false, up = false, down = false;

	private BodyPart b;
	private ArrayList<BodyPart> snake;

	private int score = 0;
	private int xCoor = 10, yCoor = 10, size = 3;
	private int ticks = 0;

	private Apple apple;
	private ArrayList<Apple> apples;

	private Random r;

	public Gamepanel() {

		setFocusable(true);

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);

		snake = new ArrayList<BodyPart>();
		apples = new ArrayList<Apple>();

		r = new Random();

		start();
	}

	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void tick() {
		if (snake.size() == 0) {
			b = new BodyPart(xCoor, yCoor, 10);
			snake.add(b);
		}
		ticks++;

//		Speed of snake
		if (ticks > 850000) {
			if (right)
				xCoor++;
			if (left)
				xCoor--;
			if (up)
				yCoor--;
			if (down)
				yCoor++;

			ticks = 0;

			b = new BodyPart(xCoor, yCoor, 10);
			snake.add(b);

			if (snake.size() > size) {
				snake.remove(0);
			}
		}

		if (apples.size() == 0) {
			int xCoor = r.nextInt(49);
			int yCoor = r.nextInt(49);

			apple = new Apple(xCoor, yCoor, 10);
			apples.add(apple);
		}

		for (int i = 0; i < apples.size(); i++) {
			if (xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()) {
				size++;
				score++;
				apples.remove(i);
				i++;

				 
			}
		}

//		Collision on snake body part
		for (int i = 0; i < snake.size(); i++) {
			if (xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()) {
				if (i != snake.size() - 1) {
					System.out.println("Game Over");
					System.out.println("Test1");
					stop();
				}
			}
		}

//		Collision on border
//		if (xCoor < 0 || xCoor > 49 || yCoor < 0 || yCoor > 49) {
////			System.out.println("Game Over");
//			
//			stop();
//		}
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		for (int i = 0; i < WIDTH / 10; i++) {
			g.drawLine(i * 10, 0, i * 10, HEIGHT);
		}
		for (int i = 0; i < HEIGHT / 10; i++) {
			g.drawLine(0, i * 10, HEIGHT, i * 10);
		}
		for (int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(g);
		}

		for (int i = 0; i < apples.size(); i++) {
			apples.get(i).draw(g);
		}

//		scores
		g.setColor(Color.WHITE);
		g.setFont(new Font("serif", Font.BOLD, 15));
		g.drawString("Score: " + score, 400, 30);
		
//		size
		g.setColor(Color.WHITE);
		g.setFont(new Font("serif", Font.BOLD, 15));
		g.drawString("Size: " + size, 400, 45);
		

		for (int i = 0; i < snake.size(); i++) {
//			Collision on border
			if (xCoor < 0 || xCoor > 49 || yCoor < 0 || yCoor > 49) {

				right = false;
				up = false;
				down = false;
				left = false;

				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game Over", 130, 250);

				g.setColor(Color.red);
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Space to RESTART", 180, 280);

			}
		}

		g.dispose();
	}

	@Override
	public void run() {

		while (running) {
			tick();
			repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void resetGame() {
		score = 0;
		snake.clear();
		running = false;

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_SPACE) {
			resetGame();
			run();

			repaint();

		}

		if (key == KeyEvent.VK_RIGHT && !left) {
			up = false;
			down = false;
			right = true;

		}
		if (key == KeyEvent.VK_LEFT && !right) {
			left = true;
			up = false;
			down = false;
		}
		if (key == KeyEvent.VK_UP && !down) {
			left = false;
			up = true;
			System.out.println("UP");
			right = false;
		}
		if (key == KeyEvent.VK_DOWN && !up) {
			left = false;
			right = false;
			down = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
