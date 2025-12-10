package app.monopoly2;

public abstract class Tile {
	protected double x, y, width, height;

	public Tile() {
	}

	public Tile(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean isInside(double pX, double pY) {
		if (pX < this.x) {
			return false;
		}
		if (pX > this.x + this.width) {
			return false;
		}
		if (pY < this.y) {
			return false;
		}
		if (pY > this.y + height) {
			return false;
		}

		return true;
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
