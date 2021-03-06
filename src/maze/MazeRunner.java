package maze;

import javax.swing.JFrame;

public class MazeRunner {
	static int width = 800;
	static int height = 303;
	static int level = 1;
	GamePanel panel = new GamePanel();
	JFrame frame = new JFrame();

	public static void main(String[] args) {
		MazeRunner a = new MazeRunner();
		a.setup();
	}

	public MazeRunner() {

	}

	void setup() {
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(width, height);
		frame.addKeyListener(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.startGame();
	}
}
