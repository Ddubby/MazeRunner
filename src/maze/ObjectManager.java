package maze;

import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
	Runner runner;
	ArrayList<Barrier> barriers = new ArrayList<Barrier>();
	ArrayList<Chaser> chasers = new ArrayList<Chaser>();
	ArrayList<Bouncer> bouncers = new ArrayList<Bouncer>();
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
		for (Bouncer o : bouncers) {
			o.update();
		}
	}

	void draw(Graphics g) {
		runner.draw(g);
	}

	void addChaser(Chaser c) {
		chasers.add(c);
	}

	void addBarrier(Barrier b) {
		barriers.add(b);
	}

	void addBouncer(Bouncer o) {
		bouncers.add(o);
	}

	void BouncerAI(Bouncer o) {
		boolean intersects = false;
		for (Barrier b : barriers) {
			if (b.collisionBox.intersects(o.collisionBox)) {
				intersects = true;
			}
		}
		if (intersects) {
			o.speed = o.speed * -1;
			o.x=o.x+o.speed;
		} else {
			o.x = o.x + o.speed;
		}
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
					} else {
						for (Bouncer o : bouncers) {
							if (runner.collisionBox.intersects(o.collisionBox)) {
								runner.isAlive = false;
								break;
							}
						}
					}
				}
			}
		}
	}
}
