package common.gamestate;

import java.awt.Graphics2D;

import common.api.BaseCore;

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
	public abstract void draw(Graphics2D graphics);

	public abstract void keyPressed(int keyCode);

	public abstract void keyReleased(int keyCode);

}
