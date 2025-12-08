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

	public static Scene AppScene;

	public static Stage AppStage;

	@Override
	public void start(Stage s) throws IOException {
		AppStage = s;
		StartScene("test", s);
	}

	public void StartScene(String title, Stage stage) throws IOException {
		switch (title) {
			case "main":
				Font.loadFont(getClass().getResourceAsStream("/retro_gaming/Retro Gaming.ttf"), 20);
				AppScene = new Scene(loadFXML("StartScene"), 1280, 800);
				stage.centerOnScreen();
				stage.setScene(AppScene);
				stage.setTitle("PlayerScene");
				stage.show();
				break;
			case "test":
				Font.loadFont(getClass().getResourceAsStream("/retro_gaming/Retro Gaming.ttf"), 20);
				AppScene = new Scene(loadFXML("StartTest"), 600, 400);
				StartTest.scene = AppScene;
				stage.centerOnScreen();
				stage.setScene(AppScene);
				stage.setTitle("Test");
				stage.show();
				break;
			default:
				throw new IOException("Can't find title name");
		}
	}

	public static void setAppScene(Scene scene) {
		AppStage.setScene(scene);
	}

	public static void setRoot(String fxml) throws Exception {
		AppScene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/app/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

}
