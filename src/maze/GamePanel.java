package maze;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	int MENU_STATE = 0;
	int GAME_STATE = 1;
	int END_STATE = 2;
	int currentState = MENU_STATE;
	Font titleFont = new Font("Times New Roman", Font.BOLD, 48);
	static Runner runner = new Runner(50, 220, 10, 10);
	Chaser chaser = new Chaser(70, 200, 10, 10);
	Bouncer bouncer = new Bouncer(130, 131, 10, 10);
	Bouncer bouncer1 = new Bouncer(330, 209, 10, 10);
	Bouncer bouncer2 = new Bouncer(530, 161, 10, 10);
	Bouncer bouncer3 = new Bouncer(430, 49, 10, 10);
	Bouncer bouncer4 = new Bouncer(630, 81, 10, 10);
	Barrier barrier1 = new Barrier(300, 0, 30, 200);
	Barrier barrier2 = new Barrier(100, 0, 30, 250);
	Barrier barrier3 = new Barrier(600, 100, 30, 200);
	Barrier barrier4 = new Barrier(500, 0, 30, 150);
	Barrier barrier5 = new Barrier(700, 0, 30, 250);
	Barrier barrier6 = new Barrier(200, 150, 30, 150);
	Barrier barrier7 = new Barrier(400, 70, 30, 225);
	ObjectManager manager = new ObjectManager(runner);
	ObjectManagerChaser managerEnemy = new ObjectManagerChaser(chaser);
	Timer timer = new Timer(1000 / 60, this);

	public GamePanel() {
		manager.addBarrier(barrier1);
		manager.addBarrier(barrier2);
		manager.addBarrier(barrier3);
		manager.addBarrier(barrier4);
		manager.addBarrier(barrier5);
		manager.addBarrier(barrier6);
		manager.addBarrier(barrier7);
		manager.addChaser(chaser);
		manager.addBouncer(bouncer);
		manager.addBouncer(bouncer1);
		manager.addBouncer(bouncer2);
		manager.addBouncer(bouncer3);
		manager.addBouncer(bouncer4);
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, MazeRunner.width, MazeRunner.height);
		Rectangle r = new Rectangle(0, 0, MazeRunner.width, MazeRunner.height);
		g.setColor(Color.RED);
		drawCenteredString(g, "Maze Runner", r, titleFont);
	}

	void drawGameState(Graphics g) {
		manager.draw(g);
		managerEnemy.draw(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, MazeRunner.width, MazeRunner.height);
		g.setColor(Color.RED);
		runner.draw(g);
		g.setColor(Color.YELLOW);
		chaser.draw(g);
		g.setColor(Color.GREEN);
		bouncer.draw(g);
		bouncer1.draw(g);
		bouncer2.draw(g);
		bouncer3.draw(g);
		bouncer4.draw(g);
		g.setColor(Color.BLUE);
		barrier1.draw(g);
		barrier2.draw(g);
		barrier3.draw(g);
		barrier4.draw(g);
		barrier5.draw(g);
		barrier6.draw(g);
		barrier7.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, MazeRunner.width, MazeRunner.height);
		Rectangle r = new Rectangle(MazeRunner.width, MazeRunner.height);
	}

	void updateMenuState() {

	}

	void updateGameState() {
		manager.update();
		manager.checkCollision();
		managerEnemy.ChaserAIRight();
		manager.BouncerAI(bouncer);
		manager.BouncerAI(bouncer1);
		manager.BouncerAI(bouncer2);
		manager.BouncerAI(bouncer3);
		manager.BouncerAI(bouncer4);
		if (!runner.isAlive) {
			currentState = END_STATE;
		}
	}

	void updateEndState() {

	}

	void startGame() {
		timer.start();
	}

	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {

			drawMenuState(g);

		} else if (currentState == GAME_STATE) {

			drawGameState(g);

		} else if (currentState == END_STATE) {

			drawEndState(g);

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			if (currentState == END_STATE) {
				runner = new Runner(50, 220, 10, 10);
				manager = new ObjectManager(runner);
				chaser = new Chaser(70, 200, 10, 10);
				bouncer = new Bouncer(130, 131, 10, 10);
				bouncer1 = new Bouncer(330, 209, 10, 10);
				bouncer2 = new Bouncer(530, 161, 10, 10);
				bouncer3 = new Bouncer(430, 49, 10, 10);
				bouncer4 = new Bouncer(630, 81, 10, 10);
				barrier1 = new Barrier(300, 0, 30, 200);
				barrier2 = new Barrier(100, 0, 30, 250);
				barrier3 = new Barrier(600, 100, 30, 200);
				barrier4 = new Barrier(500, 0, 30, 150);
				barrier5 = new Barrier(700, 0, 30, 250);
				barrier6 = new Barrier(200, 150, 30, 150);
				barrier7 = new Barrier(400, 70, 30, 225);
				manager.addBarrier(barrier1);
				manager.addBarrier(barrier2);
				manager.addBarrier(barrier3);
				manager.addBarrier(barrier4);
				manager.addBarrier(barrier5);
				manager.addBarrier(barrier6);
				manager.addBarrier(barrier7);
				manager.addChaser(chaser);
				manager.addBouncer(bouncer);
				manager.addBouncer(bouncer1);
				manager.addBouncer(bouncer2);
				manager.addBouncer(bouncer3);
				manager.addBouncer(bouncer4);
				managerEnemy = new ObjectManagerChaser(chaser);
				currentState = MENU_STATE;
			} else if (currentState == MENU_STATE) {
				currentState = GAME_STATE;
			} else if (currentState == GAME_STATE) {
				currentState = END_STATE;
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (runner.x + runner.speed <= 795) {
				runner.x = runner.x + runner.speed;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (runner.x - runner.speed >= 0) {
				runner.x = runner.x - runner.speed;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (runner.y + runner.speed <= 270) {
				runner.y = runner.y + runner.speed;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (runner.y - runner.speed >= 0) {
				runner.y = runner.y - runner.speed;
			}
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (currentState == MENU_STATE) {

			updateMenuState();

		} else if (currentState == GAME_STATE) {

			updateGameState();

		} else if (currentState == END_STATE) {

			updateEndState();

		}
		repaint();
	}

	public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
		// Get the FontMetrics
		FontMetrics metrics = g.getFontMetrics(font);
		// Determine the X coordinate for the text
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		// Determine the Y coordinate for the text (note we add the ascent, as in java
		// 2d 0 is top of the screen)
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		// Set the font
		g.setFont(font);
		// Draw the String
		g.drawString(text, x, y);
	}
}
