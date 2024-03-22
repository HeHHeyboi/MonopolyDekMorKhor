package com.example;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;



import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    @FXML private Pane popUpPane;
    @FXML private Button popCloseButton;
    @FXML private Button popYesButton;
    @FXML private Button popNoButton;
    @FXML private Button popNextButton;
    @FXML private Text popText;
    public List<Rectangle> tile = new ArrayList<>();
    private List<Location> locations = new ArrayList<>();
    private Player player1;
    private Player player2;
    private static Player curPlayer;
    Random random = new Random();
    
    IntegerProperty Money1;
    IntegerProperty Money2;
    IntegerProperty Step = new SimpleIntegerProperty();
    StringProperty Player1Name = new SimpleStringProperty();
    StringProperty Player2Name = new SimpleStringProperty();
    
    static int count = 0;
    static int dice1;
    static int dice2;
    static Location l;
    static Rectangle rect;
    static double posX;
    static double posY;
    //#region initialize  
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        init();
        update();
    }
    public void init(){
        player1 = new Player(1000, "Jame");
        player2 = new Player(1000,"Billy");
        player1.setNextPlayer(player2);
        player1.setCircle(playCircle);
        player2.setNextPlayer(player1);
        player2.setCircle(player2Circle);
        curPlayer = player1;
        nameDisplay.setText(player1.getName());
        moneyDisplay.setText(""+player1.getMoney());
        player2NameDisplay.setText(player2.getName());
        player2MoneyDisplay.setText(""+player2.getMoney());
        Money1 = player1.moneyProperty();
        Money2 = player2.moneyProperty();
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
        
        // for(int i =0;i <= tile.size();i++){
        //     locations.add(new Property(i));
        // }
        locations.add(new EventTile(2));
        locations.add(new Property(1, 150, 30));
        locations.add(new EventTile(0));
        locations.add(new Property(1, 250, 50));
        locations.add(new EventTile(3));
        locations.add(new Property(1, 350, 70));
        locations.add(new Property(1, 400, 80));
        locations.add(new Property(1, 450, 90));
        rect = tile.get(0);
        posX = rect.getWidth()/2+rect.getLayoutX();
        posY = rect.getHeight()/2+rect.getLayoutY();
        playCircle.setLayoutX(posX);
        playCircle.setLayoutY(posY);
        player2Circle.setLayoutX(posX);
        player2Circle.setLayoutY(posY);
    }
    public void update(){

        Money1.addListener((observer,oldval,newval) ->{
           moneyDisplay.setText(String.valueOf(newval.intValue()));
        });
        Money2.addListener((observer,oldval,newval) ->{
            player2MoneyDisplay.setText(String.valueOf(newval.intValue()));
        });
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
        curPlayer.setMoney(player1.getMoney() + 1000);
    }

    public void minusMoney() {
        curPlayer.setMoney(player1.getMoney() - 1000);
    }
    //#endregion
    public void DoubleDice() throws InterruptedException{
        dice1 = 2;
        dice2 =2;
        Step.set(dice1+dice2);
        if(curPlayer.CheckDouble_count() == true){
            curPlayer.PlayerPos(2);
            rect = tile.get(curPlayer.PlayerPos());
            posX = rect.getWidth()/2+rect.getLayoutX();
            posY = rect.getHeight()/2+rect.getLayoutY();
            curPlayer.getCircle().setLayoutX(posX);
            curPlayer.getCircle().setLayoutY(posY);
            System.out.println(curPlayer.getName()+" is in jailed");
        }
        else if(curPlayer.getWaitInjaild()>0){
            boolean sameDice = dice1==dice2;
            if(sameDice){
                System.out.println(curPlayer.getName()+"is geted out of jail");
                moveCircle(dice1+dice2);
            }
            else{
                curPlayer.setWaitinJail(curPlayer.getWaitInjaild()-1);
                curPlayer = curPlayer.getNextPlayer();
                System.out.println("You didn't get double wait in jail for "+curPlayer.getWaitInjaild() + "turn");
            }
        }
        else{
            moveCircle(dice1+dice2);

        }
    }
    public void TossDice() throws InterruptedException{
        dice1 = random.nextInt(6)+1;
        dice2 = random.nextInt(6)+1;
        Step.set(dice1+dice2);
        System.out.println(curPlayer.getName()+" turn");
        // Platform.runLater(()->{
            //     movePlayer.run();
            // });
            if(curPlayer.CheckDouble_count() == true){
                curPlayer.PlayerPos(2);
                rect = tile.get(curPlayer.PlayerPos());
                posX = rect.getWidth()/2+rect.getLayoutX();
                posY = rect.getHeight()/2+rect.getLayoutY();
                curPlayer.getCircle().setLayoutX(posX);
                curPlayer.getCircle().setLayoutY(posY);
                System.out.println(curPlayer.getName()+" is in jailed");
            }
            else if(curPlayer.getWaitInjaild()>0){
                boolean sameDice = dice1==dice2;
                if(sameDice){
                    System.out.println(curPlayer.getName()+"is geted out of jail");
                    curPlayer.setDouble_countToZero();
                    moveCircle(dice1+dice2);
                }
                else{
                    curPlayer.setWaitinJail(curPlayer.getWaitInjaild()-1);
                    System.out.println(curPlayer.getName()+" didn't get double wait in jail for "+curPlayer.getWaitInjaild() + "turn");
                    curPlayer = curPlayer.getNextPlayer();
                }
            }
            else{
                moveCircle(dice1+dice2);
    
            }
        
    }
    //move player circle with animation 'dice1 +dice' times
    public void moveCircle(int Sumdice) throws InterruptedException {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.25),event ->{
            curPlayer.PlayerPos(curPlayer.PlayerPos()+1);
            rect = tile.get(curPlayer.PlayerPos());
            posX = rect.getWidth()/2+rect.getLayoutX();
            posY = rect.getHeight()/2+rect.getLayoutY();
            curPlayer.getCircle().setLayoutX(posX);
            curPlayer.getCircle().setLayoutY(posY);
            count++;

            l = locations.get(curPlayer.PlayerPos());
            tossButton.setDisable(true);
            switch (l.id) {
                case 2:
                    ((EventTile) l).giveMoney(curPlayer);
                    
                    break;
            }
            
            //when count or animation finish reset tossbutton, reset count to 0, show popup window and change popwindow text
            if(count == Sumdice){
                if(dice1==dice2){
                    curPlayer.Inc_DoubleC();
                    System.out.println(curPlayer.getDoubleCount());
                }
                tossButton.setDisable(false);
                checkTile(l);
                
                count = 0;
            }
        }));
        timeline.setCycleCount(Sumdice);
        timeline.play();
        
    }
    //show popup window
    public void popUpwindow(){
        popUpPane.setVisible(true);
    }
    public void setButton(int id){
        switch (id) {
            case 1:
                popCloseButton.setVisible(false);
                popYesButton.setVisible(true);
                popNoButton.setVisible(true);
                popNextButton.setVisible(false);
                break;

            case 2: 
                popCloseButton.setVisible(false);
                popYesButton.setVisible(false);
                popNoButton.setVisible(false);
                popNextButton.setVisible(true);
                break;
            case 0:
                popCloseButton.setVisible(true);
                popYesButton.setVisible(false);
                popNoButton.setVisible(false);
                popNextButton.setVisible(false);
                break;
        }
    }
    
    //when click button to close popup window
    public void Exit(){
        if(dice1!=dice2){
            curPlayer.setDouble_countToZero();
            curPlayer = curPlayer.getNextPlayer();
            curPlayer.getCircle().toFront();
        }
        popUpPane.setVisible(false);
    }
    public void buyProperty(){
        Player owner = ((Property)l).getOwner();
        if(owner != null){
            curPlayer.setMoney(curPlayer.getMoney()-((Property) l).getPrice());
            owner.setMoney(owner.getMoney()+((Property)l).getPrice());
        }
        else{
            curPlayer.setMoney(curPlayer.getMoney()-((Property) l).getPrice());
        }
        ((Property) l).setOwner(curPlayer);
        Rectangle rect = tile.get(curPlayer.PlayerPos());
        rect.setFill(curPlayer.getCircle().getFill());
        ((Property) l).UgpradeProp();
        
        if(dice1!=dice2){
            curPlayer.setDouble_countToZero();
            curPlayer = curPlayer.getNextPlayer();
            curPlayer.getCircle().toFront();
        }
        popUpPane.setVisible(false);
    }
    public void NextButton(){
        if(((Property) l).getUpgradeC()<=3){    
            popText.setText("Would you like to buy?\n"+((Property) l).getPrice()+ " baht, with upgrade "+((Property)l).getUpgradeC());
            setButton(1);
        }
        else{
            popText.setText("upgrade is max can't not purchase");
            setButton(0);
        }
        
    }
    public void checkTile(Location los){
        switch (los.getID()) {
            case 2:
                ((EventTile) los).giveMoney(curPlayer);
                if(dice1!= dice2){
                    curPlayer.setDouble_countToZero();
                    curPlayer = curPlayer.getNextPlayer();
                    curPlayer.getCircle().toFront();
                }
                break;
            
            case 1:
                Player owner = ((Property) los).getOwner();
                if(owner == null){
                    popText.setText("Would you like to buy?\n"+((Property) l).getPrice()+ " baht");
                    setButton(1);
                }
                else if(owner == curPlayer){
                    if(((Property) l).getUpgradeC()<=3){
                        popText.setText("Would you like to buy upgrade "+((Property) l).getUpgradeC()+"\n"+((Property) l).getPrice()+" baht");
                        setButton(1);
                    }
                    else{
                        popText.setText("upgrade is max can't not purchase");
                        setButton(0);
                    }
                }
                else{
                    popText.setText("You paid "+((Property) l).getPaid() + " to the "+owner.getName());
                    curPlayer.setMoney(curPlayer.getMoney()-((Property) l).getPaid());
                    owner.setMoney(owner.getMoney()+((Property) l).getPaid());
                    setButton(2);
                }
                popUpwindow();
                break;
            case 3:
                curPlayer.PlayerPos(2);
                rect = tile.get(curPlayer.PlayerPos());
                posX = rect.getWidth()/2+rect.getLayoutX();
                posY = rect.getHeight()/2+rect.getLayoutY();
                curPlayer.getCircle().setLayoutX(posX);
                curPlayer.getCircle().setLayoutY(posY);
                curPlayer.setWaitinJail(3);
                System.out.println(curPlayer.getName()+" is in jailed");
                curPlayer = curPlayer.getNextPlayer();
                break;
            default:
                if(dice1!= dice2){
                    curPlayer.setDouble_countToZero();
                    curPlayer = curPlayer.getNextPlayer();
                    curPlayer.getCircle().toFront();
                }
                break;
            }   
        }
    }
