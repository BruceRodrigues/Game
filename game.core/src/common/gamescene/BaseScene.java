package common.gamescene;

import java.awt.Color;
import java.awt.Graphics2D;

import common.BaseCore;
import common.gamepanel.BaseGamePanel;

public abstract class BaseScene implements BaseCore {

	protected SceneManager manager;

	public BaseScene(SceneManager manager) {
		this.manager = manager;
		this.start();
	}

	@Override
	public abstract void start();

	@Override
	public abstract boolean update();

	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, BaseGamePanel.WIDTH, BaseGamePanel.HEIGHT);
		this.drawScene(graphics);
	};

	public abstract void drawScene(Graphics2D graphics);

	public abstract void keyPressed(int keyCode);

	public abstract void keyReleased(int keyCode);

}
