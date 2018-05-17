package maze;

import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
	Runner runner;
	ArrayList<Barrier> barriers = new ArrayList<Barrier>();
	ArrayList<Chaser> chasers = new ArrayList<Chaser>();
	ArrayList<Bouncer> bouncers = new ArrayList<Bouncer>();
	ArrayList<Teleport> teleporters = new ArrayList<Teleport>();
	ArrayList<FinishLine> finishLines = new ArrayList<FinishLine>();
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<UpProjectile> upprojectiles = new ArrayList<UpProjectile>();
	ArrayList<LeftProjectile> leftprojectiles = new ArrayList<LeftProjectile>();
	long enemyTimer = 0;

	ObjectManager(Runner r) {
		runner = r;
	}

	void update() {
		runner.update();
		for (Chaser c : chasers) {
			c.update();
		}
		for (Bouncer o : bouncers) {
			o.update();
		}
		for (Teleport t : teleporters) {
			t.update();
		}
		for (FinishLine f : finishLines) {
			f.update();
		}
		for (Projectile p : projectiles) {
			p.update();
		}
		for (UpProjectile u : upprojectiles) {
			u.update();
		}
		for (LeftProjectile l : leftprojectiles) {
			l.update();
		}
	}

	void draw(Graphics g) {
		for (Barrier b : barriers) {
			b.draw(g);
		}
		for (Bouncer b : bouncers) {
			b.draw(g);
		}
		for (Projectile p : projectiles) {
			p.draw(g);
		}
		for (UpProjectile u : upprojectiles) {
			u.draw(g);
		}
		for (LeftProjectile l : leftprojectiles) {
			l.draw(g);
		}
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

	void addTeleport(Teleport t) {
		teleporters.add(t);
	}

	void addFinishLine(FinishLine f) {
		finishLines.add(f);
	}

	void addProjectile(Projectile p) {
		projectiles.add(p);
	}

	void addUpProjectile(UpProjectile u) {
		upprojectiles.add(u);
	}

	void addLeftProjectile(LeftProjectile l) {
		leftprojectiles.add(l);
	}

	void checkBouncerCollision() {
		for (Bouncer o : bouncers) {
			boolean intersects = false;
			for (Barrier b : barriers) {
				if (b.collisionBox.intersects(o.collisionBox)) {
					intersects = true;
				}
			}
			if (intersects) {
				o.speed = o.speed * -1;
				o.x = o.x + o.speed;
			} else {
				o.x = o.x + o.speed;
			}
		}
	}

	void checkWin() {
		for (Teleport t : teleporters) {
			if (runner.collisionBox.intersects(t.collisionBox)) {
				runner.Wins = true;
				break;
			} else {
				for (FinishLine f : finishLines) {
					if (runner.collisionBox.intersects(f.collisionBox)) {
						runner.Wins = true;
						break;
					}
				}
			}
		}
	}

	void purgeObjects() {
		for (int i = 0; i < projectiles.size(); i++) {
			if (!projectiles.get(i).isAlive) {
				projectiles.remove(i);
			}
		}
		for (int i = 0; i < upprojectiles.size(); i++) {
			if (!upprojectiles.get(i).isAlive) {
				upprojectiles.remove(i);
			}
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
							} else {
								for (Projectile p : projectiles) {
									if (runner.collisionBox.intersects(p.collisionBox)) {
										runner.isAlive = false;
										break;
									} else {
										for (UpProjectile u : upprojectiles) {
											if (runner.collisionBox.intersects(u.collisionBox)) {
												runner.isAlive = false;
												break;
											} else {
												for (LeftProjectile l : leftprojectiles) {
													if (runner.collisionBox.intersects(l.collisionBox)) {
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
					}
				}
			}
		}
	}
}