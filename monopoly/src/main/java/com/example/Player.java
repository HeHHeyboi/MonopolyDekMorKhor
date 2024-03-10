package com.example;


import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Circle;


public class Player {
    
    private final IntegerProperty money = new SimpleIntegerProperty();
    private final IntegerProperty step = new SimpleIntegerProperty(0);
    private final String name;
    private Player nextPlayer;
    private List<Location> location = new ArrayList<>();
    private Circle circle;

    public Player(int money, String name) {
        this.money.set(money);
        this.name = name;
    }

    public int getMoney() {
        return money.get();
    }
    public void setMoney(int money) {
        this.money.set(money);
    }
    public IntegerProperty moneyProperty() {
        return money;
    }
    public int getStep(){
        return step.get();
    }
    public void setStep(int step){
        this.step.set(step);
    }
    public IntegerProperty stepProp(){
        return step;
    }
    public void setCircle(Circle c){
        this.circle = c;
    }
    public Circle getCircle(){
        return circle;
    }
    public List<Location> getList(){
        return this.location;
    }
    public String getName() {
        return name;
    }
    public void setPlayer(Player n){
        this.nextPlayer = n;
    }
    public Player getNextPlayer(){
        return nextPlayer;
    }

    
}