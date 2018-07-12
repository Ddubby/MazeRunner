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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
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
	int FINAL_STATE = -1;
	int currentState = MENU_STATE;
	int chaserDelay = 100;
	Font titleFont = new Font("Times New Roman", Font.BOLD, 48);
	Font enterFont = new Font("Times New Roman", Font.BOLD, 24);
	Font instructionFont = new Font("Times New Roman", Font.BOLD, 20);
	static Runner runner = new Runner(50, 220, 10, 10);
	Chaser chaser = new Chaser(50, 200, 10, 10);
	FinishLine finish = new FinishLine(790, 0, 10, 10);
	FinishLine finish2 = new FinishLine(0, 0, 10, 10);
	Blank blank = new Blank(50, 200, 10, 10);
	Blank blank2 = new Blank(10, 200, 10, 10);
	Blank blank3 = new Blank(30, 200, 10, 10);
	Teleport teleport = new Teleport(50, 230, 10, 10);
	ObjectManager manager = new ObjectManager(runner);
	ArrayList<GameObject> trackers = new ArrayList<GameObject>();
	Timer timer = new Timer(1000 / 60, this);
	Timer chaserTimer = new Timer(150, this);
	BufferedImage smileImg;
	public GamePanel() {
		makeBarriers();
		makeBouncers();
		makeProjectiles();
		manager.addChaser(chaser);
		manager.addTeleport(teleport);
		manager.addFinishLine(finish);
		manager.addFinishLine(finish2);
		 try {

	           smileImg = ImageIO.read(this.getClass().getResourceAsStream("smileImg.png"));

	    } catch (IOException e) {

	            // TODO Auto-generated catch block

	            e.printStackTrace();

	    }
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
		if (currentState == GAME_STATE) {
			finish.draw(g);
		} else if (currentState == GAME_STATE2) {
			finish2.draw(g);
		} else if (currentState == GAME_STATE3) {

		}
		g.setColor(Color.BLACK);
		if (currentState == GAME_STATE) {
			blank.draw(g);
		} else if (currentState == GAME_STATE2) {
			blank2.draw(g);
		} else if (currentState == GAME_STATE3) {
			blank3.draw(g);
		}
		teleport.draw(g);
		g.setColor(Color.darkGray);
		if (currentState == GAME_STATE2) {
			g.drawLine(500, 245, 500, 260);
			g.drawLine(530, 245, 530, 260);
		}
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
		g.drawString("For a challenge", 322, 250);
		g.drawString("Level 2 passed!", 323, 200);
	}

	void drawWinState3(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, MazeRunner.width, MazeRunner.height);
		Rectangle r = new Rectangle(0, 0, MazeRunner.width, MazeRunner.height);
		g.setColor(Color.RED);
		drawCenteredString(g, "Congrats!", r, titleFont);
		g.setFont(enterFont);
		g.drawString("Press Enter to Continue", 275, 225);
		g.drawString("Level 3 passed!", 323, 200);
	}

	void drawFinalState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, MazeRunner.width, MazeRunner.height);
		g.drawImage(smileImg, 200, 80, 50, 50, null);
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
			if (currentState == GAME_STATE) {
				currentState = END_STATE;
			} else if (currentState == GAME_STATE2) {
				currentState = END_STATE2;
			} else if (currentState == GAME_STATE3) {
				currentState = END_STATE3;
			}
		}
		if (runner.Wins) {
			if (currentState == GAME_STATE) {
				currentState = WIN_STATE;
			} else if (currentState == GAME_STATE2) {
				currentState = WIN_STATE2;
			} else if (currentState == GAME_STATE3) {
				currentState = WIN_STATE3;
			}
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

		} else if (currentState == GAME_STATE || currentState == GAME_STATE2 || currentState == GAME_STATE3) {

			drawGameState(g);

		} else if (currentState == END_STATE || currentState == END_STATE2 || currentState == END_STATE3) {

			drawEndState(g);

		} else if (currentState == WIN_STATE) {

			drawWinState(g);

		} else if (currentState == WIN_STATE2) {

			drawWinState2(g);

		} else if (currentState == WIN_STATE3) {

			drawWinState3(g);

		} else if (currentState == FINAL_STATE) {

			drawFinalState(g);

		}
	}

	public void makeBarriers() {
		if (MazeRunner.level == 1) {
			makeBarriers1();
		} else if (MazeRunner.level == 2) {
			makeBarriers2();
		} else if (MazeRunner.level == 3) {
			makeBarriers3();
		}
	}

	public void makeBouncers() {
		if (MazeRunner.level == 1) {
			makeBouncers1();
		} else if (MazeRunner.level == 2) {
			makeBouncers2();
		} else if (MazeRunner.level == 3) {
			makeBouncers3();
		}
	}

	public void makeProjectiles() {
		if (MazeRunner.level == 1) {
			makeProjectiles1();
		} else if (MazeRunner.level == 2) {
			makeProjectiles2();
		} else if (MazeRunner.level == 3) {
			makeProjectiles3();
		}
	}

	public void makeBarriers1() {
		manager.addBarrier(new Barrier(300, 0, 30, 200));
		manager.addBarrier(new Barrier(100, 0, 30, 250));
		manager.addBarrier(new Barrier(600, 100, 30, 200));
		manager.addBarrier(new Barrier(500, 0, 30, 150));
		manager.addBarrier(new Barrier(700, 0, 30, 250));
		manager.addBarrier(new Barrier(200, 150, 30, 150));
		manager.addBarrier(new Barrier(400, 70, 30, 225));
	}

	public void makeBouncers1() {
		manager.addBouncer(new Bouncer(130, 131, 10, 10));
		manager.addBouncer(new Bouncer(390, 209, 10, 10));
		manager.addBouncer(new Bouncer(510, 161, 10, 10));
		manager.addBouncer(new Bouncer(460, 49, 10, 10));
		manager.addBouncer(new Bouncer(620, 81, 10, 10));
	}

	public void makeProjectiles1() {
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
		manager.addBarrier(new Barrier(700, 200, 30, 110));
		manager.addBarrier(new Barrier(100, 0, 30, 90));
		manager.addBarrier(new Barrier(200, 61, 30, 89));
		manager.addBarrier(new Barrier(300, 0, 30, 90));
		manager.addBarrier(new Barrier(400, 61, 30, 89));
		manager.addBarrier(new Barrier(500, 0, 30, 90));
		manager.addBarrier(new Barrier(600, 61, 30, 89));
		manager.addBarrier(new Barrier(700, 0, 30, 139));
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

	public void makeBarriers3() {
		manager.addBarrier(new Barrier(-10, 131, 360, 40));
		manager.addBarrier(new Barrier(450, 131, 350, 40));
		manager.addBarrier(new Barrier(380, 0, 40, 100));
		manager.addBarrier(new Barrier(380, 203, 40, 100));
		manager.addBarrier(new Barrier(-10, -10, 20, 141));
		manager.addBarrier(new Barrier(-10, 171, 20, 152));
		manager.addBarrier(new Barrier(790, -10, 20, 141));
		manager.addBarrier(new Barrier(790, 171, 20, 152));
	}

	public void makeBouncers3() {
		manager.addBouncer(new Bouncer(10, 110, 10, 10));
		manager.addBouncer(new Bouncer(780, 110, 10, 10));
		manager.addBouncer(new Bouncer(10, 93, 10, 10));
		manager.addBouncer(new Bouncer(780, 93, 10, 10));
		manager.addBouncer(new Bouncer(10, 183, 10, 10));
		manager.addBouncer(new Bouncer(780, 183, 10, 10));
		manager.addBouncer(new Bouncer(10, 200, 10, 10));
		manager.addBouncer(new Bouncer(780, 200, 10, 10));
	}

	public void makeProjectiles3() {
		manager.addProjectile(new Projectile(297, 0, 10, 10));
		manager.addLeftProjectile(new LeftProjectile(497, 0, 10, 10));
		manager.addUpProjectile(new UpProjectile(50, 121, 10, 10));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == MENU_STATE) {
				currentState = GAME_STATE;
			} else if (currentState == END_STATE || currentState == END_STATE2 || currentState == END_STATE3) {
				int x = 0;
				if (currentState == END_STATE) {
					x = 50;
				} else if (currentState == END_STATE2) {
					x = 10;
				} else if (currentState == END_STATE3) {
					x = 30;
				}
				runner = new Runner(x, 220, 10, 10);
				chaser = new Chaser(x, 200, 10, 10);
				manager = new ObjectManager(runner);
				makeBouncers();
				makeBarriers();
				makeProjectiles();
				manager.addChaser(chaser);
				manager.addTeleport(new Teleport(x, 230, 10, 10));
				trackers = new ArrayList<GameObject>();
				if (currentState == END_STATE) {
					manager.addFinishLine(new FinishLine(790, 0, 10, 10));
					chaserDelay = 100;
					currentState = GAME_STATE;
				} else if (currentState == END_STATE2) {
					manager.addFinishLine(new FinishLine(0, 0, 10, 10));
					chaserDelay = 150;
					currentState = GAME_STATE2;
				} else if (currentState == END_STATE3) {
					manager.addFinishLine(new FinishLine(0, 0, 10, 10));
					chaserDelay = 50;
					currentState = GAME_STATE3;
				}
			} else if (currentState == WIN_STATE || currentState == WIN_STATE2) {
				int x;
				if (currentState == WIN_STATE) {
					MazeRunner.level = 2;
					x = 10;
				} else {
					MazeRunner.level = 3;
					x = 30;
				}
				runner = new Runner(x, 220, 10, 10);
				chaser = new Chaser(x, 200, 10, 10);
				manager = new ObjectManager(runner);
				makeBouncers();
				makeBarriers();
				makeProjectiles();
				manager.addChaser(chaser);
				manager.addTeleport(new Teleport(x, 230, 10, 10));
				manager.addFinishLine(new FinishLine(0, 0, 10, 10));
				trackers = new ArrayList<GameObject>();
				if (currentState == WIN_STATE) {
					chaserDelay = 150;
					currentState = GAME_STATE2;
				} else {
					chaserDelay = 75;
					currentState = GAME_STATE3;
				}
				if (currentState == GAME_STATE3) {
					//change speed
					timer.setDelay(1000 / 100);
				}
			} else if (currentState == WIN_STATE3) {
				currentState = FINAL_STATE;
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

			} else if (currentState == GAME_STATE || currentState == GAME_STATE2 || currentState == GAME_STATE3) {

				updateGameState();

			} else if (currentState == END_STATE) {

				updateEndState();

			} else if (currentState == WIN_STATE) {

				updateWinState();

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
