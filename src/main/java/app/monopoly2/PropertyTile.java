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
	int startPrice;
	int startPaid;
	int upgradePrice;
	int upgradePaid;

	enum PropertyState {
		NotOwn, Owned, MaxLevel
	}

	PropertyState state = PropertyState.NotOwn;

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

	public void upgrade() {
		level += 1;
		state = (level >= 3) ? PropertyState.MaxLevel : PropertyState.Owned;
		price += price * 0.2;
		paid = (int) (price * 0.6);
		upgradePrice = price + (int) (price * 0.2);
		upgradePaid = (int) (upgradePrice * 0.6);
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
		owner.addListener((obs, oldval, newval) -> {
			String color = "#ffffff";
			int ownerIndex = newval.intValue();
			if (ownerIndex > -1) {
				// WARN: Need to handle this case
				PlayerColor c = PlayerColor.values()[ownerIndex];
				color = Monopoly.colorMap.get(c);
			}
			rect.setFill(Paint.valueOf(color));
		});
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
		this.startPrice = price;
		this.upgradePrice = price + (int) (price * 0.2);
	}

	public void setPaid(int paid) {
		this.paid = paid;
		this.startPaid = paid;
		upgradePaid = (int) (upgradePrice * 0.6);
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

	public void resetProperty() {
		this.setOwner(-1);
		this.level = 1;
		this.state = PropertyState.NotOwn;
		this.setPrice(startPrice);
		this.setPaid(startPaid);
	}

}
