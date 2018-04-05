package maze;

import java.awt.Graphics;

public class Barrier extends GameObject{

	public Barrier(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	void update() {
		
	}
	void draw(Graphics g) {
		g.drawRect(x, y, width, height);
	}
}
