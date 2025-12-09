package app.monopoly2;

import app.monopoly2.Monopoly.PlayerColor;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class PropertyTile extends Tile {
	final int maxLevel = 3;
	int price = 0;
	int paid = 0;
	IntegerProperty owner = new SimpleIntegerProperty(-1);
	int level = 1;
	boolean isSpecial = false;
	Rectangle rect;

	public PropertyTile() {
	}

	public PropertyTile(double x, double y,
			double width, double height) {
		super(x, y, width, height);
	}

	@Override
	public String toString() {
		return String.format("Price: %d, Paid: %d, Rect: %s,Pos: %f,%f ,Size: %f,%f, Special: %b",
				this.price, this.paid, this.rect, this.x, this.y, this.width, this.height, this.isSpecial);
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
		owner.addListener((obs, oldval, newval) -> {
			String color;
			if (newval.intValue() < 0) {
				color = "#FFFFFF";
			} else {
				color = Monopoly.colorMap.get(PlayerColor.values()[newval.intValue()]);
			}
			rect.setFill(Paint.valueOf(color));
		});
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}

	public int getPaid() {
		return paid;
	}

	public int getOwner() {
		return owner.get();
	}

	public void setOwner(int owner) {
		this.owner.set(owner);
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
