package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public class EarthMap implements IWorldMap {
    Vector2d lowerLeft = new Vector2d(0,0);
    Vector2d upperRight;
    Map<Vector2d,Animal[]> animals = new HashMap<>();
    Map<Vector2d,Grass> clumpsOfGrass = new HashMap<>();

    public EarthMap(Vector2d upperRight, int grassQuantity, int animalQuantity){

        this.upperRight = upperRight;
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

    }

    @Override
    public Object objectAt(Vector2d position) {
         return this.animals.get(position);
    }

    public void placeGrass(Vector2d position){

    }
    public Vector2d getUpperRight(){
        return this.upperRight;
    }
//    public void plantTheGrass(){
//
//    }
}
