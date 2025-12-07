package app.monopoly2.test;

import java.util.Vector;

import app.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class PlayTest {
	@FXML
	Pane mainPane;
	final double xSpawn = 326;
	final double ySpawn = 230;

	public void InitGame(int numPlayer) {
		System.out.println(numPlayer);
		Circle c = new Circle(10);
		c.setLayoutX(xSpawn);
		c.setLayoutY(ySpawn);
		mainPane.getChildren().add(c);
	}

	public void on_back_pressed() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/StartTest.fxml"));
		try {
			Scene scene = new Scene(fxmlLoader.load());
			App.setScene(scene);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
