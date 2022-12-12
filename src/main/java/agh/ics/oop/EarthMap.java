package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public class EarthMap{
    Vector2d lowerLeft = new Vector2d(0,0);
    Vector2d upperRight;
    Map<Vector2d,Animal[]> animals = new HashMap<>();

    public EarthMap(Vector2d upperRight){

        this.upperRight = upperRight;


    }

}
