package app.monopoly.test;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class StartTest {
	@FXML
	Text displayText;

	public void on_2playerPressed() {
		System.out.println("2Player");
	}

	public void on_3playerPressed() {
		System.out.println("3Player");
	}

	public void on_4playerPressed() {
		System.out.println("4Player");
	}
}
