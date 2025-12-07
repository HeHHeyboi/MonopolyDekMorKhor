package monopoly;

import java.io.IOException;

import javafx.fxml.FXML;

public class StartController {
	@FXML
	private void switchTo2Player() throws IOException {
		App.setRoot("MonopolyDekmorKhor2");
	}

	@FXML
	private void switchTo3Player() throws IOException {
		App.setRoot("MonopolyDekmorkhor3");
	}

	@FXML
	private void switchTo4Player() throws IOException {
		App.setRoot("MonopolyDekmorkhor4");
	}
}
