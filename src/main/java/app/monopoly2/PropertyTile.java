package app.monopoly2;

public class PropertyTile extends Tile {
	final int maxLevel = 3;
	int price = 0;
	int owner = 0;
	int level = 1;
	boolean isSpecial = false;

	public PropertyTile() {
	}

	public PropertyTile(int price, int id, double x, double y,
			double width, double height) {
		super(id, x, y, width, height);
		this.price = price;
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

}
