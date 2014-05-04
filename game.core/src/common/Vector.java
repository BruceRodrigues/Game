package common;

public class Vector {

	private double x;

	private double y;

	public Vector() {

	}

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public void add(Vector vector) {
		this.x += vector.getX();
		this.y += vector.getY();
	}

	public void sub(Vector vector) {
		this.x -= vector.getX();
		this.y -= vector.getY();
	}

}
