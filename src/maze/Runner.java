package maze;

import java.awt.Color;
import java.awt.Graphics;

public class Runner extends GameObject {
	int speed;
	
	Runner(int x, int y, int width, int height){
		super(x,y, width, height);
		speed=10;
	}
	void update() {
	collisionBox.setBounds(x, y, width, height);
	}
	void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(x, y, width, height);
	}
}
