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
	Runner runner = new Runner(50, 225, 10, 10);
	Chaser follower = new Chaser(70, 200, 10, 10);
	ObjectManager manager = new ObjectManager(runner);
	Timer timer = new Timer(1000/60, this);
	public GamePanel() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, MazeRunner.width, MazeRunner.height);
		Rectangle r = new Rectangle(0, 0, MazeRunner.width, MazeRunner.height);
		g.setColor(Color.RED);
		drawCenteredString(g, "Maze Runner", r, titleFont);
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, MazeRunner.width, MazeRunner.height);
		g.setColor(Color.RED);
		runner.draw(g);
		g.setColor(Color.YELLOW);
		follower.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, MazeRunner.width, MazeRunner.height);
	}
	void updateMenuState() {
	
	}
	void updateGameState() {
		
	}
	void updateEndState() {
		
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
				currentState = MENU_STATE;
			}
			else if (currentState == MENU_STATE) {
				currentState = GAME_STATE;
		}
			else if(currentState == GAME_STATE) {
				currentState = END_STATE;
				}

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (runner.x+runner.speed<=794) {
				runner.x=runner.x+runner.speed;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (runner.x-runner.speed>=0) {
				runner.x=runner.x-runner.speed;
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (runner.y+runner.speed<=270) {
				runner.y=runner.y+runner.speed;	
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (runner.y-runner.speed>=0) {
				runner.y=runner.y-runner.speed;	
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
