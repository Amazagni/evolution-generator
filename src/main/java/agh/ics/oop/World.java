package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class World {

    public static void main(String[] args) throws IOException {
//        TEST RUN WITHOUT GUI
//        EarthMap map = new EarthMap(new Vector2d(15,15));
//        SimulationEngine engine = new SimulationEngine(map,30);//30
//        engine.run();

//        RUN WITH GUI
//        SaveToFile save = new SaveToFile();
       // save.CreateFile();
        Application.launch(App.class, args);
    }
}
