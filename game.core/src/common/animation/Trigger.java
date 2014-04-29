package common.animation;

public abstract class Trigger {

	private Boolean trigger;

	public Trigger(Boolean trigger) {
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
