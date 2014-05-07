package game.panel;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameMain {

	public static void main(String[] args) {
		JFrame window = new JFrame("Game Title");
		GamePanel gamePanel = new GamePanel();
		gamePanel.WIDTH = 320;
		gamePanel.HEIGHT = 240;
		gamePanel.setPreferredSize(new Dimension(gamePanel.WIDTH,
				gamePanel.HEIGHT));
		window.setContentPane(gamePanel);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}
}
