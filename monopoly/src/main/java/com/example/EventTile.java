package com.example;

public class EventTile extends Location{

    public EventTile(int id) {
        super(id);
        
    }
    public void giveMoney(Player curPlayer){
        curPlayer.setMoney(curPlayer.getMoney()+200);
    }
    
}
