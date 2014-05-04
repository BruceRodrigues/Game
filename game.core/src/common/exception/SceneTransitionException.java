package common.exception;


public class SceneTransitionException extends Throwable {

	private static final long serialVersionUID = 1L;

	private String message;

	public SceneTransitionException(String to, String message) {
		this.message = message;
		this.message += " [Estado Destino = " + to.toString() + " ]";
	}

	public SceneTransitionException(String from, String to, String message) {
		this.message = message;
		this.message += " [Estado Origem = " + from.toString()
				+ " Estado Destino = " + to.toString() + " ]";
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
