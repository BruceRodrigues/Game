package game.panel;

import game.scenes.MenuScene;

import common.gamepanel.BaseGamePanel;

public class GamePanel extends BaseGamePanel {

	private static final long serialVersionUID = 1L;

	@Override
	public void addAllScenes() {
		this.manager.addScene(new MenuScene(this.manager));
	}

}
