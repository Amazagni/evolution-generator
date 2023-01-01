package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Simulation implements IAnimalMovementObserver{

    private GridPane mapGridPane = new GridPane();
    private SimulationEngine engine;
    private EarthMap map;
    public Scene simulationScene;

    private final XYChart.Series<Number, Number> animalsNumberChartSeries = new XYChart.Series<>();
    private final XYChart.Series<Number, Number> grassNumberChartSeries = new XYChart.Series<>();
    private final XYChart.Series<Number, Number> freeSpotsChartSeries = new XYChart.Series<>();
    private final XYChart.Series<Number, Number> avgEnergyChartSeries = new XYChart.Series<>();
    private final XYChart.Series<Number, Number> avgLifeSpanChartSeries = new XYChart.Series<>();
    private ArrayList<XYChart.Series<Number, Number>> chartSeriesArr;

    private void drawMap(EarthMap map, GridPane mapGridPane, boolean flag) {
        mapGridPane.setGridLinesVisible(false);
        GUIElement generator;
        int height = map.getUpperRight().y;
        int width = map.getUpperRight().x;
        try {
            generator = new GUIElement();
            Vector2d currentPosition = new Vector2d(0, 0);
            for (int i = 0; i <= width; i++) {
                for (int j = 0; j <= height; j++) {
                    currentPosition = new Vector2d(i, height - j);
                    StackPane tile = generator.GUIMapElement((IGameElement) map.objectAt(currentPosition), currentPosition, engine);
                    mapGridPane.add(tile, i, j);
                    GridPane.setHalignment(tile, HPos.CENTER);
                }
            }
        } catch (FileNotFoundException error) {
            System.out.println("Couldn't load necessary files...");
        }
        if (!flag) {
            Vector2d mapSize = map.getUpperRight();
            int biggerCord = Math.max(mapSize.x, mapSize.y);
            int tileSize = 25;
            if(biggerCord > 24) tileSize = 600/biggerCord;
            for (int a = 0; a <= width + 1; a++)
                mapGridPane.getColumnConstraints().add(new ColumnConstraints(tileSize));
            for (int b = 0; b <= height + 1; b++)
                mapGridPane.getRowConstraints().add(new RowConstraints(tileSize));
        }
    }

    public Simulation(int mapWidth, int mapHeight, int animalsNumber,
                      int grassNumber, int dailyGrassGrowth,int startingEnergy, int moveEnergy, int eatEnergy,
                      int reproductionEnergy, int minReproductionEnergy, int genLength, int minNumberOfMutations,
                      int maxNumberOfMutations
                      ) {
        this.mapGridPane.setPadding(new Insets(50, 50, 20, 50));
        Button startSimulationButton = new Button("Resume simulation");
        Button stopSimulationButton = new Button("Pause simulation");
        startSimulationButton.setVisible(false);
        startSimulationButton.setManaged(false);
        HBox buttonsBox = new HBox(startSimulationButton, stopSimulationButton);
        VBox mapBox = new VBox(this.mapGridPane, buttonsBox);

        this.map = new EarthMap(new Vector2d(mapWidth, mapHeight));
        this.engine = new SimulationEngine(
                this.map, animalsNumber, grassNumber, dailyGrassGrowth, startingEnergy, moveEnergy, eatEnergy,
                reproductionEnergy, minReproductionEnergy, genLength, minNumberOfMutations, maxNumberOfMutations
            );
        this.engine.addObserver(this);
        Thread engineThread = new Thread(this.engine);
        engineThread.start();
        drawMap(this.map, this.mapGridPane, false);
        if(this.map.getUpperRight().x < 25) buttonsBox.setTranslateX(5 + this.map.getUpperRight().x*25/2);
        else buttonsBox.setTranslateX(305);
        this.simulationScene = new Scene(mapBox, 1280, 960);
        Stage simulationStage = new Stage();
        simulationStage.setScene(this.simulationScene);
        simulationStage.show();

//       CHARTS
        final NumberAxis AxisX = new NumberAxis();
        final NumberAxis AxisY = new NumberAxis();
        final LineChart<Number, Number> chart = new LineChart<>(AxisX, AxisY);
        chart.setTitle("Statistics");

        this.animalsNumberChartSeries.setName("Animal count");
        this.grassNumberChartSeries.setName("Grass count");
        this.freeSpotsChartSeries.setName("Free spots");
        this.avgEnergyChartSeries.setName("Average energy");
        this.avgLifeSpanChartSeries.setName("Average life span");

        chart.getData().add(this.animalsNumberChartSeries);
        chart.getData().add(this.grassNumberChartSeries);
        chart.getData().add(this.freeSpotsChartSeries);
        chart.getData().add(this.avgEnergyChartSeries);
        chart.getData().add(this.avgLifeSpanChartSeries);

        this.chartSeriesArr = new ArrayList<>() {
            {
                add(animalsNumberChartSeries);
                add(grassNumberChartSeries);
                add(freeSpotsChartSeries);
                add(avgEnergyChartSeries);
                add(avgLifeSpanChartSeries);
            }
        };

        startSimulationButton.setOnAction(event -> {
            // resume the simulation
            this.engine.Start();
            startSimulationButton.setVisible(false);
            startSimulationButton.setManaged(false);
            stopSimulationButton.setVisible(true);
            stopSimulationButton.setManaged(true);
        });

        stopSimulationButton.setOnAction(event -> {
            // stop the simulation
            this.engine.Stop();
            startSimulationButton.setVisible(true);
            startSimulationButton.setManaged(true);
            stopSimulationButton.setVisible(false);
            stopSimulationButton.setManaged(false);
        });
    }

    @Override
    public void animalMoved() {
        Platform.runLater(() -> {
//            UPDATING MAP
            mapGridPane.getChildren().clear();
            drawMap(this.map, this.mapGridPane, true);

//            UPDATING CHARTS
//            this.animalsNumberChartSeries.getData().add(new XYChart.Data<>(this.engine., this.engine1.countAnimals()));
//            this.grassNumberChartSeries.getData().add(new XYChart.Data<>(this.eraCount, this.map1.getTotalGrassAmount()));
//            this.freeSpotsChartSeries.getData().add(new XYChart.Data<>(this.eraCount, this.engine1.getAvgLifeSpan()));
//            this.avgEnergyChartSeries.getData().add(new XYChart.Data<>(this.eraCount, this.engine1.getAvgEnergy()));
//            this.avgLifeSpanChartSeries.getData().add(new XYChart.Data<>(this.eraCount, this.engine1.getAvgChildrenAmount()));
        });
    }
}
