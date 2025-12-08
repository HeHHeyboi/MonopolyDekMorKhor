package app.monopoly2;

import app.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Monopoly {
	@FXML
	ImageView background2;
	@FXML
	Text player1Name;
	@FXML
	Text player2Name;
	@FXML
	Text player3Name;
	@FXML
	Text player4Name;

	@FXML
	Text player1Money;
	@FXML
	Text player2Money;
	@FXML
	Text player3Money;
	@FXML
	Text player4Money;

	public void GameInit(int numplayer) {
		Image image;
		switch (numplayer) {
			case 2:
				image = new Image("/pic/2Player.png");
				background2.setImage(image);
				break;
			case 3:
				image = new Image("/pic/3Player.png");
				background2.setImage(image);
				player3Name.setVisible(true);
				player3Money.setVisible(true);
				break;
			case 4:
				image = new Image("/pic/4Player.png");
				background2.setImage(image);
				player3Name.setVisible(true);
				player3Money.setVisible(true);
				player4Name.setVisible(true);
				player4Money.setVisible(true);
				break;
		}
	}

	public void on_back_button_pressed() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/StartTest.fxml"));
		try {
			Scene scene = new Scene(fxmlLoader.load());
			App.setAppScene(scene);
			App.AppStage.centerOnScreen();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
