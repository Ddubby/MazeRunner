package maze;

import java.awt.Graphics;

public class UpProjectile extends GameObject{

	int originalX;
	int originalY;
	public UpProjectile(int x, int y, int width, int height) {
		super(x, y, width, height);
		originalX = x;
		originalY = y;
	}

	void update() {
		collisionBox.setBounds(x, y, width, height);
		super.update();
		y = y - 3;
		x = x + 2;
		if (y < 0) {
			x = originalX;
			y = originalY;
		}
	}

	void draw(Graphics g) {
		g.drawRect(x, y, width, height);
	}
}
