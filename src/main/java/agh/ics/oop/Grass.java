package agh.ics.oop;

public class Grass implements IGameElement{
    Vector2d position;
    public Grass (Vector2d startingPosition){
        this.position = startingPosition;
    }

    @Override
    public boolean isAt(Vector2d pos) {
        if (pos.equals(this.position)) return true;
        return false;
    }

    public Vector2d getPosition(){
        return this.position;

    }
    public String toString(){
        return "*";
    }

}
