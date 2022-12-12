package agh.ics.oop;
import java.util.Random;
public class Animal {
    private MapDirection direction;
    private int[] genes;
    private int genIndex = 0;
    private Vector2d position;

    public Animal(Vector2d startingPosition, int[] genes){
        this.position = startingPosition;
        this.genes = genes;
        Random rand = new Random();
        this.direction = MapDirection.NORTH.toMapDirection(rand.nextInt(8));
    }
    public Vector2d getPosition(){
        return this.position;
    }


}
