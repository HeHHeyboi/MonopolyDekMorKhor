package app.monopoly;

import app.App;
import java.io.IOException;

import javafx.fxml.FXML;

public class StartController {
	@FXML
	private void switchTo2Player() throws Exception {
		long start = System.nanoTime();

		App.setRoot("MonopolyDekmorKhor2");

		long end = System.nanoTime();
		double ms = (end - start) / 1_000_000.0;

		System.out.println("main took: " + ms + " ms");
	}

	@FXML
	private void switchTo3Player() throws Exception {
		long start = System.nanoTime();

		App.setRoot("MonopolyDekmorkhor3");

		long end = System.nanoTime();
		double ms = (end - start) / 1_000_000.0;

		System.out.println("main took: " + ms + " ms");
	}

	@FXML
	private void switchTo4Player() throws Exception {
		long start = System.nanoTime();
		App.setRoot("MonopolyDekmorkhor4");
		long end = System.nanoTime();
		double ms = (end - start) / 1_000_000.0;

		System.out.println("main took: " + ms + " ms");
	}
}
