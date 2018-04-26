package maze;

import java.awt.Graphics;

public class Bouncer extends GameObject {
	int speed;
	
	Bouncer(int x, int y, int width, int height){
		super(x, y, width, height);
		speed=5;
	}
	void update() {
		collisionBox.setBounds(x, y, width, height);
	}
	void draw(Graphics g) {
		g.drawRect(x, y, width, height);
	}
}

