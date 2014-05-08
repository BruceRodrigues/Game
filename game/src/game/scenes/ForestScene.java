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
		case KeyEvent.VK_A:
			if (this.player.isFacingRight()) {
				this.player.setFacingRight(false);
			}
			this.player.triggerRun(true);
			break;
		case KeyEvent.VK_D:
			if (!this.player.isFacingRight()) {
				this.player.setFacingRight(true);
			}
			this.player.triggerRun(true);
		default:
			break;
		}

	}

	@Override
	public void keyReleased(int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_A:
			System.out.print("A");
			this.player.triggerRun(false);
			break;
		case KeyEvent.VK_D:
			System.out.print("D");
			this.player.triggerRun(false);
		default:
			break;
		}
	}

}
