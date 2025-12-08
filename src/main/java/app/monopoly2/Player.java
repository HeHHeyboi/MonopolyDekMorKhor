package app.monopoly2;

import javafx.scene.shape.Circle;

public class Player {
	private int money;
	private Circle player_char;
	private int player_pos = 0;

	public Player(int money, Circle circle) {
		this.money = money;
		this.player_char = circle;
	}

	public int getPlayer_pos() {
		return player_pos;
	}

	public void setPlayer_pos(int pos) {
		this.player_pos = pos;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Circle getPlayer_char() {
		return player_char;
	}
}
