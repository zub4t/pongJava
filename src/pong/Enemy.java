package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {

	public double x, y;
	public int width, height;
	public static Rectangle boundsEnemy;

	Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;

	}

	public void tick() {
		x += (Game.ball.x - x);
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int) x, (int) y, width, height);
	}
}
