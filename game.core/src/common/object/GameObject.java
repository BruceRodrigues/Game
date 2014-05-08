package common.object;

import java.awt.Graphics2D;

import common.BaseCore;
import common.Vector;

public abstract class GameObject implements BaseCore {

	protected int tileSize;

	protected Vector position;

	protected Vector vector;

	protected double moveSpeed;

	protected int width, height;

	public GameObject() {
		this.position = new Vector(0, 0);
		this.vector = new Vector(0, 0);
		this.start();
	}

	@Override
	public abstract void start();

	@Override
	public boolean update() {
		this.position.add(this.vector);
		return this.objectUpdate();
	};

	public abstract boolean objectUpdate();

	@Override
	public abstract void draw(Graphics2D graphics);

}
