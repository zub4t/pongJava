package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 160;
	public static int HEIGHT = 120;
	private static int SCALE = 3;
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	private double amount_of_ticks = 60.0;
	private boolean isRunning;

	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	Game() {

		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.addKeyListener(this);
		player = new Player(100, HEIGHT - 5);
		enemy = new Enemy(100, 0);
		ball = new Ball(100, HEIGHT / 2 - 1);
	}

	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.isRunning = true;
		new Thread(game).start();

	}

	@Override
	public void run() {
		System.out.println("iniciando");
		long lastTime = System.nanoTime();
		double ns = 1000000000 / amount_of_ticks;
		double timer = System.currentTimeMillis();
		double delta = 0;
		while (isRunning) {
			long now = System.nanoTime();
			delta += ((now - lastTime) / ns);
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				delta--;
			}
		}

	}

	public synchronized void start() {

	}

	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = layer.getGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		player.render(g);
		enemy.render(g);
		ball.render(g);
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

		bs.show();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
			player.left = false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.right = false;
			player.left = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
