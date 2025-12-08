package app.monopoly2;

import java.net.URL;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.ResourceBundle;

import app.App;
import app.monopoly2.EventTile.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Monopoly implements Initializable {
	@FXML
	Pane pane;
	@FXML
	Pane board;
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

	enum PlayerColor {
		RED, GREEN, BLUE, YELLOW;
	}

	final double xSpawn = 721;
	final double ySpawn = 732;
	final double playerSize = 10;

	// NOTE: Id for each tile in board. Use for create Object of each type
	// This is for CSS ID
	final String startTileId = "start";
	final String jailTileId = "jail";
	final String goToJailTileId = "goToJail";
	final String busTileId = "bus";
	final String propertyTileId = "property";
	final String specialPropertyTileId = "special";
	final String randomTileId = "random";
	final String loseTileId = "lose";

	final int basePrice = 25;
	final int incrementPrice = 10;

	final int startMoney = 500;
	ArrayList<Player> players = new ArrayList<>();
	// ArrayList<Text> nameTexts = new ArrayList<>();
	ArrayList<Text> moneyTexts = new ArrayList<>();
	ArrayList<PropertyTile> properties = new ArrayList<>();
	ArrayList<Tile> tileList = new ArrayList<>();

	static EnumMap<PlayerColor, String> colorMap = new EnumMap<>(PlayerColor.class);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (colorMap.isEmpty()) {
			colorMap.put(PlayerColor.RED, "#ff0000");
			colorMap.put(PlayerColor.GREEN, "#03fc03");
			colorMap.put(PlayerColor.BLUE, "#030bfc");
			colorMap.put(PlayerColor.YELLOW, "#dbfc03");
		}
		// nameTexts.add(player1Name);
		// nameTexts.add(player2Name);
		// nameTexts.add(player3Name);
		// nameTexts.add(player4Name);
		moneyTexts.add(player1Money);
		moneyTexts.add(player2Money);
		moneyTexts.add(player3Money);
		moneyTexts.add(player4Money);
	}

	public void GameInit(int numPlayer) {
		Image image;
		double startPosY = ySpawn;
		switch (numPlayer) {
			case 2:
				image = new Image("/pic/2Player.png");
				background2.setImage(image);
				startPosY -= playerSize * 2;
				break;
			case 3:
				image = new Image("/pic/3Player.png");
				background2.setImage(image);
				player3Name.setVisible(true);
				player3Money.setVisible(true);
				startPosY -= playerSize * 2 + playerSize;
				break;
			case 4:
				image = new Image("/pic/4Player.png");
				background2.setImage(image);
				player3Name.setVisible(true);
				player3Money.setVisible(true);
				player4Name.setVisible(true);
				player4Money.setVisible(true);
				startPosY -= playerSize * 4;
				break;
		}

		PlayerColor color;
		for (int i = 0; i < numPlayer; i++) {
			color = PlayerColor.values()[i];
			Paint p = Paint.valueOf(colorMap.get(color));
			Circle c = new Circle(playerSize, p);
			// Text nText = nameTexts.get(i);
			// nText.setText(color.toString());
			// nText.setFill(p);
			Text mText = moneyTexts.get(i);
			Player player = new Player(startMoney, c, i + 1);

			// NOTE: Bind TextProperty from Text to player Money Property
			mText.textProperty().bind(player.getMoneyProperty().asString());

			c.setLayoutX(xSpawn);
			c.setLayoutY(startPosY + 20 * i);
			pane.getChildren().add(player.getPlayer_char());
			players.addLast(player);
		}

		int rectIndex = 0;
		int i = -1;
		for (Node n : board.getChildren()) {
			i += 1;
			if (n.getId() == null && n instanceof Rectangle) {
				PropertyTile p = properties.get(rectIndex);
				Rectangle r = (Rectangle) n;
				p.setRect(r);
				rectIndex += 1;
				continue;
			}
			ImageView tile = (ImageView) n;
			switch (n.getId()) {
				case startTileId:
					EventTile start_tile = new EventTile(EventType.START, tile.getLayoutX(), tile.getLayoutY(),
							tile.getFitWidth(), tile.getFitHeight());
					tileList.add(start_tile);
					break;
				case jailTileId:
					EventTile jail_tile = new EventTile(EventType.JAIL, tile.getLayoutX(), tile.getLayoutY(),
							tile.getFitWidth(), tile.getFitHeight());
					tileList.add(jail_tile);
					break;
				case goToJailTileId:
					EventTile goToJailTile = new EventTile(EventType.GO_TO_JAIL, tile.getLayoutX(),
							tile.getLayoutY(), tile.getFitWidth(), tile.getFitHeight());
					tileList.add(goToJailTile);
					break;
				case busTileId:
					EventTile busTile = new EventTile(EventType.BUS, tile.getLayoutX(), tile.getLayoutY(),
							tile.getFitWidth(), tile.getFitHeight());
					tileList.add(busTile);
					break;
				case randomTileId:
					EventTile randomTile = new EventTile(EventType.RANDOM, tile.getLayoutX(), tile.getLayoutY(),
							tile.getFitWidth(), tile.getFitHeight());
					tileList.add(randomTile);
					break;
				case loseTileId:
					EventTile loseTile = new EventTile(EventType.LOSE, tile.getLayoutX(), tile.getLayoutY(),
							tile.getFitWidth(), tile.getFitHeight());
					tileList.add(loseTile);
					break;
				case propertyTileId:
					int price = (i == 0) ? basePrice : basePrice + incrementPrice;
					PropertyTile propertyTile = new PropertyTile(price, tile.getLayoutX(), tile.getLayoutY(),
							tile.getFitWidth(), tile.getFitHeight());
					tileList.add(propertyTile);
					properties.add(propertyTile);
					break;
				case specialPropertyTileId:
					int special_price = (i == 0) ? basePrice : basePrice + incrementPrice;
					PropertyTile specialTile = new PropertyTile(special_price, tile.getLayoutX(), tile.getLayoutY(),
							tile.getFitWidth(), tile.getFitHeight());
					specialTile.isSpecial = true;
					tileList.add(specialTile);
					properties.add(specialTile);
					break;
			}
		}
		System.out.println("---- Tile List ----");
		for (var t : tileList) {
			System.out.println(t.toString());
		}
		System.out.println("---- Properties ----");
		for (var t : properties) {
			System.out.println(t.toString());
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

	public void increaseMoney() {
		Player p = players.get(1);
		p.setMoney(p.getMoney() + 10);
	}
}
