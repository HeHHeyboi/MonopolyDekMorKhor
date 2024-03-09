package com.example;

public class Location {
    public String name;
    public int price;
    public int id;
    public int paid;
    public Player Owner;

    public Location(String name,int price,int id,int paid){
        this.name = name;
        this.price = price;
        this.paid =paid;
        this.id = id;
    }
}
