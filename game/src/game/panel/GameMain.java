package game.panel;

import javax.swing.JFrame;

public class GameMain {

	public static void main(String[] args) {
		JFrame window = new JFrame("Game Title");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}
}
