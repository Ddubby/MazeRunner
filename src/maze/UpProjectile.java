package maze;

import java.awt.Graphics;

public class UpProjectile extends GameObject {

	int originalX;
	int originalY;
	long resetTime = 0;

	public UpProjectile(int x, int y, int width, int height) {
		super(x, y, width, height);
		originalX = x;
		originalY = y;
	}

	void update() {
		collisionBox.setBounds(x, y, width, height);
		super.update();
		if (MazeRunner.level == 2 || MazeRunner.level == 3) {
			if (y < -10) {
				resetTime = System.currentTimeMillis();
				x = originalX;
				y = originalY;
			} else {
				if (resetTime == 0) {
					y = y - 3;
					x = x + 2;
				} else if (System.currentTimeMillis() > resetTime + 1000) {
					resetTime = 0;
				}
			}
		}
		if (MazeRunner.level == 1) {
			y = y - 3;
			x = x + 2;
			if (y < -10) {
				x = originalX;
				y = originalY;
			}
		}
	}

	void draw(Graphics g) {
		g.drawRect(x, y, width, height);
	}
}
