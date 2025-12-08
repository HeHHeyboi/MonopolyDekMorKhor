package app.monopoly2;

import javafx.scene.shape.Rectangle;

public class PropertyTile extends Tile {
	final int maxLevel = 3;
	int price = 0;
	int owner = 0;
	int level = 1;
	boolean isSpecial = false;
	Rectangle rect;

	public PropertyTile() {
	}

	public PropertyTile(int price, double x, double y,
			double width, double height) {
		super(x, y, width, height);
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("Price: %d, Rect: %s,Pos: %f,%f ,Size: %f,%f, Special: %b",
				this.price, this.rect, this.x, this.y, this.width, this.height, this.isSpecial);
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isSpecial() {
		return isSpecial;
	}

	public void setSpecial(boolean isSpecial) {
		this.isSpecial = isSpecial;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

}
