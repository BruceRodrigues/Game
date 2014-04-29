package common.animation;


public abstract class BaseAnimation {

	private Boolean trigger;

	public BaseAnimation(Boolean trigger) {
		this.trigger = trigger;
	}

	public boolean isTriggered() {
		return this.trigger;
	}

	public void resetTrigger() {
		this.trigger = false;
	}

	public abstract void onTrigger();
}
