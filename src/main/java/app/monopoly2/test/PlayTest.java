package app.monopoly2.test;

import java.net.URL;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import app.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class PlayTest implements Initializable {
	@FXML
	Pane mainPane;
	final double xSpawn = 326;
	final double ySpawn = 230;

	enum PlayerColor {
		RED, GREEN, BLUE, YELLOW;
	}

	static EnumMap<PlayerColor, String> colorMap = new EnumMap<>(PlayerColor.class);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (colorMap.isEmpty()) {
			colorMap.put(PlayerColor.RED, "#ff0000");
			colorMap.put(PlayerColor.GREEN, "#03fc03");
			colorMap.put(PlayerColor.BLUE, "#030bfc");
			colorMap.put(PlayerColor.YELLOW, "#dbfc03");
		}
	}

	public void InitGame(int numPlayer) {
		PlayerColor color = PlayerColor.RED;
		for (int i = 0; i < numPlayer; i++) {
			color = PlayerColor.values()[i];
			Paint p = Paint.valueOf(colorMap.get(color));
			Circle c = new Circle(10, p);
			c.setLayoutX(xSpawn);
			c.setLayoutY(ySpawn);
			mainPane.getChildren().add(c);
		}
		// Circle c = new Circle(10);
		// c.setLayoutX(xSpawn);
		// c.setLayoutY(ySpawn);
	}

	public void on_back_pressed() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/StartTest.fxml"));
		try {
			Scene scene = new Scene(fxmlLoader.load());
			App.setAppScene(scene);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
