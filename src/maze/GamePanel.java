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
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	int MENU_STATE = 0;
	int GAME_STATE = 1;
	int GAME_STATE2 = 11;
	int GAME_STATE3 = 21;
	int END_STATE = 2;
	int END_STATE2 = 12;
	int END_STATE3 = 22;
	int WIN_STATE = 3;
	int WIN_STATE2 = 13;
	int WIN_STATE3 = 23;
	int currentState = MENU_STATE;
	int chaserDelay = 100;
	Font titleFont = new Font("Times New Roman", Font.BOLD, 48);
	Font enterFont = new Font("Times New Roman", Font.BOLD, 24);
	Font instructionFont = new Font("Times New Roman", Font.BOLD, 20);
	static Runner runner = new Runner(50, 220, 10, 10);
	Chaser chaser = new Chaser(50, 210, 10, 10);
	FinishLine finish = new FinishLine(790, 0, 10, 10);
	FinishLine finish2 = new FinishLine(0, 0, 10, 10);
	Teleport teleport = new Teleport(50, 210, 10, 10);
	ObjectManager manager = new ObjectManager(runner);
	ArrayList<GameObject> trackers = new ArrayList<GameObject>();
	Timer timer = new Timer(1000 / 60, this);
	Timer chaserTimer = new Timer(150, this);

	public GamePanel() {
		makeBarriers();
		makeBouncers();
		makeProjectiles();
		manager.addChaser(chaser);
		manager.addTeleport(teleport);
		manager.addFinishLine(finish);
		manager.addFinishLine(finish2);
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
		g.drawString("Only rule is to survive", 298, 250);
		g.drawString("Press Enter to begin", 309, 275);
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, MazeRunner.width, MazeRunner.height);
		manager.draw(g);
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
		g.setColor(Color.RED);
		runner.draw(g);
		g.setColor(Color.YELLOW);
		chaser.draw(g);
		g.setColor(Color.GREEN);
		g.setColor(Color.BLUE);
		g.setColor(Color.YELLOW);
		finish2.draw(g);
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

	void drawEndState2(Graphics g) {
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
		drawCenteredString(g, "Congrats!", r, titleFont);
		g.setFont(enterFont);
		g.drawString("Press Enter to Continue", 275, 225);
		g.drawString("Level 1 passed!", 323, 200);
	}

	void drawWinState2(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, MazeRunner.width, MazeRunner.height);
		Rectangle r = new Rectangle(0, 0, MazeRunner.width, MazeRunner.height);
		g.setColor(Color.RED);
		drawCenteredString(g, "Congrats!", r, titleFont);
		g.setFont(enterFont);
		g.drawString("Press Enter to Continue", 275, 225);
		g.drawString("Level 2 passed!", 323, 200);
	}

	void updateMenuState() {

	}

	void updateGameState() {
		manager.update();
		manager.checkCollision();
		manager.checkWin();
		manager.purgeObjects();
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
		manager.checkBouncerCollision();
		if (!runner.isAlive) {
			currentState = END_STATE2;
		}
		if (runner.Wins) {
			currentState = WIN_STATE2;
		}
	}

	void updateEndState() {

	}

	void updateWinState() {

	}

	void updateWinState2() {

	}

	void startGame() {
		timer.start();
		chaserTimer.start();
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

		} else if (currentState == END_STATE2) {

			drawEndState2(g);

		} else if (currentState == WIN_STATE2) {

			drawWinState2(g);

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

	public void makeBarriers2() {
		manager.addBarrier(new Barrier(-5, 151, 735, 10));
		manager.addBarrier(new Barrier(100, 200, 30, 90));
		manager.addBarrier(new Barrier(200, 161, 30, 89));
		manager.addBarrier(new Barrier(300, 200, 30, 90));
		manager.addBarrier(new Barrier(400, 161, 30, 89));
		manager.addBarrier(new Barrier(500, 200, 30, 45));
		manager.addBarrier(new Barrier(500, 260, 30, 45));
		manager.addBarrier(new Barrier(600, 161, 30, 89));
		manager.addBarrier(new Barrier(700, 200, 30, 90));
		manager.addBarrier(new Barrier(100, 0, 30, 90));
		manager.addBarrier(new Barrier(200, 61, 30, 89));
		manager.addBarrier(new Barrier(300, 0, 30, 90));
		manager.addBarrier(new Barrier(400, 61, 30, 89));
		manager.addBarrier(new Barrier(500, 0, 30, 90));
		manager.addBarrier(new Barrier(600, 61, 30, 89));
		manager.addBarrier(new Barrier(700, 0, 30, 90));
	}

	public void makeBouncers2() {
		manager.addBouncer(new Bouncer(140, 259, 10, 10));
		manager.addBouncer(new Bouncer(240, 181, 10, 10));
		manager.addBouncer(new Bouncer(340, 259, 10, 10));
		manager.addBouncer(new Bouncer(440, 181, 10, 10));
		manager.addBouncer(new Bouncer(540, 259, 10, 10));
		manager.addBouncer(new Bouncer(140, 41, 10, 10));
		manager.addBouncer(new Bouncer(240, 99, 10, 10));
		manager.addBouncer(new Bouncer(340, 41, 10, 10));
		manager.addBouncer(new Bouncer(440, 99, 10, 10));
		manager.addBouncer(new Bouncer(540, 41, 10, 10));
	}

	public void makeProjectiles2() {
		manager.addProjectile(new Projectile(120, 161, 10, 10));
		manager.addLeftProjectile(new LeftProjectile(505, 161, 10, 10));
		manager.addUpProjectile(new UpProjectile(120, 141, 10, 10));
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
				chaser = new Chaser(50, 210, 10, 10);
				manager = new ObjectManager(runner);
				makeBouncers();
				makeBarriers();
				makeProjectiles();
				manager.addChaser(chaser);
				manager.addTeleport(new Teleport(50, 210, 10, 10));
				manager.addFinishLine(new FinishLine(790, 0, 10, 10));
				trackers = new ArrayList<GameObject>();
				chaserDelay = 100;
				currentState = GAME_STATE;
			} else if (currentState == MENU_STATE) {
				currentState = GAME_STATE;
			} else if (currentState == GAME_STATE) {
				currentState = END_STATE;
			} else if (currentState == WIN_STATE) {
				MazeRunner.level = 2;
				runner = new Runner(10, 220, 10, 10);
				chaser = new Chaser(10, 210, 10, 10);
				manager = new ObjectManager(runner);
				makeBouncers2();
				makeBarriers2();
				makeProjectiles2();
				manager.addChaser(chaser);
				manager.addTeleport(new Teleport(10, 210, 10, 10));
				manager.addFinishLine(new FinishLine(0, 0, 10, 10));
				trackers = new ArrayList<GameObject>();
				chaserDelay = 200;
				currentState = GAME_STATE2;
			} else if (currentState == END_STATE2) {
				runner = new Runner(10, 220, 10, 10);
				chaser = new Chaser(10, 210, 10, 10);
				manager = new ObjectManager(runner);
				makeBouncers2();
				makeBarriers2();
				makeProjectiles2();
				manager.addChaser(chaser);
				manager.addTeleport(new Teleport(10, 210, 10, 10));
				manager.addFinishLine(new FinishLine(0, 0, 10, 10));
				trackers = new ArrayList<GameObject>();
				chaserDelay = 200;
				currentState = GAME_STATE2;
			} else if (currentState == WIN_STATE2) {
				currentState = GAME_STATE3;
			} else if (currentState == END_STATE3) {
				currentState = GAME_STATE3;
			} else if (currentState == WIN_STATE3) {

			}

		}
		if (currentState == GAME_STATE || currentState == GAME_STATE2 || currentState == GAME_STATE3) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (runner.x + runner.speed <= 795) {
					runner.x = runner.x + runner.speed;
					trackers.add(new GameObject(runner.x, runner.y, 0, 0));
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (runner.x - runner.speed >= 0) {
					runner.x = runner.x - runner.speed;
					trackers.add(new GameObject(runner.x, runner.y, 0, 0));
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (runner.y + runner.speed <= 270) {
					runner.y = runner.y + runner.speed;
					trackers.add(new GameObject(runner.x, runner.y, 0, 0));
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (runner.y - runner.speed >= 0) {
					runner.y = runner.y - runner.speed;
					trackers.add(new GameObject(runner.x, runner.y, 0, 0));
				}
			}

			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void updateChaser() {
		if (!trackers.isEmpty()) {
			GameObject pos = trackers.remove(0);
			chaser.x = pos.x;
			chaser.y = pos.y;
		} else {

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == chaserTimer) {
			if (chaserDelay > 0) {
				chaserDelay--;
			} else {
				updateChaser();
			}
		}
		if (e.getSource() == timer) {
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

			} else if (currentState == WIN_STATE2) {

			}
			repaint();
		}
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
