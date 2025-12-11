package app.monopoly2;

import java.net.URL;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Random;
import java.util.ResourceBundle;

import app.App;
import app.monopoly2.EventTile.EventType;
import app.monopoly2.Player.PlayerState;
import app.monopoly2.PropertyTile.PropertyState;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Binding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
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
	Text turnText;
	@FXML
	Text doubleText;
	@FXML
	TextField textField;

	enum PlayerColor {
		RED, GREEN, BLUE, YELLOW;
	}

	enum PopUpType {
		BuyProperty, UpgradeProperty, BusSelectTile, Notify, Hide
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
	int goToJailIndex;
	boolean getDouble = false;
	int doubleCount = 0;
	PropertyTile processingProperty;

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
			Bounds b = tile.getBoundsInParent();
			double x = b.getMinX();
			double y = b.getMinY();
			double width = b.getWidth();
			double hight = b.getHeight();
			switch (n.getId()) {
				case startTileId:
					EventTile start_tile = new EventTile(EventType.START, x, y, width, hight);
					tileList.add(start_tile);
					break;
				case jailTileId:
					EventTile jail_tile = new EventTile(EventType.JAIL, x, y, width, hight);
					tileList.add(jail_tile);
					jailIndex = tileList.size() - 1;
					break;
				case goToJailTileId:
					EventTile goToJailTile = new EventTile(EventType.GO_TO_JAIL, x, y, width, hight);
					tileList.add(goToJailTile);
					goToJailIndex = tileList.size() - 1;
					break;
				case busTileId:
					EventTile busTile = new EventTile(EventType.BUS, x, y, width, hight);
					tileList.add(busTile);
					busIndex = tileList.size() - 1;
					break;
				case randomTileId:
					EventTile randomTile = new EventTile(EventType.RANDOM, x, y, width, hight);
					tileList.add(randomTile);
					break;
				case loseTileId:
					EventTile loseTile = new EventTile(EventType.LOSE, x, y, width, hight);
					tileList.add(loseTile);
					break;
				case propertyTileId:
					PropertyTile propertyTile = new PropertyTile(x, y, width, hight);
					tileList.add(propertyTile);
					properties.add(propertyTile);
					break;
				case specialPropertyTileId:
					PropertyTile specialTile = new PropertyTile(x, y, width, hight);
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
			Text mText = moneyTexts.get(i);
			Player player = new Player(color.toString(), startMoney, c, i);

			// NOTE: Bind TextProperty from Text to player Money Property
			mText.textProperty().bind(player.getMoneyProperty().asString());

			c.setLayoutX(xSpawn);
			c.setLayoutY(startPosY + 20 * i);
			player.setMaxTile(tileList.size());
			pane.getChildren().add(player.getPlayer_char());
			players.addLast(player);
		}
		curPlayer = players.getFirst();
		setTurnText(curPlayer);
		stepDisplay.textProperty().bind(StepCount.asString());
	}

	private void nextPlayer() {
		getDouble = false;
		doubleCount = 0;
		doubleText.setText("");
		stepDisplay.setVisible(false);
		curPlayerIndex++;
		if (curPlayerIndex >= players.size()) {
			curPlayerIndex = 0;
		}
		curPlayer = players.get(curPlayerIndex);
		curPlayer.getPlayer_char().toFront();
		setTurnText(curPlayer);
		tossButton.setDisable(false);
		if (curPlayer.state == PlayerState.WaitBus) {
			curPlayer.state = PlayerState.SelectTile;
			tossButton.setDisable(true);
			setPopUpButton(PopUpType.BusSelectTile);
		}
	}

	public void setLuckText(Player player, String text) {
		luckText.setText(text);
		luckText.setVisible(true);
		luckText.setFill(player.getPlayer_char().getFill());
	}

	public void setTurnText(Player player) {
		turnText.setText(player.name + " turn");
		turnText.setFill(player.getPlayer_char().getFill());
	}

	public void TossDice(ActionEvent event) throws InterruptedException {
		stepDisplay.setVisible(true);
		int dice1 = random.nextInt(6) + 1;
		int dice2 = random.nextInt(6) + 1;
		StepCount.set(dice2 + dice1);
		luckText.setVisible(false);
		if (dice1 == dice2) {
			getDouble = true;
			doubleCount += 1;
			if (doubleCount >= 3) {
				doubleCount = 0;
				curPlayer.state = PlayerState.InJailed;
				curPlayer.waitInJail = 3;
				curPlayer.setPlayerPos(jailIndex);
				setLuckText(curPlayer, String.format("%s get Double 3 times. Get F!", curPlayer.name));
				Tile tile = tileList.get(jailIndex);
				Circle c = curPlayer.getPlayer_char();
				double posX = tile.getX() + tile.getWidth() / 2;
				double posY = tile.getY() + tile.getHeight() / 2;
				c.setLayoutX(posX);
				c.setLayoutY(posY);
				nextPlayer();
				doubleText.setText("");
				return;
			}
			doubleText.setText(String.format("Get Double: %d", doubleCount));
			// setLuckText(String.format("%s get Double!!!", curPlayer.name));
		} else {
			getDouble = false;
			doubleCount = 0;
			doubleText.setText("");
		}

		if (curPlayer.state == PlayerState.InJailed) {
			if (curPlayer.waitInJail <= 0) {
				curPlayer.state = PlayerState.Normal;
				setLuckText(curPlayer, String.format("%s wait 3 Turn and Doesn't get F anymore", curPlayer.name));
				movePlayerByStep(dice1 + dice2);
			} else if (getDouble) {
				curPlayer.state = PlayerState.Normal;
				setLuckText(curPlayer, String.format("%s get Double, Doesn't get F anymore", curPlayer.name));
				movePlayerByStep(dice1 + dice2);
			} else {
				setLuckText(curPlayer,
						String.format("%s still get F because Doesn't get double wait %d turn",
								curPlayer.name, curPlayer.waitInJail));
				curPlayer.waitInJail -= 1;
				nextPlayer();
			}
		} else {
			curPlayer.state = PlayerState.Normal;
			movePlayerByStep(dice1 + dice2);
		}
	}

	public KeyFrame createKeyFrame(Player player, Tile tile, double currentTime, int step) {
		double posX = tile.getX() + tile.getWidth() / 2;
		double posY = tile.getY() + tile.getHeight() / 2;

		Circle c = player.getPlayer_char();
		KeyValue x = new KeyValue(c.layoutXProperty(), posX);
		KeyValue y = new KeyValue(c.layoutYProperty(), posY);

		if (tile instanceof EventTile) {
			EventTile e = (EventTile) tile;
			if (e.type == EventType.START)
				return new KeyFrame(Duration.seconds(currentTime), event -> {
					player.increaseMoney(200);
					setLuckText(player, String.format("%s get 200 Baht", player.name));
				}, x, y);
		}
		return new KeyFrame(Duration.seconds(currentTime), x, y);
	}

	public void movePlayerToTile(Player player, int tileIndex, double delay) {
		assert (tileIndex > -1);
		tossButton.setDisable(true);
		ArrayList<KeyFrame> keyframes = new ArrayList<>();
		int animCount = 0;
		// final Player player = curPlayer;

		while (player.getPlayerPos() != tileIndex) {
			animCount += 1;
			player.setPlayerPos(player.getPlayerPos() + 1);
			int playerPos = player.getPlayerPos();
			Tile t = tileList.get(player.getPlayerPos());
			// checkEvent(t, true);
			KeyFrame keyFrame = createKeyFrame(player, t, 0.25 * animCount, playerPos);
			keyframes.add(keyFrame);
		}
		Timeline timeline = new Timeline(60, keyframes.toArray(new KeyFrame[0]));
		timeline.setDelay(Duration.seconds(delay));
		timeline.setOnFinished(event -> {
			Tile t = tileList.get(player.getPlayerPos());
			tossButton.setDisable(false);
			if (player.state == PlayerState.MoveByBus) {
				player.state = PlayerState.ArriveByBus;
			}
			checkPlayerEvent(player, t);
			checkPlayerState(player);
			System.out.println(player.state);
		});
		timeline.play();
	}

	public void movePlayerByStep(int step) {
		assert (step > -1);
		tossButton.setDisable(true);
		ArrayList<KeyFrame> keyframes = new ArrayList<>();
		final Player player = curPlayer;
		for (int i = 1; i <= step; i++) {
			player.setPlayerPos(player.getPlayerPos() + 1);
			int playerPos = player.getPlayerPos();
			Tile t = tileList.get(player.getPlayerPos());
			// checkEvent(t, true);
			KeyFrame keyFrame = createKeyFrame(player, t, 0.25 * i, playerPos);
			keyframes.add(keyFrame);
		}
		Timeline timeline = new Timeline(60, keyframes.toArray(new KeyFrame[0]));
		timeline.setOnFinished(event -> {
			Tile t = tileList.get(player.getPlayerPos());
			tossButton.setDisable(false);
			player.state = PlayerState.ArriveNormal;
			checkPlayerEvent(player, t);
			checkPlayerState(player);
		});
		timeline.play();
	}

	public void checkPlayerState(Player player) {
		switch (player.state) {
			// NOTE: flush animation
			case WaitForDecision:
				player.state = player.prev_state;
				break;
			case InJailed, WaitBus:
				nextPlayer();
				break;
			case MoveByRandom:
				player.state = PlayerState.ArriveByRandom;
				break;
			case MoveByBus:
				player.state = PlayerState.ArriveByBus;
				break;
			case Normal:
				player.state = PlayerState.ArriveNormal;
				break;
			case ArriveByRandom, ArriveByBus:
				player.state = PlayerState.EndTurn;
				nextPlayer();
				break;
			case EndTurn:
				break;
			default:
				player.state = PlayerState.Normal;
				if (!getDouble) {
					nextPlayer();
				}
				break;
		}
	}

	public void checkPlayerEvent(Player player, Tile t) {
		if (t instanceof EventTile) {
			EventTile e = (EventTile) t;
			switch (e.type) {
				case GO_TO_JAIL:
					player.setPlayerPos(jailIndex);
					Circle c = player.getPlayer_char();
					Tile tile = tileList.get(jailIndex);
					double posX = tile.getX() + tile.getWidth() / 2;
					double posY = tile.getY() + tile.getHeight() / 2;
					c.setLayoutX(posX);
					c.setLayoutY(posY);
					// NOTE: GO_TO_JAIL Case Fallthrough to JAIL Case
				case JAIL:
					player.state = PlayerState.InJailed;
					player.waitInJail = 3;
					setLuckText(player, String.format("%s get F", player.name));
					return;
				case BUS:
					player.state = PlayerState.WaitBus;
					setLuckText(player, "Wait 1 Turn");
					return;
				case LOSE:
					player.decreaseMoney(100);
					setLuckText(player, String.format("%s lose 100 Baht to Mukata", player.name));
					return;
				case RANDOM:
					randomEvent(player);
					return;
			}
		} else {
			PropertyTile property = (PropertyTile) t;
			player.prev_state = player.state;
			player.state = PlayerState.WaitForDecision;
			if (player.getMoney() < property.price) {
				popYesButton.setDisable(true);
			} else {
				popYesButton.setDisable(false);
			}
			System.out.println(property.level);
			checkPropertyState(player, property);
		}
	}

	public void checkPropertyState(Player player, PropertyTile property) {
		int ownerIndex;
		Player owner;
		switch (property.state) {
			case NotOwn:
				popText.setText(String.format("Do you want to buy this Property\n Lv.%d Price: %d, Paid: %d",
						property.level, property.price, property.paid));
				processingProperty = property;
				setPopUpButton(PopUpType.BuyProperty);
				break;
			case MaxLevel:
				if (property.getOwner() != player.id) {
					ownerIndex = property.getOwner();
					player.decreaseMoney(property.paid);
					owner = players.get(ownerIndex);
					owner.increaseMoney(property.paid);
					setLuckText(player, String.format("%s paid %d to %s",
							player.name, property.paid, owner.name));
					checkPlayerState(player);
				} else {
					checkPlayerState(player);
				}
				break;
			case Owned:
				ownerIndex = property.getOwner();
				owner = players.get(ownerIndex);
				if (property.getOwner() != player.id) {
					player.decreaseMoney(property.paid);
					owner.increaseMoney(property.paid);
					setLuckText(player, String.format("%s paid %d to %s",
							player.name, property.paid, owner.name));
					if (!property.isSpecial) {
						popText.setText(
								String.format("Do you want to buy this Property From %s Lv.%d Price: %d, Paid: %d",
										owner.name, property.level + 1, property.upgradePrice, property.upgradePaid));
						setPopUpButton(PopUpType.BuyProperty);
						// System.out.println(property.level);
					}
					processingProperty = property;
				} else {
					if (property.level < property.maxLevel && !property.isSpecial) {
						popText.setText(
								String.format(
										"Would you like to upgrade this property\n Lv.%d -> Lv.%d Price: %d, Paid: %d",
										property.level, property.level + 1, property.upgradePrice,
										property.upgradePaid));
						processingProperty = property;
						setPopUpButton(PopUpType.UpgradeProperty);
					}
				}
				if (player.getMoney() < property.upgradePrice) {
					popYesButton.setDisable(true);
				} else {
					popYesButton.setDisable(false);
				}
				break;
		}
	}

	enum RandomEvent {
		GoToStart, GoToJailed, GoToBus, LoseMoney, GetMoney
	}

	public RandomEvent randomEvent(Player player) {
		int choice = random.nextInt(0, 5);
		RandomEvent re = RandomEvent.values()[choice];
		switch (re) {
			case GetMoney:
				player.increaseMoney(50);
				setLuckText(player, String.format("Random: %s get 50 baht", player.name));
				System.out.println(player.state);
				break;
			case LoseMoney:
				player.decreaseMoney(50);
				setLuckText(player, String.format("Random: %s lose 50 baht", player.name));
				System.out.println(player.state);
				break;
			case GoToBus:
				setLuckText(player, String.format("Random: %s go to Bus", player.name));
				player.state = PlayerState.MoveByRandom;
				movePlayerToTile(player, busIndex, 1);
				break;
			case GoToJailed:
				setLuckText(player, String.format("Random: %s go to F", player.name));
				player.setPlayerPos(jailIndex);
				Circle c = player.getPlayer_char();
				Tile tile = tileList.get(jailIndex);
				double posX = tile.getX() + tile.getWidth() / 2;
				double posY = tile.getY() + tile.getHeight() / 2;
				c.setLayoutX(posX);
				c.setLayoutY(posY);
				player.state = PlayerState.InJailed;
				player.waitInJail = 3;
				break;
			case GoToStart:
				setLuckText(player, String.format("Random: %s go to Start", player.name));
				player.state = PlayerState.MoveByRandom;
				movePlayerToTile(player, 0, 1);
				break;
		}
		return re;
	}

	public void setPopUpButton(PopUpType type) {
		popUpPane.setVisible(true);
		switch (type) {
			case BuyProperty, UpgradeProperty:
				popCloseButton.setVisible(false);
				popYesButton.setVisible(true);
				popNoButton.setVisible(true);
				popNextButton.setVisible(false);
				popUpPane.setVisible(true);
				tossButton.setDisable(true);
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
				popUpPane.setVisible(true);
				break;
			case Hide:
				popCloseButton.setVisible(false);
				popYesButton.setVisible(false);
				popNoButton.setVisible(false);
				popNextButton.setVisible(false);
				popUpPane.setVisible(false);
				tossButton.setDisable(false);
				break;
		}

	}

	public void on_yes_button_pressed() {
		final Player player = curPlayer;
		final PropertyTile property = processingProperty;
		int ownerIndex = property.getOwner();
		if (ownerIndex < 0) {
			player.decreaseMoney(property.price);
			property.setOwner(player.id);
			property.state = PropertyState.Owned;
		} else if (player.id != property.getOwner()) {
			Player owner = players.get(ownerIndex);
			property.upgrade();
			owner.increaseMoney(property.price);
			player.decreaseMoney(property.price);
			property.setOwner(player.id);
		} else {
			property.upgrade();
			player.decreaseMoney(property.price);
		}
		setPopUpButton(PopUpType.Hide);
		checkPlayerState(player);
	}

	public void on_no_button_pressed() {
		final Player player = curPlayer;
		setPopUpButton(PopUpType.Hide);
		checkPlayerState(player);
	}

	public void on_mouse_click_on_pane(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY
				&& curPlayer.state == PlayerState.SelectTile) {
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

			if (tileIndex == busIndex || tileIndex < 0) {
				return;
			}
			curPlayer.state = PlayerState.MoveByBus;
			setPopUpButton(PopUpType.Hide);
			final Player player = curPlayer;
			movePlayerToTile(player, tileIndex, 0);
		}
	}

	public void on_toss_9_pressed() {
		movePlayerByStep(9);
	}

	public void on_toBusTile_pressed() {
		movePlayerToTile(curPlayer, busIndex, 0);
	}

	public void on_get_double_pressed() throws Exception {
		doubleCount += 1;
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
