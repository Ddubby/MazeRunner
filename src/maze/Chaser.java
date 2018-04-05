package maze;

import java.awt.Graphics;

public class Chaser extends GameObject {
	int speed;
	
	Chaser(int x, int y, int width, int height){
		super(x, y, width, height);
		speed=5;
	}
	void update() {
		
	}
	void draw(Graphics g) {
		g.drawRect(x, y, width, height);
	}
}
