package com.example;


import javafx.beans.property.IntegerProperty;

import javafx.beans.property.SimpleIntegerProperty;


public class Player {
    
    private final IntegerProperty money = new SimpleIntegerProperty();
    private final IntegerProperty step = new SimpleIntegerProperty(0);
    private final String name;

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


    public String getName() {
        return name;
    }

    
}