package common.animation;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import common.api.BaseCore;
import common.api.Position;

public class Animation implements BaseCore {

	private BufferedImage[] frames;

	private int currentFrame;

	private long startTime;

	private long delay;

	private boolean playedOnce;

	private Trigger trigger;

	private boolean initial;

	private BufferedImage[] sprites;

	private Position position;

	private int width;

	private int height;

	public Animation(Position position) {
		this.position = position;
		this.playedOnce = false;
		this.initial = false;
	}

	public Animation(Position position, boolean isInitial) {
		this(position);
		this.initial = isInitial;
	}

	public void loadSprites(BufferedImage image, int width, int height,
			int numberOfFrames) {
		this.width = width;
		this.height = height;

		this.sprites = new BufferedImage[numberOfFrames];
		for (int i = 0; i < numberOfFrames; i++) {
			this.sprites[i] = image.getSubimage(i * width, i * height, width,
					height);
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

	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		this.currentFrame = 0;
		this.startTime = System.nanoTime();
		this.playedOnce = false;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public void setFrame(int frame) {
		this.currentFrame = frame;
	}

	@Override
	public boolean update() {
		if (this.delay == -1) {
			return false;
		}

		long elapsed = (System.nanoTime() - this.startTime) / 1000000;
		if (elapsed > this.delay) {
			this.currentFrame++;
			this.startTime = System.nanoTime();
		}

		if (this.currentFrame == this.frames.length) {
			this.currentFrame = 0;
			this.playedOnce = true;
		}
		return true;
	}

	public int getFrame() {
		return this.currentFrame;
	}

	public BufferedImage getImage() {
		return this.frames[this.currentFrame];
	}

	public boolean hasPlayedOnce() {
		return this.playedOnce;
	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.drawImage(this.sprites[this.currentFrame],
				this.position.getX() - this.width / 2, this.position.getY()
						- this.height / 2, null);
	}

	@Override
	public void start() {
	}
}
