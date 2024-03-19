package com.example;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
public class PlayerController implements Initializable{
    @FXML
    private Text nameDisplay;
    @FXML
    private Text moneyDisplay;
    @FXML 
    private Text stepDisplay;
    @FXML
    private Button tossButton;
    @FXML 
    private Circle playCircle;
    @FXML Text player2NameDisplay;
    @FXML Text player2MoneyDisplay;
    //@FXML Text player2StepDisplay;
    @FXML Circle player2Circle;
    @FXML private Pane pane;
    public List<Rectangle> tile = new ArrayList<>();
    //private List<Location> locations = new ArrayList<>();
    private Player player1;
    private Player player2;
    private static Player curPlayer;
    Random random = new Random();
    static int count = 0;// need to Fix

    IntegerProperty Money;
    //IntegerProperty Money2 = player2.moneyProperty();
    IntegerProperty Step = new SimpleIntegerProperty();
    //IntegerProperty Step2 = player2.stepProp();
    //BooleanProperty tossButtonCheck = new SimpleBooleanProperty();
    //#region initialize  
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        init();
        update();
    }
    public void init(){
        player1 = new Player(1000, "Jame");
        player2 = new Player(500,"Billy");
        player1.setNextPlayer(player2);
        player1.setCircle(playCircle);
        player2.setNextPlayer(player1);
        player2.setCircle(player2Circle);
        curPlayer = player1;
        
        nameDisplay.setText(player1.getName());
        moneyDisplay.setText(""+player1.getMoney());
        //player2NameDisplay.setText(player2.getName());
        //player2MoneyDisplay.setText(""+player2.getMoney());
        Money = player1.moneyProperty();
        stepDisplay.setText(""+Step.getValue());
        //tossButtonCheck.set(false);
        //#region Add rectangle to list
        List<Node> nodes = pane.getChildren();
        for(Node node:nodes){
            if(node instanceof Rectangle){
                Rectangle rectangle = (Rectangle) node;
                tile.add(rectangle);
            }
        }//#endregion
        Rectangle rect = tile.get(0);
        double posX = rect.getWidth()/2+rect.getLayoutX();
        double posY = rect.getHeight()/2+rect.getLayoutY();
        playCircle.setLayoutX(posX);
        playCircle.setLayoutY(posY);
        player2Circle.setLayoutX(posX);
        player2Circle.setLayoutY(posY);
    }
    public void update(){

        Money.addListener((observer,oldval,newval) ->{
           moneyDisplay.setText(String.valueOf(newval.intValue()));
        });
        // Money2.addListener((observer,oldval,newval) ->{
        //     player2MoneyDisplay.setText(String.valueOf(newval.intValue()));
        // });
        Step.addListener((obs,oldval,newval) ->{
            stepDisplay.setText(String.valueOf(newval.intValue()));
        });
        // tossButtonCheck.addListener((obs,oldval,newval)->{
        //     tossButton.setDisable(newval);
        // });
    }
//#endregion
   

    //#region Add and Minus money
    public void addMoney() {
        player1.setMoney(player1.getMoney() + 1000);
    }

    public void minusMoney() {
        player1.setMoney(player1.getMoney() - 500);
    }
    //#endregion
    // Need to fix
    public void TossDice() throws InterruptedException{
        int dice1 = random.nextInt(6)+1;
        int dice2 = random.nextInt(6)+1;
        Step.set(dice1+dice2);
        moveCircle(dice1+dice2);
        // Platform.runLater(()->{
        //     movePlayer.run();
        // });
        if(dice1 != dice2){
            curPlayer = curPlayer.getNextPlayer();
            nameDisplay.setText(curPlayer.getName());
            moneyDisplay.setText(""+curPlayer.getMoney());
        }
        
        
    }
    public void moveCircle(int Sumdice) throws InterruptedException {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.25),event ->{
            curPlayer.PlayerPos(curPlayer.PlayerPos()+1);
            Rectangle rect = tile.get(curPlayer.PlayerPos());
            double posX = rect.getWidth()/2+rect.getLayoutX();
            double posY = rect.getHeight()/2+rect.getLayoutY();
            curPlayer.getCircle().setLayoutX(posX);
            curPlayer.getCircle().setLayoutY(posY);
            count++;
            tossButton.setDisable(true);
            if(count == Sumdice){
                count = 0;
                tossButton.setDisable(false);
            }
        }));
        timeline.setCycleCount(Sumdice);
        timeline.play();
    
    }
}
