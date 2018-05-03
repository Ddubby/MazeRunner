package maze;

import java.awt.Graphics;

public class Projectile extends GameObject {
	public Projectile(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	void update() {
		collisionBox.setBounds(x, y, width, height);
		super.update();
		y = y + 3;
		x = x + 2;
		if (y > 297) {
			y=0;
			x=133;
		}
	}

	void draw(Graphics g) {
		g.drawRect(x, y, width, height);
	}
}
