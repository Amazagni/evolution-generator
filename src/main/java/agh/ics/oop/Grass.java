package agh.ics.oop;

public class Grass {
    Vector2d position;
    public Grass (Vector2d startingPosition){
        this.position = startingPosition;
    }
    public Vector2d getPosition(){
        return this.position;

    }
    public String toString(){
        return "*";
    }

}
