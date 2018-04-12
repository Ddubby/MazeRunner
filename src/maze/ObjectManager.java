package maze;

import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
	Runner runner;
	ArrayList<Barrier> barriers = new ArrayList<Barrier>();
	ArrayList<Chaser> chaser = new ArrayList<Chaser>();
	long enemyTimer = 0;

	ObjectManager(Runner r) {
		runner = r;
	}

	void update() {
		runner.update();
		for (Barrier b : barriers) {
			b.update();
		}
	}

	void draw(Graphics g) {
		runner.draw(g);
		for (Barrier b : barriers) {
			b.draw(g);
		}
	}

	public void AIChaser() {

	}
	void addChaser(Chaser c) {
		chaser.add(c);
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
				for (Chaser c : chaser) {
					if (c.collisionBox.intersects(runner.collisionBox)) {
						runner.isAlive = false;
						break;
					}
			}
		}
	}
}
}
