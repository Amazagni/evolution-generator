package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EarthMap implements IWorldMap {
    Vector2d lowerLeft = new Vector2d(0,0);
    Vector2d upperRight;
    Map<Vector2d, ArrayList<Animal>> animals = new HashMap<>();
    Map<Vector2d,Grass> clumpsOfGrass = new HashMap<>();

    MapVisualizer toVisualize;
    public EarthMap(Vector2d upperRight, int grassQuantity, int animalQuantity){

        this.upperRight = upperRight;
        this.toVisualize = new MapVisualizer(this);
//        for(int i = 0; i < animalQuantity; i++){
//            placeRandomAnimal();
//        }
//        for(int i = 0; i < grassQuantity; i++){
//            plantTheGrass();
//        }
    }
//    public void placeRandomAnimal(){
//
//    }
    public void place(Animal animal){
        if(this.animals.get(animal.getPosition()) == null){

        }

    }

    @Override
    public Object objectAt(Vector2d position) {
         if(this.animals.get(position) != null)return this.animals.get(position);
         return this.clumpsOfGrass.get(position);
    }

    public void placeGrass(Vector2d position){
        this.clumpsOfGrass.put(position,new Grass(position));

    }
    public Vector2d getUpperRight(){
        return this.upperRight;
    }
    public String toString(){return this.toVisualize.draw(new Vector2d(0,0),getUpperRight());}

//    public void plantTheGrass(){
//
//    }
}
