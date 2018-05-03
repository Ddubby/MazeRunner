package maze;

import java.awt.Graphics;

public class Teleport extends GameObject {
	public Teleport(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	void update() {
		collisionBox.setBounds(x, y, width, height);
	}

	void draw(Graphics g) {
		g.drawRect(x, y, width, height);
	}
}
