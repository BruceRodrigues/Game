package common.exception;

import common.gamestate.enums.Scene;

public class SceneTransitionException extends Throwable {

	private static final long serialVersionUID = 1L;

	private String message;

	public SceneTransitionException(Scene to, String message) {
		this.message = message;
		this.message += " [Estado Destino = " + to.toString() + " ]";
	}

	public SceneTransitionException(Scene from, Scene to, String message) {
		this.message = message;
		this.message += " [Estado Origem = " + from.toString()
				+ " Estado Destino = " + to.toString() + " ]";
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
