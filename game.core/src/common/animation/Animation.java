package common.animation;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import common.BaseCore;
import common.Vector;

public class Animation implements BaseCore {

	private int currentFrame;

	private long startTime;

	private long delay;

	private boolean playedOnce;

	private Trigger trigger;

	private boolean initial;

	private BufferedImage[] sprites;

	private Vector position;

	private int width;

	private int height;

	private boolean playing;

	public Animation(Vector position) {
		this.position = position;
		this.playedOnce = false;
		this.initial = false;
		this.playing = false;
		this.delay = 400;
	}

	public Animation(Vector position, boolean isInitial) {
		this(position);
		this.initial = isInitial;
	}

	public void loadSprites(BufferedImage image, int width, int height,
			int numberOfFrames) {
		this.width = width;
		this.height = height;

		this.sprites = new BufferedImage[numberOfFrames];
		for (int i = 0; i < numberOfFrames; i++) {
			this.sprites[i] = image.getSubimage(i * width, 0 * height, width,
					height);
		}
		if (this.initial) {
			this.playing = true;
		}
	}

	public void addTrigger(Trigger trigger) {
		this.trigger = trigger;
	}

	public Trigger getTrigger() {
		return this.trigger;
	}

	public boolean isInitial() {
		return this.initial;
	}

	public void play() {
		this.playing = true;
		this.startTime = System.nanoTime();
		this.playedOnce = false;
	}

	public void stop() {
		this.playing = false;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public void setFrame(int frame) {
		this.currentFrame = frame;
	}

	public boolean isPlaying() {
		return this.playing;
	}

	@Override
	public boolean update() {
		if (this.playing) {
			if (this.delay == -1) {
				return false;
			}

			long elapsed = (System.nanoTime() - this.startTime) / 1000000;
			if (elapsed > this.delay) {
				this.currentFrame++;
				this.startTime = System.nanoTime();
			}

			if (this.currentFrame == this.sprites.length) {
				this.currentFrame = 0;
				this.playedOnce = true;
			}
		}
		return true;
	}

	public int getFrame() {
		return this.currentFrame;
	}

	public BufferedImage getImage() {
		return this.sprites[this.currentFrame];
	}

	public boolean hasPlayedOnce() {
		return this.playedOnce;
	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.drawImage(this.sprites[this.currentFrame],
				(int) this.position.getX() - this.width / 2,
				(int) this.position.getY() - this.height / 2, null);
	}

	@Override
	public void start() {
	}
}
