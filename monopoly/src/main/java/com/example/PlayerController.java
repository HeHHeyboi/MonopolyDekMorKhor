package com.example;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
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
    @FXML
    private Pane pane;
    //#region Rectangle Var
    
    //#endregion
    private List<Rectangle> tile = new ArrayList<>();
    private Player player1 = new Player(1000, "Jame");
    Random random = new Random();
    static int count = 0;

    IntegerProperty Money = player1.moneyProperty();
    IntegerProperty Step = player1.stepProp();
  //#region initialize  
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        init();
        update();
        
    }
    public void init(){
        nameDisplay.setText(player1.getName());
        moneyDisplay.setText(""+player1.getMoney());
        stepDisplay.setText(""+player1.getStep());
        List<Node> nodes = pane.getChildren();
        for(Node node:nodes){
            if(node instanceof Rectangle){
                Rectangle rectangle = (Rectangle) node;
                tile.add(rectangle);
            }
        }
        Rectangle rect = tile.get(0);
        double posX = rect.getWidth()/2+rect.getLayoutX();
        double posY = rect.getHeight()/2+rect.getLayoutY();
        playCircle.setLayoutX(posX);
        playCircle.setLayoutY(posY);
        //count++;
    }
    public void update(){

        Money.addListener((observer,oldval,newval) ->{
            moneyDisplay.setText(String.valueOf(newval.intValue()));
        });
        Step.addListener((obs,oldval,newval) ->{
            stepDisplay.setText(String.valueOf(newval.intValue()));
            moveCircle();
        });
    }
//#endregion
    public void addMoney() {
        player1.setMoney(player1.getMoney() + 1000);
    }

    public void minusMoney() {
        player1.setMoney(player1.getMoney() - 500);
    }
    public void TossDice(){
        int dice1 = random.nextInt(6)+1;
        int dice2 = random.nextInt(6)+1;
        player1.setStep(dice1+dice2);
        boolean sameFace = dice1==dice2;
        if(!sameFace){
            tossButton.setDisable(true);
        }
        else{
            tossButton.setDisable(false);
        }
    }
    public void moveCircle() {
        for (int i = 0;i < player1.getStep();i++) // or (int i = 1;i<=player1.getStep();i++)/for (int i = 0;i < player1.getStep();i++)
        {
            count++;
            if (count >= tile.size()) {
                count = 0;
            }
            Rectangle rect = tile.get(count);
            double posX = rect.getWidth()/2+rect.getLayoutX();
            double posY = rect.getHeight()/2+rect.getLayoutY();
            
            playCircle.setLayoutX(posX);
            playCircle.setLayoutY(posY);
        }
    }
    public void WaitTurn(){
        tossButton.setDisable(false);
    }
    
}
