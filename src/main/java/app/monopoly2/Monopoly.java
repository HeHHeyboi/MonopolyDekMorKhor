package app.monopoly2;

import java.net.URL;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import app.App;
import app.monopoly2.EventTile.EventType;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

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

	@FXML
	Text stepDisplay;
	@FXML
	Button tossButton;
	@FXML
	Pane popUpPane;
	@FXML
	Text popText;
	@FXML
	Button popCloseButton;
	@FXML
	Button popNextButton;
	@FXML
	Button popYesButton;
	@FXML
	Button popNoButton;
	@FXML
	Button popGoButton;
	@FXML
	Text luckText;
	@FXML
	TextField textField;

	enum PlayerColor {
		RED, GREEN, BLUE, YELLOW;
	}

	enum PopUpType {
		BuyProperty, UpgradeProperty, BusSelectTile, Notify, Hide
	}

	enum GameState {
		Normal, BusSelect
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

	Player curPlayer;
	int curPlayerDoubleCount;
	int curPlayerIndex;
	IntegerProperty StepCount = new SimpleIntegerProperty(0);
	Random random = new Random();

	int jailIndex;
	int busIndex;
	boolean getDouble = false;
	GameState gameState = GameState.Normal;

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
				image = new Image("/pic/2player.png");
				background2.setImage(image);
				startPosY -= playerSize * 2;
				break;
			case 3:
				image = new Image("/pic/3player.png");
				background2.setImage(image);
				player3Name.setVisible(true);
				player3Money.setVisible(true);
				startPosY -= playerSize * 2 + playerSize;
				break;
			case 4:
				image = new Image("/pic/4player.png");
				background2.setImage(image);
				player3Name.setVisible(true);
				player3Money.setVisible(true);
				player4Name.setVisible(true);
				player4Money.setVisible(true);
				startPosY -= playerSize * 4;
				break;
		}

		int rectIndex = 0;
		for (Node n : board.getChildren()) {
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
					jailIndex = tileList.size() - 1;
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
					busIndex = tileList.size() - 1;
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
					PropertyTile propertyTile = new PropertyTile(tile.getLayoutX(), tile.getLayoutY(),
							tile.getFitWidth(), tile.getFitHeight());
					tileList.add(propertyTile);
					properties.add(propertyTile);
					break;
				case specialPropertyTileId:
					PropertyTile specialTile = new PropertyTile(tile.getLayoutX(), tile.getLayoutY(),
							tile.getFitWidth(), tile.getFitHeight());
					specialTile.isSpecial = true;
					tileList.add(specialTile);
					properties.add(specialTile);
					break;
			}
		}
		// System.out.println("---- Properties ----");
		int price = basePrice;
		double mul = 1.0;
		final double paidRate = 0.6;
		int rateCount = 0;
		for (PropertyTile t : properties) {
			int paid = (int) (price * paidRate);
			t.setPrice(price);
			t.setPaid(paid);
			price += incrementPrice * mul;
			rateCount += 1;
			if (rateCount % 5 == 0) {
				mul += 0.3;
			}
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
			Player player = new Player(color.toString(), startMoney, c, i);

			// NOTE: Bind TextProperty from Text to player Money Property
			mText.textProperty().bind(player.getMoneyProperty().asString());

			c.setLayoutX(xSpawn);
			c.setLayoutY(startPosY + 20 * i);
			System.out.println(c.getTranslateX() + "," + c.getTranslateY());
			player.setMaxTile(tileList.size());
			pane.getChildren().add(player.getPlayer_char());
			players.addLast(player);
		}
		System.out.println(players.toString());
		curPlayer = players.getFirst();
		stepDisplay.textProperty().bind(StepCount.asString());
	}

	private Player iteratorPlayer() {
		curPlayerIndex++;
		if (curPlayerIndex >= players.size()) {
			curPlayerIndex = 0;
		}
		return players.get(curPlayerIndex);
	}

	public void setLuckText(String text) {
		luckText.setText(text);
		luckText.setVisible(true);
		luckText.setFill(curPlayer.getPlayer_char().getFill());
	}

	public void TossDice(ActionEvent event) throws InterruptedException {
		int dice1 = random.nextInt(6) + 1;
		int dice2 = random.nextInt(6) + 1;
		System.out.println("Dice 1: " + dice1 + ", Dice 2: " + dice2);
		StepCount.set(dice2 + dice1);
		luckText.setVisible(false);
		if (dice1 == dice2) {
			getDouble = true;
			luckText.setText(String.format("%s get Double!!!", curPlayer.name));
			luckText.setVisible(true);
			luckText.setFill(curPlayer.getPlayer_char().getFill());
		} else {
			getDouble = false;
		}

		if (curPlayer.isInJail) {
			if (curPlayer.waitInJail <= 0) {
				curPlayer.isInJail = false;
				setLuckText(String.format("%s wait 3 Turn and Doesn't get F anymore", curPlayer.name));
				movePlayer(dice1 + dice2);
			} else if (getDouble) {
				curPlayer.isInJail = false;
				setLuckText(String.format("%s get Double, Doesn't get F anymore", curPlayer.name));
				movePlayer(dice1 + dice2);
			} else {
				setLuckText(String.format("%s still get F because Doesn't get double wait %d turn", curPlayer.name,
						curPlayer.waitInJail));
				curPlayer.waitInJail -= 1;
				curPlayer = iteratorPlayer();
				curPlayer.getPlayer_char().toFront();
			}
		} else {
			movePlayer(dice1 + dice2);
		}
	}

	// public void moveCircle(int dice1, int dice2) throws InterruptedException {
	// tossButton.setDisable(true);
	// int sumDice = dice1 + dice2;
	// ArrayList<KeyFrame> keyframes = new ArrayList<>();
	// for (int i = 1; i <= sumDice; i++) {
	// curPlayer.setPlayerPos(curPlayer.getPlayerPos() + 1);
	// int playerPos = curPlayer.getPlayerPos();
	// Tile t = tileList.get(curPlayer.getPlayerPos());
	// // checkEvent(t, true);
	// KeyFrame keyFrame = createKeyFrame(t, 0.25 * i, playerPos);
	// keyframes.add(keyFrame);
	// }
	// Timeline timeline = new Timeline(60, keyframes.toArray(new KeyFrame[0]));
	// timeline.setOnFinished(event -> {
	// tossButton.setDisable(false);
	// Tile t = tileList.get(curPlayer.getPlayerPos());
	// checkEvent(t);
	// });
	// timeline.play();
	// }
	//
	public void moveToTile(int tileIndex) {
		tossButton.setDisable(true);
		ArrayList<KeyFrame> keyframes = new ArrayList<>();
		int animCount = 0;
		while (curPlayer.getPlayerPos() != tileIndex) {
			curPlayer.setPlayerPos(curPlayer.getPlayerPos() + 1);
			int playerPos = curPlayer.getPlayerPos();
			Tile t = tileList.get(curPlayer.getPlayerPos());
			// checkEvent(t, true);
			KeyFrame keyFrame = createKeyFrame(t, 0.25 * animCount, playerPos);
			keyframes.add(keyFrame);
			animCount += 1;
		}
		Timeline timeline = new Timeline(60, keyframes.toArray(new KeyFrame[0]));
		timeline.setOnFinished(event -> {
			Tile t = tileList.get(curPlayer.getPlayerPos());
			tossButton.setDisable(false);
			checkEvent(t);
		});
		timeline.play();
	}

	public void movePlayer(int step) {
		tossButton.setDisable(true);
		ArrayList<KeyFrame> keyframes = new ArrayList<>();
		for (int i = 1; i <= step; i++) {
			curPlayer.setPlayerPos(curPlayer.getPlayerPos() + 1);
			int playerPos = curPlayer.getPlayerPos();
			Tile t = tileList.get(curPlayer.getPlayerPos());
			// checkEvent(t, true);
			KeyFrame keyFrame = createKeyFrame(t, 0.25 * i, playerPos);
			keyframes.add(keyFrame);
		}
		Timeline timeline = new Timeline(60, keyframes.toArray(new KeyFrame[0]));
		timeline.setOnFinished(event -> {
			Tile t = tileList.get(curPlayer.getPlayerPos());
			tossButton.setDisable(false);
			checkEvent(t);
		});
		timeline.play();
	}

	// EventHandler<ActionEvent> stepOnStart = new EventHandler<ActionEvent>() {
	// @Override
	// public void handle(ActionEvent event) {
	// curPlayer.setMoney(curPlayer.getMoney() + 200);
	// }
	// };
	public KeyFrame createKeyFrame(Tile tile, double currentTime, int step) {
		double posX = tile.getX() + tile.getWidth() / 2;
		double posY = tile.getY() + tile.getHeight() / 2;

		Circle c = curPlayer.getPlayer_char();
		KeyValue x = new KeyValue(c.layoutXProperty(), posX);
		KeyValue y = new KeyValue(c.layoutYProperty(), posY);

		if (tile instanceof EventTile) {
			EventTile e = (EventTile) tile;
			if (e.type == EventType.START)
				return new KeyFrame(Duration.seconds(currentTime), event -> {
					curPlayer.setMoney(curPlayer.getMoney() + 200);
					setLuckText(String.format("%s get 200 Baht", curPlayer.name));
				}, x, y);
		}
		return new KeyFrame(Duration.seconds(currentTime), x, y);
	}

	public void checkEvent(Tile t) {
		if (t instanceof EventTile) {
			EventTile e = (EventTile) t;
			switch (e.type) {
				case START:
					curPlayer.setMoney(curPlayer.getMoney() + 200);
					setLuckText(String.format("%s get 200 Baht", curPlayer.name));
					break;
				case GO_TO_JAIL:
					curPlayer.setPlayerPos(jailIndex);
					Circle c = curPlayer.getPlayer_char();
					Tile tile = tileList.get(jailIndex);
					double posX = tile.getX() + tile.getWidth() / 2;
					double posY = tile.getY() + tile.getHeight() / 2;
					c.setLayoutX(posX);
					c.setLayoutY(posY);
					// NOTE: GO_TO_JAIL Case Fallthrough to JAIL Case
				case JAIL:
					curPlayer.isInJail = true;
					curPlayer.waitInJail = 3;
					setLuckText(String.format("%s get F", curPlayer.name));
					curPlayer = iteratorPlayer();
					curPlayer.getPlayer_char().toFront();
					return;
				case BUS:
					setPopUpButton(PopUpType.BusSelectTile);
					gameState = GameState.BusSelect;
					return;
				case LOSE:
					break;
				case RANDOM:
					break;
			}
		} else {
		}
		if (!getDouble) {
			curPlayer = iteratorPlayer();
			curPlayer.getPlayer_char().toFront();
		}
	}

	public void setPopUpButton(PopUpType type) {
		popUpPane.setVisible(true);
		switch (type) {
			case BuyProperty, UpgradeProperty:
				popCloseButton.setVisible(false);
				popYesButton.setVisible(true);
				popNoButton.setVisible(true);
				popNextButton.setVisible(false);
				break;

			case Notify:
				popCloseButton.setVisible(false);
				popYesButton.setVisible(false);
				popNoButton.setVisible(false);
				popNextButton.setVisible(true);
				break;
			case BusSelectTile:
				popText.setText("Please Select Tile Do you want to go, Except Shuttle Bus Tile");
				popCloseButton.setVisible(false);
				popYesButton.setVisible(false);
				popNoButton.setVisible(false);
				popNextButton.setVisible(false);
				break;
			case Hide:
				popCloseButton.setVisible(false);
				popYesButton.setVisible(false);
				popNoButton.setVisible(false);
				popNextButton.setVisible(false);
				popUpPane.setVisible(false);
				break;
		}

	}

	public void on_toss_9_pressed() {
		movePlayer(9);
	}

	public void on_toBusTile_pressed() {
		movePlayer(27);
	}

	public void on_mouse_click_on_pane(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY
				&& gameState == GameState.BusSelect) {
			double mPosX = event.getX();
			double mPosY = event.getY();
			int tileIndex = -1;
			Tile t;
			for (int i = 0; i < tileList.size(); i++) {
				t = tileList.get(i);
				if (t.isInside(mPosX, mPosY)) {
					tileIndex = i;
					break;
				}
			}

			if (tileIndex == busIndex) {
				return;
			}
			setPopUpButton(PopUpType.Hide);
			gameState = GameState.Normal;
			moveToTile(tileIndex);
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
