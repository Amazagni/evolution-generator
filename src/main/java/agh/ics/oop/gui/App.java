package agh.ics.oop.gui;

import agh.ics.oop.EarthMap;
import agh.ics.oop.IGameElement;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.Vector2d;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class App extends Application {

    private GridPane mapGridPane = new GridPane();
    private SimulationEngine engine;
    private EarthMap map;

//    public void init() {}

    private void runSimulation(EarthMap map, GridPane mapGridPane) throws FileNotFoundException {
        mapGridPane.setGridLinesVisible(false);
        Label yx = new Label("y/x");
        yx.setFont(new Font(20));
        mapGridPane.add(yx, 0, 0);
        GridPane.setHalignment(yx, HPos.CENTER);
        GUIElement generator;
        generator = new GUIElement();
        int height = map.getUpperRight().y;
        int width = map.getUpperRight().x;
        Vector2d currentPosition = new Vector2d(0, 0);
        for (int i = 0; i <= width; i++) {
            for (int j = 0; j <= height; j++) {
                currentPosition = new Vector2d(i, j);
                StackPane tile = generator.GUIMapElement(map, (IGameElement) map.objectAt(currentPosition), currentPosition, engine);
                mapGridPane.add(tile, i, j);
                GridPane.setHalignment(tile, HPos.CENTER);
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox mapBox = new VBox(this.mapGridPane);
        mapBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(mapBox, 1280, 960);
        primaryStage.setScene(scene);
        primaryStage.show();
        this.map = new EarthMap(new Vector2d(15, 15));
        this.engine = new SimulationEngine(this.map, 10);
        this.engine.run();
        runSimulation(map, mapGridPane);
    }
}
