package com.example;

public class Property extends Location{

    private int price;
    private int paid;
    private Player owner;
    private int upgradeCount = 0;
    public Property(int id,int price,int paid) {
        super(id);
        this.price = price;
        this.paid = paid;
        this.owner = null;    
    }

    public void setPrice(int price){
        this.price = price;
    }
    public int getPrice(){
        return this.price;
    }
    public int getPaid(){
        return this.paid;
    }
    public void setOwner(Player p){
        this.owner = p;
    }
    public Player getOwner(){
        return this.owner;
    }
}
