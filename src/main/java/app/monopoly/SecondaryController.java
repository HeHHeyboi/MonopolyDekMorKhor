package app.monopoly;

import app.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

	@FXML
	private void switchToPrimary() throws Exception {
		App.setRoot("primary");
	}
}
