package app.monopoly2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Player {
	private IntegerProperty money = new SimpleIntegerProperty();
	private Circle player_char;
	private int player_pos = 0;

	public Player(int money, Circle circle) {
		this.money.set(money);
		// this.money.addListener((obs, oldvalue, newvalue) -> {
		// text.setText(newvalue.toString());
		// });
		this.player_char = circle;
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
