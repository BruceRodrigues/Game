package common.animation;

import java.awt.Graphics2D;
import java.util.HashMap;

import common.api.BaseCore;

public class Animator implements BaseCore {

	public enum BaseAnimation {
	}

	private HashMap<BaseAnimation, Animation> animations;

	private Animation currentAnimation;

	public Animator() {
		this.animations = new HashMap<BaseAnimation, Animation>();
	}

	public void addAnimation(BaseAnimation baseAnimation, Animation animation) {
		this.animations.put(baseAnimation, animation);
	}

	public void play(BaseAnimation baseAnimation) {
		if (this.animations.containsKey(baseAnimation)) {
			this.currentAnimation = this.animations.get(baseAnimation);
		}
	}

	@Override
	public boolean update() {
		this.currentAnimation.update();
		for (Animation animation : this.animations.values()) {
			if (animation.getTrigger().isTriggered()) {
				animation.getTrigger().onTrigger();
				animation.getTrigger().resetTrigger();
			}
		}
		return true;
	}

	@Override
	public void draw(Graphics2D graphics) {
		this.currentAnimation.draw(graphics);
	}

	@Override
	public void start() {
	}
}
