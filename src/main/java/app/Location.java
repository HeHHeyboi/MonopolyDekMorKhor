package app;



public abstract class Location {
    protected int id;

    public Location(int id){
        this.id = id;
    }
    public int getID(){
        return this.id;
    }

    
}
