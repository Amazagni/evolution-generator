package agh.ics.oop;
import java.util.ArrayList;
import java.util.Random;
public class Animal {
    private MapDirection direction;
    private ArrayList<Integer> genes;
    private int genIndex = 0;
    private Vector2d position;
    private int energy;

    public Animal(Vector2d startingPosition, ArrayList<Integer> genes,int energy){
        this.energy = energy;
        this.position = startingPosition;
        this.genes = genes;
        //losowo generuje początkowy kierunek zwierzaka
        this.direction = MapDirection.NORTH.toMapDirection((int)(Math.random()*8));
    }
    public Vector2d getPosition(){
        return this.position;
    }
    public String toString(){
        return "";
    }


}
