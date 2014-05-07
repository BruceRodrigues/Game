package game.scenes;

import game.object.player.Player;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import common.gamescene.BaseScene;
import common.gamescene.SceneManager;

public class ForestScene extends BaseScene {

	private Player player;

	public ForestScene(SceneManager manager) {
		super(manager);
	}

	@Override
	public void start() {
		this.player = new Player();
	}

	@Override
	public boolean update() {
		this.player.update();
		return true;
	}

	@Override
	public void drawScene(Graphics2D graphics) {
		this.player.draw(graphics);
	}

	@Override
	public void keyPressed(int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_LEFT:
			this.player.run(true);
			break;
		default:
			break;
		}

	}

	@Override
	public void keyReleased(int keyCode) {

	}

}
