package game.object.player;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import common.Vector;
import common.animation.Animation;
import common.animation.Animator;
import common.animation.Trigger;
import common.object.GameObject;

public class Player extends GameObject {

	private Animator animator;

	private Animation animationIdle;
	private final String idleURL = "/resources/gameobjects/player/idle.png";

	private Animation animationRun;
	private final String runURL = "/resources/gameobjects/player/run.png";

	private boolean facingRight;

	private Trigger.Parameter running;

	private Trigger.Parameter idle;

	private AffineTransform transform = AffineTransform.getScaleInstance(-1, 1);

	private final double SPEED = 3;

	@Override
	public void start() {
		this.facingRight = true;

		this.moveSpeed = this.SPEED;

		this.position = new Vector(200, 150);

		this.running = new Trigger.Parameter();
		this.idle = new Trigger.Parameter();

		this.animator = new Animator();

		// IDLE
		{
			this.animationIdle = new Animation(this.position, true);
			this.animationIdle.loadSprites(this.getImageFromUrl(this.idleURL),
					53, 56, 5);
			this.animationIdle.setDelay(100);
			this.animationIdle.addTrigger(new Trigger(this.idle) {
				@Override
				public void onTrigger() {
					Player.this.animationIdle.setFlip(!Player.this.facingRight);
					Player.this.animator.play(Player.this.animationIdle);
				}
			});

			this.animator.addAnimation(this.animationIdle);
			this.animator.play(this.animationIdle);
		}

		{
			this.animationRun = new Animation(this.position);
			this.animationRun.loadSprites(this.getImageFromUrl(this.runURL),
					55, 60, 8);
			this.animationRun.setDelay(100);
			this.animationRun.addTrigger(new Trigger(this.running) {
				@Override
				public void onTrigger() {
					Player.this.animationRun.setFlip(!Player.this.facingRight);
					Player.this.animator.play(Player.this.animationRun);
				}
			});

			this.animator.addAnimation(this.animationRun);

		}

	}

	@Override
	public boolean objectUpdate() {
		this.animator.update();

		this.run();

		return true;
	}

	private void run() {
		int mult = 0;
		if (this.facingRight) {
			mult = 1;
		} else {
			mult = -1;
		}
		this.vector.setX(this.animationRun.isPlaying() ? this.moveSpeed * mult
				: 0);
	}

	@Override
	public void draw(Graphics2D graphics) {
		this.animator.draw(graphics);
	}

	private BufferedImage getImageFromUrl(String url) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(this.getClass().getResourceAsStream(url));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public void triggerRun(boolean run) {
		if (run) {
			this.running.setTrigger();
		} else {
			this.idle.setTrigger();
		}
	}

	public boolean isFacingRight() {
		return this.facingRight;
	}

	public void setFacingRight(boolean facingRight) {
		this.facingRight = facingRight;
	}

}
