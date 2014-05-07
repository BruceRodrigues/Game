package game.object.player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import common.Vector;
import common.animation.Animation;
import common.animation.Animator;
import common.animation.BaseAnimation;
import common.animation.Trigger;
import common.object.GameObject;

public class Player extends GameObject {

	private Animator animator;

	private Animation animationIdle;
	private final String idleURL = "/resources/gameobjects/player/idle.png";

	private Animation animationRun;
	private final String runURL = "/resources/gameobjects/player/run.png";

	private Trigger.Parameter running;

	private Trigger.Parameter idle;

	BaseAnimation run;

	@Override
	public void start() {
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
			this.animationIdle.addTrigger(new Trigger(this.running) {
				@Override
				public void onTrigger() {
					Player.this.animator.play(Player.this.run);
				}
			});

			BaseAnimation idle = new BaseAnimation(false) {

				@Override
				public void onTrigger() {
				}
			};

			this.animator.addAnimation(idle, this.animationIdle);
			this.animator.play(idle);
		}

		{
			this.animationRun = new Animation(this.position);
			this.animationRun.loadSprites(this.getImageFromUrl(this.runURL),
					55, 60, 8);
			this.animationRun.setDelay(200);
			this.animationRun.addTrigger(new Trigger(this.running) {
				@Override
				public void onTrigger() {
					Player.this.animator.play(Player.this.run);
				}
			});

			this.run = new BaseAnimation(false) {

				@Override
				public void onTrigger() {
				}
			};
			this.animator.addAnimation(this.run, this.animationRun);

		}

	}

	@Override
	public boolean update() {
		this.animator.update();
		return true;
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

	public void run(boolean run) {
		this.running.setTrigger();
	}

}
