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
	int GAME_STATE2 = 10;
	int END_STATE = 2;
	int WIN_STATE = 3;
	int currentState = MENU_STATE;
	Font titleFont = new Font("Times New Roman", Font.BOLD, 48);
	Font enterFont = new Font("Times New Roman", Font.BOLD, 24);
	Font instructionFont = new Font("Times New Roman", Font.BOLD, 20);
	static Runner runner = new Runner(50, 220, 10, 10);
	Chaser chaser = new Chaser(70, 200, 10, 10);
	FinishLine finish = new FinishLine(790, 0, 10, 10);
	Teleport teleport = new Teleport(50, 210, 10, 10);
	ObjectManager manager = new ObjectManager(runner);
	ObjectManagerChaser managerEnemy = new ObjectManagerChaser(chaser);
	Timer timer = new Timer(1000 / 60, this);

	public GamePanel() {
		makeBarriers();
		makeBouncers();
		makeProjectiles();
		manager.addChaser(chaser);
		manager.addTeleport(teleport);
		manager.addFinishLine(finish);
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, MazeRunner.width, MazeRunner.height);
		Rectangle r = new Rectangle(0, 0, MazeRunner.width, MazeRunner.height);
		g.setColor(Color.RED);
		drawCenteredString(g, "Maze Runner", r, titleFont);
		g.setFont(instructionFont);
		g.drawString("Welcome, escape the maze and you will be rewarded", 170, 200);
		g.drawString("Use arrow keys to move", 290, 225);
		g.drawString("Only rule is to survive", 299, 250);
		g.drawString("Press Enter to begin", 310, 275);
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, MazeRunner.width, MazeRunner.height);
		manager.draw(g);
		managerEnemy.draw(g);
		g.setColor(Color.RED);
		runner.draw(g);
		g.setColor(Color.YELLOW);
		chaser.draw(g);
		g.setColor(Color.GREEN);
		g.setColor(Color.BLUE);
		g.setColor(Color.YELLOW);
		finish.draw(g);
		g.setColor(Color.BLACK);
		teleport.draw(g);
		g.setColor(Color.CYAN);
	}

	void drawGameState2(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, MazeRunner.width, MazeRunner.height);
		manager.draw(g);
		managerEnemy.draw(g);
		g.setColor(Color.RED);
		runner.draw(g);
		g.setColor(Color.YELLOW);
		chaser.draw(g);
		g.setColor(Color.GREEN);
		g.setColor(Color.BLUE);
		g.setColor(Color.YELLOW);
		finish.draw(g);
		g.setColor(Color.BLACK);
		teleport.draw(g);
		g.setColor(Color.CYAN);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, MazeRunner.width, MazeRunner.height);
		Rectangle r = new Rectangle(MazeRunner.width, MazeRunner.height);
		g.setColor(Color.RED);
		drawCenteredString(g, "You Lose!", r, titleFont);
		g.setFont(enterFont);
		g.drawString("Press Enter to Retry", 295, 225);
	}

	void drawWinState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, MazeRunner.width, MazeRunner.height);
		Rectangle r = new Rectangle(0, 0, MazeRunner.width, MazeRunner.height);
		g.setColor(Color.RED);
		drawCenteredString(g, "You Win!", r, titleFont);
		g.setFont(enterFont);
		g.drawString("Press Enter to Continue", 275, 225);
		g.drawString("Level 1 passed!", 323, 200);
	}

	void updateMenuState() {

	}

	void updateGameState() {
		manager.update();
		manager.checkCollision();
		manager.checkWin();
		manager.purgeObjects();
		managerEnemy.ChaserAIRight();
		manager.checkBouncerCollision();
		if (!runner.isAlive) {
			currentState = END_STATE;
		}
		if (runner.Wins) {
			currentState = WIN_STATE;
		}
	}

	void updateGameState2() {
		manager.update();
		manager.checkCollision();
		manager.checkWin();
		manager.purgeObjects();
		managerEnemy.ChaserAIRight();
		manager.checkBouncerCollision();
		if (!runner.isAlive) {
			currentState = END_STATE;
		}
		if (runner.Wins) {
			currentState = WIN_STATE;
		}
	}

	void updateEndState() {

	}

	void updateWinState() {

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

		} else if (currentState == WIN_STATE) {

			drawWinState(g);

		} else if (currentState == GAME_STATE2) {

			drawGameState2(g);

		}

	}

	public void makeBarriers() {
		manager.addBarrier(new Barrier(300, 0, 30, 200));
		manager.addBarrier(new Barrier(100, 0, 30, 250));
		manager.addBarrier(new Barrier(600, 100, 30, 200));
		manager.addBarrier(new Barrier(500, 0, 30, 150));
		manager.addBarrier(new Barrier(700, 0, 30, 250));
		manager.addBarrier(new Barrier(200, 150, 30, 150));
		manager.addBarrier(new Barrier(400, 70, 30, 225));
	}

	public void makeBouncers() {
		manager.addBouncer(new Bouncer(130, 131, 10, 10));
		manager.addBouncer(new Bouncer(390, 209, 10, 10));
		manager.addBouncer(new Bouncer(510, 161, 10, 10));
		manager.addBouncer(new Bouncer(460, 49, 10, 10));
		manager.addBouncer(new Bouncer(620, 81, 10, 10));
	}

	public void makeProjectiles() {
		manager.addProjectile(new Projectile(133, 0, 10, 10));
		manager.addProjectile(new Projectile(390, 0, 10, 10));
		manager.addUpProjectile(new UpProjectile(430, 303, 10, 10));
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
				makeBouncers();
				makeBarriers();
				makeProjectiles();
				finish = new FinishLine(790, 0, 10, 10);
				teleport = new Teleport(50, 210, 10, 10);
				manager.addChaser(chaser);
				manager.addTeleport(teleport);
				manager.addFinishLine(finish);
				managerEnemy = new ObjectManagerChaser(chaser);
				currentState = MENU_STATE;
			} else if (currentState == MENU_STATE) {
				currentState = GAME_STATE;
			} else if (currentState == GAME_STATE) {
				currentState = END_STATE;
			} else if (currentState == WIN_STATE) {
				runner = new Runner(50, 220, 10, 10);
				manager = new ObjectManager(runner);
				chaser = new Chaser(70, 200, 10, 10);
				makeBouncers();
				makeBarriers();
				finish = new FinishLine(790, 0, 10, 10);
				teleport = new Teleport(50, 210, 10, 10);
				manager.addChaser(chaser);
				manager.addTeleport(teleport);
				manager.addFinishLine(finish);
				managerEnemy = new ObjectManagerChaser(chaser);
				currentState = GAME_STATE2;
			}

		}
		if (currentState == GAME_STATE || currentState == GAME_STATE2) {
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

		} else if (currentState == WIN_STATE) {

			updateWinState();

		} else if (currentState == GAME_STATE2) {

			updateGameState2();

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
