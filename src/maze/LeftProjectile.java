package maze;

import java.awt.Color;
import java.awt.Graphics;

public class LeftProjectile extends GameObject {
	int originalX;
	int originalY;
	public LeftProjectile(int x, int y, int width, int height) {
		super(x, y, width, height);
		originalX = x;
		originalY = y;
	}

	void update() {
		collisionBox.setBounds(x, y, width, height);
		super.update();
		y = y + 3;
		x = x - 2;
		if (y > MazeRunner.height) {
			x = originalX;
			y = originalY;
		}
	}

	void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.drawRect(x, y, width, height);
	}
}
