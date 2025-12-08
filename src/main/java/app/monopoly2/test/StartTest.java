package app.monopoly2.test;

import app.App;
import app.monopoly2.Monopoly;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;

public class StartTest {
	@FXML
	Text displayText;
	int count = 0;
	public static Scene scene;

	public void on_2playerPressed() {
		long start = System.nanoTime();
		Monopoly controller = loadScene();
		try {
			controller.GameInit(2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		long end = System.nanoTime();
		double ms = (end - start) / 1_000_000.0;
		System.out.println("test took: " + ms + " ms");
	}

	public void on_3playerPressed() {
		long start = System.nanoTime();

		Monopoly controller = loadScene();
		try {
			controller.GameInit(3);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		long end = System.nanoTime();
		double ms = (end - start) / 1_000_000.0;
		System.out.println("test took: " + ms + " ms");
	}

	public void on_4playerPressed() {
		long start = System.nanoTime();

		Monopoly controller = loadScene();
		try {
			controller.GameInit(4);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		long end = System.nanoTime();
		double ms = (end - start) / 1_000_000.0;
		System.out.println("test took: " + ms + " ms");
	}

	private Monopoly loadScene() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/MonopolyDekmorKhor.fxml"));
		try {
			Scene scene = new Scene(fxmlLoader.load());
			App.setAppScene(scene);
			App.AppStage.centerOnScreen();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return fxmlLoader.getController();
	}
}
