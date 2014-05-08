package common.animation;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import common.BaseCore;

public class Animator implements BaseCore {

	private List<Animation> animations;

	private Animation currentAnimation;

	public Animator() {
		this.animations = new ArrayList<Animation>();
	}

	public void addAnimation(Animation animation) {
		this.animations.add(animation);
		if (this.currentAnimation == null) {
			this.currentAnimation = animation;
		}
		if (animation.isInitial()) {
			this.currentAnimation = animation;
		}
	}

	@Override
	public void draw(Graphics2D graphics) {
		this.currentAnimation.draw(graphics);
	}

	public void play(Animation animation) {
		if (this.animations.contains(animation)
				&& this.currentAnimation != animation) {
			this.currentAnimation.stop();
			this.currentAnimation = this.animations.get(this.animations
					.indexOf(animation));
			this.currentAnimation.play();
		}
	}

	@Override
	public void start() {
	}

	@Override
	public boolean update() {
		this.currentAnimation.update();
		for (Animation animation : this.animations) {
			if (animation.getTrigger() != null
					&& animation.getTrigger().isTriggered()) {
				animation.getTrigger().onTrigger();
				animation.getTrigger().resetTrigger();
			}
		}
		return true;
	}
}
