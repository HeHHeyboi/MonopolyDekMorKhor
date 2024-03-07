package com.example;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class PlayerController implements Initializable{
    @FXML
    private Text nameDisplay;
    @FXML
    private Text moneyDisplay;
    @FXML 
    private Text stepDisplay;

    private Player player1 = new Player(1000, "Jame");;
    Random random = new Random();

    IntegerProperty Money = player1.moneyProperty();
    IntegerProperty Step = player1.stepProp();
    // public void initialize() {
    //     init();
    //     update();
    // }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        init();
        update();
    }
    public void init(){
        nameDisplay.setText(player1.getName());
        moneyDisplay.setText(""+player1.getMoney());
        stepDisplay.setText(""+player1.getStep());
    }
    public void update(){

        Money.addListener((observer,oldval,newval) ->{
            moneyDisplay.setText(String.valueOf(newval.intValue()));
        });
        Step.addListener((obs,oldval,newval) ->{
            stepDisplay.setText(String.valueOf(newval.intValue()));
        });
    }

    public void addMoney() {
        player1.setMoney(player1.getMoney() + 1000);
    }

    public void minusMoney() {
        player1.setMoney(player1.getMoney() - 500);
    }
    public void TossDice(){
        player1.setStep(player1.getStep()+random.nextInt(6)+1);
    }
    
}
