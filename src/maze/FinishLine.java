package maze;

import java.awt.Color;
import java.awt.Graphics;

public class FinishLine extends GameObject {

	public FinishLine(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	void update() {
		collisionBox.setBounds(x, y, width, height);
	}

	void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(x, y, width, height);
	}
}
