package app.monopoly2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Circle;

public class Player {
	private IntegerProperty money = new SimpleIntegerProperty();
	private Circle player_char;
	private int player_pos = 0;
	private int playerId;

	public Player(int money, Circle circle, int id) {
		this.money.set(money);
		this.playerId = id;
		// this.money.addListener((obs, oldvalue, newvalue) -> {
		// text.setText(newvalue.toString());
		// });
		this.player_char = circle;
	}

	public int getPlayerId() {
		return this.playerId;
	}

	public int getPlayer_pos() {
		return player_pos;
	}

	public void setPlayer_pos(int pos) {
		this.player_pos = pos;
	}

	public int getMoney() {
		return money.get();
	}

	public IntegerProperty getMoneyProperty() {
		return money;
	}

	public void setMoney(int money) {
		this.money.set(money);
	}

	public Circle getPlayer_char() {
		return player_char;
	}
}
