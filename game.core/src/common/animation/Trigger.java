package common.animation;

public abstract class Trigger {

	protected Parameter trigger;

	public Trigger(Parameter trigger) {
		this.trigger = trigger;
	}

	public boolean isTriggered() {
		return this.trigger.isTriggered();
	}

	public void resetTrigger() {
		this.trigger.resetTrigger();
	}

	public abstract void onTrigger();

	public static class Parameter {

		private Boolean trigger = false;

		public void setTrigger() {
			this.trigger = !this.trigger;
		}

		protected boolean isTriggered() {
			return this.trigger;
		}

		protected void resetTrigger() {
			this.trigger = Boolean.FALSE;
		}

	}

}
