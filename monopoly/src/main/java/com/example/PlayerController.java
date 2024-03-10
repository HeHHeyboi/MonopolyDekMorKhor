package com.example;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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
    @FXML
    private Pane pane;
    private List<Rectangle> tile = new ArrayList<>();
    private Player player1;
    private Player player2;
    private static Player curPlayer;

    Random random = new Random();
    static int count = 0;

    IntegerProperty Money = player1.moneyProperty();
    IntegerProperty Money2 = player2.moneyProperty();
    IntegerProperty Step = new SimpleIntegerProperty();
    //IntegerProperty Step2 = player2.stepProp();
    
    //#region initialize  
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        init();
        update();
        
    }
    public void init(){
        player1 = new Player(1000, "Jame");
        player2 = new Player(500,"Billy");
        player1.setPlayer(player2);
        player2.setPlayer(player1);
        nameDisplay.setText(player1.getName());
        moneyDisplay.setText(""+player1.getMoney());
        stepDisplay.setText(""+Step.getValue());

        player2NameDisplay.setText(player2.getName());
        player2MoneyDisplay.setText(""+player2.getMoney());
        //player2StepDisplay.setText(""+player2StepDisplay);
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
        player2Circle.setLayoutY(posY);//count++;
    }
    public void update(){

        Money.addListener((observer,oldval,newval) ->{
            moneyDisplay.setText(String.valueOf(newval.intValue()));
        });
        Money2.addListener((observer,oldval,newval) ->{
            player2MoneyDisplay.setText(String.valueOf(newval.intValue()));
        });
        Step.addListener((obs,oldval,newval) ->{
            stepDisplay.setText(String.valueOf(newval.intValue()));

        });
        // Step2.addListener((obs,oldval,newval) ->{
        //     player2StepDisplay.setText(String.valueOf(newval.intValue()));

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
    public void TossDice(){
        int dice1 = random.nextInt(6)+1;
        int dice2 = random.nextInt(6)+1;
        Step.set(dice1+dice2);
        curPlayer = player1;
        boolean sameFace = dice1==dice2;
        if(!sameFace){
            tossButton.setDisable(true);
        }
        else{
            tossButton.setDisable(false);
            curPlayer = curPlayer.getNextPlayer();
        }
        moveCircle();
        Rectangle rect = tile.get(count);
        double posX = rect.getWidth()/2+rect.getLayoutX();
        double posY = rect.getHeight()/2+rect.getLayoutY();
            
        curPlayer.getCircle().setLayoutX(posX);
        curPlayer.getCircle().setLayoutY(posY);
    }
    public void moveCircle() {
        for (int i = 0;i < curPlayer.getStep();i++) // or (int i = 1;i<=player1.getStep();i++)/for (int i = 0;i < player1.getStep();i++)
        {
            count++;
            if (count >= tile.size()) {
                count = 0;
            }
        }
       
    }
    public void WaitTurn(){
        tossButton.setDisable(false);
    }
    
}
