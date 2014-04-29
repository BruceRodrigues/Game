package common.gamestate;

import java.awt.Graphics2D;
import java.util.HashMap;

import common.BaseCore;
import common.exception.SceneTransitionException;
import common.gamestate.enums.Scene;

/**
 * This class is responsible for manage the game's states.
 * 
 * @author brucerodrigues
 * 
 */
public class SceneManager implements BaseCore {

	private HashMap<Scene, BaseScene> gameScenes;

	private Scene currentScene;

	public SceneManager() {
		this.gameScenes = new HashMap<Scene, BaseScene>();
	}

	public void addState(Scene scene, BaseScene gameScene) {
		this.gameScenes.put(scene, gameScene);
		if (this.currentScene == null) {
			this.currentScene = scene;
		}
	}

	private void loadScene() {
		BaseScene gameScene = this.gameScenes.get(this.currentScene);
		if (gameScene != null) {
			// instance of
		}
	}

	private void unloadScene() {
		this.gameScenes.put(this.currentScene, null);
	}

	public void setScene(Scene current) throws SceneTransitionException {
		if (!this.gameScenes.containsKey(current)) {
			throw new SceneTransitionException(current,
					"Não é possível fazer a transição para um estado ainda não adicionado.");
		}
		this.unloadScene();
		this.currentScene = current;
		this.loadScene();
	}

	@Override
	public void start() {
		this.gameScenes.get(this.currentScene).start();
	}

	@Override
	public boolean update() {
		return this.gameScenes.get(this.currentScene).update();
	}

	@Override
	public void draw(Graphics2D graphics) {
		this.gameScenes.get(this.currentScene).draw(graphics);
	}

	public void keyPressed(int keyCode) {
		this.gameScenes.get(this.currentScene).keyPressed(keyCode);
	}

	public void keyReleased(int keyCode) {
		this.gameScenes.get(this.currentScene).keyReleased(keyCode);
	}

}
