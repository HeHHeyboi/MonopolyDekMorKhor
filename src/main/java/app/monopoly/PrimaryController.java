package app.monopoly;

import app.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

	@FXML
	private void switchToSecondary() throws Exception {
		App.setRoot("secondary");
	}
}
