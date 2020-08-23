package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player {
	public boolean right;
	public boolean left;
	public int x, y;
	public int width = 40;
	public int height = 5;
	public static Rectangle boundsPlayer;

	Player(int x, int y) {

		this.x = x;
		this.y = y;
	}

	public void tick() {
		if (right && (x+width < Game.WIDTH)) {
			x+=2;
		} else if (left && (x > 0)) {
			x-=2;
		}

	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.RED);
		g2.fillRect(x, y, width, height);
	}

}
