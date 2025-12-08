package app.monopoly2;

public abstract class Tile {
	protected int id;
	protected double x, y, width, height;

	public Tile() {
	}

	public Tile(int id, double x, double y, double width, double height) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getID() {
		return this.id;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
}
