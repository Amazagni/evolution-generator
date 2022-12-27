package agh.ics.oop;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
public class Animal implements Comparable<Animal> {
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

    //zwraca gen ktorego teraz użyjemy;
    public int getGen(){
        return this.genes.get(genIndex);
    }
    public Vector2d getPosition(){
        return this.position;
    }
    public void updateIndex(int  a){
        this.genIndex = (this.genIndex + a)%10; //dzielimy przez dlugosc genu, dalej nie pamietam ile to było
    }
    public String toString(){
        return "A";
    }

    public MapDirection getDirection() {
        return direction;
    }
    public void updateDirection(MapDirection direction){
        this.direction = direction;
    }
    public void updatePosition(Vector2d position){
        this.position = position;
    }
    public void updateEnergy(int energy){
        this.energy += energy;
    }
    public int getEnergy(){
        return this.energy;
    }


    @Override
    public int compareTo(Animal o) {
        return this.energy - o.energy;
    }
}
