package maze;

import java.awt.Graphics;

public class ObjectManagerChaser {
	Chaser chaser;

	ObjectManagerChaser(Chaser c) {
		chaser = c;
	}

	void draw(Graphics g) {
		chaser.draw(g);
	}

	void ChaserAIRight() {
		if (GamePanel.runner.x != chaser.x) {

		}
	}

	void ChaserAIUp() {
		chaser.y = GamePanel.runner.y + GamePanel.runner.speed;
	}
}
