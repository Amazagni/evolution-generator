package agh.ics.oop;

public class World {
    public static void main(String[] args){
        EarthMap map = new EarthMap(new Vector2d(15,15));
        SimulationEngine engine = new SimulationEngine(map,30);
        engine.run();


    }
}
