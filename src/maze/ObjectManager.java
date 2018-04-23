package maze;

import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
	Runner runner;
	ArrayList<Barrier> barriers = new ArrayList<Barrier>();
	ArrayList<Chaser> chasers = new ArrayList<Chaser>();
	long enemyTimer = 0;

	ObjectManager(Runner r) {
		runner = r;
	}

	void update() {
		runner.update();
		for (Barrier b : barriers) {
			b.update();
		}
		for (Chaser c : chasers) {
			c.update();
		}
	}

	void draw(Graphics g) {
		runner.draw(g);
		for (Barrier b : barriers) {
			b.draw(g);
		}
	}

	void addChaser(Chaser c) {
		chasers.add(c);
	}
	void addBarrier(Barrier b) {
		barriers.add(b);
	}

	void checkCollision() {
		for (Barrier b : barriers) {
			if (runner.collisionBox.intersects(b.collisionBox)) {
				runner.isAlive = false;
				break;
			} else {
				for (Chaser c : chasers) {
					if (runner.collisionBox.intersects(c.collisionBox)) {
						runner.isAlive = false;
						break;
					}
			}
		}
	}
}
}
