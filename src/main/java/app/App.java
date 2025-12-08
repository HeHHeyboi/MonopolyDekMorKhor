package app;

import java.io.IOException;

import app.monopoly2.test.StartTest;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

	private static Scene scene;

	private static Stage stage;

	@Override
	public void start(Stage s) throws IOException {
		stage = s;
		StartScene("main", s);
	}

	public void StartScene(String title, Stage stage) throws IOException {
		switch (title) {
			case "main":
				Font.loadFont(getClass().getResourceAsStream("/retro_gaming/Retro Gaming.ttf"), 20);
				scene = new Scene(loadFXML("StartScene"), 1280, 800);
				stage.centerOnScreen();
				stage.setScene(scene);
				stage.setTitle("PlayerScene");
				stage.show();
				break;
			case "test":
				Font.loadFont(getClass().getResourceAsStream("/retro_gaming/Retro Gaming.ttf"), 20);
				scene = new Scene(loadFXML("StartTest"), 600, 400);
				StartTest.scene = scene;
				stage.centerOnScreen();
				stage.setScene(scene);
				stage.setTitle("Test");
				stage.show();
				break;
			default:
				throw new IOException("Can't find title name");
		}
	}

	public static void setScene(Scene scene) {
		stage.setScene(scene);
	}

	public static void setRoot(String fxml) throws Exception {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/app/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

}
