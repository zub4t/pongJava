package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

	public double x, y;
	public int width, height;
	public static Rectangle bounds;
	public double dx, dy;
	public double speed = 1.4;

	Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 3;
		this.height = 3;

		this.dx = new Random().nextGaussian();
		this.dy = new Random().nextGaussian();
		System.out.println(dx + " --- " + dy);

	}

	public void tick() {
		if (x + (dx * speed) + width > Game.WIDTH) {
			dx *= -1;
		} else if (x + (dx * speed) < 0) {
			dx *= -1;
		}
		if (y >= Game.HEIGHT) {
			// ponto enemy
			// System.out.println("enemy point");
			new Game();
			return;

		} else if (y < 0) {
			// ponto meu
			/// System.out.println("player point");

			new Game();
			return;

		}
		bounds = new Rectangle((int) (x + (dx * speed)), (int) (y + (dy * speed)), width, height);
		Game.player.boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Game.enemy.boundsEnemy = new Rectangle((int) Game.enemy.x, (int) Game.enemy.y, Game.enemy.width,
				Game.enemy.height);
		if (bounds.intersects(Game.player.boundsPlayer)) {
			dy *= -1;
		} else if (bounds.intersects(Game.enemy.boundsEnemy)) {

			dy *= -1;
		}
		x += dx * speed;
		y += dy * speed;

	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int) x, (int) y, width, height);

		g.setColor(Color.white);
		/// g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
		// g.drawRect(Game.player.boundsPlayer.x, Game.player.boundsPlayer.y,
		// Game.player.boundsPlayer.width, Game.player.boundsPlayer.height);
//		g.drawRect(Game.enemy.boundsEnemy.x, Game.enemy.boundsEnemy.x, Game.enemy.boundsEnemy.width, Game.enemy.boundsEnemy.height);

	}
}
