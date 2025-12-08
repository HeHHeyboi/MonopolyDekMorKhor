package app.monopoly;

import app.App;
import java.io.IOException;

import javafx.fxml.FXML;

public class StartController {
	@FXML
	private void switchTo2Player() throws Exception {
		App.setRoot("MonopolyDekmorKhor2");
	}

	@FXML
	private void switchTo3Player() throws Exception {
		App.setRoot("MonopolyDekmorkhor3");
	}

	@FXML
	private void switchTo4Player() throws Exception {
		App.setRoot("MonopolyDekmorkhor4");
	}
}
