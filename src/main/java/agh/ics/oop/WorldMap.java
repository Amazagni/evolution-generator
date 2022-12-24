package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;
public class WorldMap {
    Vector2d lowerLeft = new Vector2d(0,0);
    Vector2d upperRight;
    protected Map<Vector2d,Animal[]> animals = new HashMap<>();

    public WorldMap(Vector2d upperRight){

        this.upperRight = upperRight;


    }
}
