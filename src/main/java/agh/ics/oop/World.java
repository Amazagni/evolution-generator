package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args){
//        TEST RUN WITHOUT GUI
//        EarthMap map = new EarthMap(new Vector2d(15,15));
//        SimulationEngine engine = new SimulationEngine(map,30);//30
//        engine.run();

//        RUN WITH GUI

        Application.launch(App.class, args);
    }
}
