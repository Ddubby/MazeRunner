package maze;

import java.awt.Color;
import java.awt.Graphics;

public class Chaser extends GameObject {
	int speed;
	
	Chaser(int x, int y, int width, int height){
		super(x, y, width, height);
		speed=10;
	}
	void update() {
		collisionBox.setBounds(x, y, width, height);
	}
	void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.drawRect(x, y, width, height);
	}
}
