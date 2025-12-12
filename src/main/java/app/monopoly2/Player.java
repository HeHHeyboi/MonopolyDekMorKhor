package app.monopoly2;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Circle;

public class Player {
	private IntegerProperty money = new SimpleIntegerProperty();
	private Circle player_char;
	private int player_pos = 0;
	int id;

	enum PlayerState {
		Normal,
		WaitBus,
		InJailed,
		SelectTile,
		WaitForDecision,
		MoveByBus,
		MoveByRandom,
		ArriveByBus,
		ArriveByRandom,
		ArriveNormal,
		EndTurn,
	}

	String name;
	int maxTile = 0;
	PlayerState state = PlayerState.Normal;
	PlayerState prev_state = PlayerState.Normal;
	boolean isBankrupt = false;
	int waitInJail = 0;
	ArrayList<PropertyTile> ownedProperty = new ArrayList<>();

	public Player(String name, int money, Circle circle, int id) {
		this.money.set(money);
		this.id = id;
		this.name = name;
		// this.money.addListener((obs, oldvalue, newvalue) -> {
		// text.setText(newvalue.toString());
		// });
		this.player_char = circle;
	}

	public void setMaxTile(int maxTile) {
		this.maxTile = maxTile - 1;
	}

	public int getPlayerId() {
		return this.id;
	}

	public int getPlayerPos() {
		return player_pos;
	}

	public void setPlayerPos(int pos) {
		player_pos = pos;
		if (player_pos > maxTile) {
			player_pos = 0;
		} else if (player_pos < 0) {
			player_pos = maxTile;
		}
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

	public void increaseMoney(int money) {
		this.money.set(this.money.get() + money);
	}

	public void decreaseMoney(int money) {
		this.money.set(this.money.get() - money);
	}

	public Circle getPlayer_char() {
		return player_char;
	}
}
