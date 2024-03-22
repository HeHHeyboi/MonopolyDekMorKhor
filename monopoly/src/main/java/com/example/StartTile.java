package com.example;

public class StartTile extends Location{

    public StartTile(int id) {
        super(id);
        
    }
    public void giveMoney(Player curPlayer){
        curPlayer.setMoney(curPlayer.getMoney()+200);
    }
    
}
