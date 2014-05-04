package common.gamescene;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import common.BaseCore;
import common.exception.SceneTransitionException;

/**
 * This class is responsible for manage the game's states.
 * 
 * @author brucerodrigues
 * 
 */
public class SceneManager implements BaseCore {

	private List<BaseScene> gameScenes;

	private Integer currentSceneIndex;

	public SceneManager() {
		this.gameScenes = new ArrayList<BaseScene>();
	}

	public void addScene(BaseScene gameScene) {
		this.gameScenes.add(gameScene);
		if (this.currentSceneIndex == null) {
			this.currentSceneIndex = this.gameScenes.size() - 1;
		}
	}

	private void loadScene() {
		BaseScene gameScene = this.gameScenes.get(this.currentSceneIndex);
		if (gameScene != null) {
			gameScene.start();
		}
	}

	private void unloadScene() {
		this.gameScenes.remove(this.currentSceneIndex);
	}

	public void setScene(BaseScene current) throws SceneTransitionException {
		if (!this.gameScenes.contains(current)) {
			throw new SceneTransitionException(current.toString(),
					"Não é possível fazer a transição para um estado ainda não adicionado.");
		}
		this.unloadScene();
		this.currentSceneIndex = this.gameScenes.indexOf(current);
		this.loadScene();
	}

	@Override
	public void start() {
		this.gameScenes.get(this.currentSceneIndex).start();
	}

	@Override
	public boolean update() {
		return this.gameScenes.get(this.currentSceneIndex).update();
	}

	@Override
	public void draw(Graphics2D graphics) {
		this.gameScenes.get(this.currentSceneIndex).draw(graphics);
	}

	public void keyPressed(int keyCode) {
		this.gameScenes.get(this.currentSceneIndex).keyPressed(keyCode);
	}

	public void keyReleased(int keyCode) {
		this.gameScenes.get(this.currentSceneIndex).keyReleased(keyCode);
	}

}
