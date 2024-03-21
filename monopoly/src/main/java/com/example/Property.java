package com.example;

public class Property extends Location{

    private String name;
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
    public Property(int id){
        super(id);
        this.paid = 0;
        this.price = 0;
        this.owner = null;
    }
    public Property(String name,int id,int price,int paid) {
        super(id);
        this.name = name;
        this.price = price;
        this.paid = paid;
        this.owner = null;    
    }
    public void UgpradeProp(){    
        price += (int)price*0.2;
        upgradeCount++;
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
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public int getUpgradeC(){
        return this.upgradeCount;
    }
}
