package monopoly;

import javafx.beans.property.IntegerProperty;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Circle;

public class Player {

	private final IntegerProperty money = new SimpleIntegerProperty();
	// private final IntegerProperty step = new SimpleIntegerProperty(0);
	private String name;
	private Circle circle;
	private int playerPos = 0;
	private int maxTile = 7;
	private int Double_count = 0;
	private int waitInjailed = 0;

	public Player(int money, String name) {
		this.money.set(money);
		this.name = name;

	}

	public int getMoney() {
		return money.get();
	}

	public void setMoney(int money) {
		this.money.set(money);
	}

	public IntegerProperty moneyProperty() {
		return money;
	}

	public void setCircle(Circle c) {
		this.circle = c;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int PlayerPos() {
		return this.playerPos;
	}

	public void PlayerPos(int i) {

		this.playerPos = i;
		if (playerPos > maxTile) {
			this.playerPos = 0;
		} else if (playerPos < 0) {
			this.playerPos = maxTile;
		}
	}

	public boolean CheckDouble_count() {

		if (this.Double_count >= 3) {
			this.Double_count = 0;
			return true;
		} else {
			return false;
		}
	}

	public void Inc_DoubleC() {
		this.Double_count++;
	}

	public int getDoubleCount() {
		return this.Double_count;
	}

	public void setDouble_countToZero() {
		this.Double_count = 0;
	}

	public void setMaxTile(int max) {
		this.maxTile = max;
	}

	public void setWaitinJail(int w) {
		this.waitInjailed = w;
	}

	public int getWaitInjaild() {
		return this.waitInjailed;
	}
}
