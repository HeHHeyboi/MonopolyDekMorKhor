package app.monopoly2.test;

import app.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartTest {
	@FXML
	Text displayText;
	int count = 0;
	public static Scene scene;

	public void on_2playerPressed() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/PlayTest.fxml"));
		try {
			Scene scene = new Scene(fxmlLoader.load());
			App.setScene(scene);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		PlayTest controller = fxmlLoader.getController();
		try {
			controller.InitGame(2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		count += 1;
		displayText.setText("Count: " + count);
	}

	public void on_3playerPressed() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/PlayTest.fxml"));
		try {
			Scene scene = new Scene(fxmlLoader.load());
			App.setScene(scene);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		PlayTest controller = fxmlLoader.getController();
		try {
			controller.InitGame(3);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		count += 1;
		displayText.setText("Count: " + count);
	}

	public void on_4playerPressed() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/PlayTest.fxml"));
		try {
			Scene scene = new Scene(fxmlLoader.load());
			App.setScene(scene);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		PlayTest controller = fxmlLoader.getController();
		try {
			controller.InitGame(4);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		count += 1;
		displayText.setText("Count: " + count);
	}
}
