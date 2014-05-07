package common.image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import common.BaseCore;
import common.Vector;
import common.gamepanel.BaseGamePanel;

public class Background implements BaseCore {

	private BufferedImage image;

	private Vector position;

	private Vector vector;

	public Background(BufferedImage image) {
		this.image = image;
		this.position = new Vector();
		this.vector = new Vector();
	}

	public Background(BufferedImage image, Vector position) {
		this(image);
		this.position.setX(position.getX());
		this.position.setY(position.getY());
	}

	public Background(BufferedImage image, Vector position, Vector vector) {
		this(image, position);
		this.vector.setX(vector.getX());
		this.vector.setY(vector.getY());

	}

	@Override
	public void start() {
	}

	public void setVector(Vector vector) {
		this.vector.setX(vector.getX());
		this.vector.setY(vector.getY());
	}

	@Override
	public boolean update() {
		this.position.add(this.vector);
		return true;
	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.drawImage(this.image, (int) this.position.getX(),
				(int) this.position.getY(), null);
		if (this.position.getX() < 0) {
			graphics.drawImage(this.image, (int) this.position.getX()
					+ BaseGamePanel.WIDTH, (int) this.position.getY(), null);
		} else if (this.position.getX() > 0) {
			graphics.drawImage(this.image, (int) this.position.getX()
					- BaseGamePanel.WIDTH, (int) this.position.getY(), null);
		}
	}
}
