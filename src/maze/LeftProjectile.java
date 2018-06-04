package maze;

import java.awt.Color;
import java.awt.Graphics;

public class LeftProjectile extends GameObject {
	int originalX;
	int originalY;
	long resetTime = 0;

	public LeftProjectile(int x, int y, int width, int height) {
		super(x, y, width, height);
		originalX = x;
		originalY = y;
	}

	void update() {
		collisionBox.setBounds(x, y, width, height);
		super.update();
		if (y > MazeRunner.height) {
			resetTime = System.currentTimeMillis();
			x = originalX;
			y = originalY;
		} else {
			if (resetTime == 0) {
				y = y + 3;
				x = x - 2;
			} else if (System.currentTimeMillis() > resetTime + 1000) {
				resetTime = 0;
			}
		}
	}

	void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.drawRect(x, y, width, height);
	}
}
