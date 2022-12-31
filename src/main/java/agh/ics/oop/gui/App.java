package agh.ics.oop.gui;

import agh.ics.oop.EarthMap;
import agh.ics.oop.IGameElement;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.Vector2d;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class App extends Application implements IAnimalMovementObserver{

    private GridPane mapGridPane = new GridPane();
    private SimulationEngine engine;
    private EarthMap map;

//    public void init() {}

    private void drawMap(EarthMap map, GridPane mapGridPane, boolean flag) throws FileNotFoundException {
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
        if (!flag) {
            for (int a = 0; a <= width + 1; a++)
                mapGridPane.getColumnConstraints().add(new ColumnConstraints(25));
            for (int b = 0; b <= height + 1; b++)
                mapGridPane.getRowConstraints().add(new RowConstraints(25));
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

//        LABELS
        Label name = new Label("Evolution Simulation");
        name.setFont(new Font(48));
        name.setPadding(new Insets(0, 0, 20, 0));
        name.setAlignment(Pos.CENTER);
        name.setTextAlignment(TextAlignment.CENTER);
        Label mapWidthLabel = new Label("Map width: ");
        Label mapHeightLabel = new Label("Map height: ");
        Label animalsNumberLabel = new Label("Starting number of animals: ");
        Label grassNumberLabel = new Label("Starting number of grass: ");
        Label startingEnergyLabel = new Label("Starting energy: ");
        Label moveEnergyLabel = new Label("Energy consumed on move: ");
        Label eatEnergyLabel = new Label("Energy gained by eating: ");
        Label reproductionEnergyLabel = new Label("Energy passed upon new animal by parents: ");
        Label minReproduceEnergyLabel = new Label("Minimum energy needed for reproduction: ");
        Label genLengthLabel = new Label("Number of genes in genome: ");

//        TEXT FIELDS
        TextField mapWidth = new TextField("15");
        TextField mapHeight = new TextField("15");
        TextField animalsNumber = new TextField("10");
        TextField grassNumber = new TextField("20");
        TextField startingEnergy = new TextField("120");
        TextField moveEnergy = new TextField("5");
        TextField eatEnergy = new TextField("40");
        TextField reproductionEnergy = new TextField("30");
        TextField minReproductionEnergy = new TextField("25");
        TextField genLength = new TextField("32");

//        TEXT FIELDS SETTINGS
        mapWidth.setMaxWidth(60);
        mapHeight.setMaxWidth(60);
        animalsNumber.setMaxWidth(60);
        grassNumber.setMaxWidth(60);
        startingEnergy.setMaxWidth(60);
        moveEnergy.setMaxWidth(60);
        eatEnergy.setMaxWidth(60);
        reproductionEnergy.setMaxWidth(60);
        minReproductionEnergy.setMaxWidth(60);
        genLength.setMaxWidth(60);
//        RADIO BUTTONS
        ToggleGroup mapRadios = new ToggleGroup();
        RadioButton earth = new RadioButton("Earth");
        RadioButton hellPortal = new RadioButton("Hell Portal");
        earth.setSelected(true);
        earth.setToggleGroup(mapRadios);
        hellPortal.setToggleGroup(mapRadios);

        ToggleGroup mapTypeRadios = new ToggleGroup();
        RadioButton forest = new RadioButton("Equatorial Forest");
        RadioButton corpses = new RadioButton("Toxic Corpses");
        forest.setSelected(true);
        forest.setToggleGroup(mapTypeRadios);
        corpses.setToggleGroup(mapTypeRadios);

        ToggleGroup mutationRadios = new ToggleGroup();
        RadioButton slight = new RadioButton("Slight gen mutations");
        RadioButton random = new RadioButton("Random gen mutations");
        slight.setSelected(true);
        slight.setToggleGroup(mutationRadios);
        random.setToggleGroup(mutationRadios);

        ToggleGroup behaviourRadios = new ToggleGroup();
        RadioButton following = new RadioButton("Animal follows its genome");
        RadioButton crazy = new RadioButton("Animal randomly choosing genes");
        following.setSelected(true);
        following.setToggleGroup(behaviourRadios);
        crazy.setToggleGroup(behaviourRadios);

//        BUTTONS
        Button setParametersButton = new Button("Set");
        Button startSimulationButton = new Button("Start simulation");
        Button stopSimulationButton = new Button("Stop simulation");

//        IMAGE
        Image mainImage = new Image(new FileInputStream("src/main/resources/mainImage.png"));
        ImageView mainImageView = new ImageView(mainImage);
        mainImageView.setX(20);

//        HBOX'ES AND VBOX'ES
        VBox nameWithImage = new VBox(name, mainImageView, startSimulationButton);
        HBox mapWidthBox = new HBox(mapWidthLabel, mapWidth);
        HBox mapHeightBox = new HBox(mapHeightLabel, mapHeight);
        HBox animalsNumberBox = new HBox(animalsNumberLabel, animalsNumber);
        HBox grassNumberBox = new HBox(grassNumberLabel, grassNumber);
        HBox startingEnergyBox = new HBox(startingEnergyLabel, startingEnergy);
        HBox moveEnergyBox = new HBox(moveEnergyLabel, moveEnergy);
        HBox eatEnergyBox = new HBox(eatEnergyLabel, eatEnergy);
        HBox reproductionEnergyBox = new HBox(reproductionEnergyLabel, reproductionEnergy);
        HBox minReproductionEnergyBox = new HBox(minReproduceEnergyLabel, minReproductionEnergy);
        HBox genLengthBox = new HBox(genLengthLabel, genLength);
        HBox mapRadiosBox = new HBox(earth, hellPortal);
        HBox mapTypeRadiosBox = new HBox(forest, corpses);
        HBox mutationRadiosBox = new HBox(slight, random);
        HBox behaviourRadiosBox = new HBox(following, crazy);

        VBox settings = new VBox(
                mapWidthBox, mapHeightBox, animalsNumberBox, grassNumberBox,
                startingEnergyBox, moveEnergyBox, eatEnergyBox, reproductionEnergyBox,
                minReproductionEnergyBox, genLengthBox, mapRadiosBox, mapTypeRadiosBox,
                mutationRadiosBox, behaviourRadiosBox
            );

        VBox mapBox = new VBox(this.mapGridPane);
        mapBox.setAlignment(Pos.CENTER);
        HBox test = new HBox(nameWithImage, settings);

        Scene scene = new Scene(test, 1280, 960);
        primaryStage.setTitle("Evolution Simulator");
        Image Icon = new Image(new FileInputStream("src/main/resources/icon.png"));

        primaryStage.getIcons().add(Icon);
        primaryStage.setScene(scene);
        primaryStage.show();
        this.map = new EarthMap(new Vector2d(15, 15));
        this.engine = new SimulationEngine(this.map, 10);
        Thread engineThread = new Thread(this.engine);
        engineThread.start();
        drawMap(this.map, this.mapGridPane, false);
    }

    @Override
    public void animalMoved() {
        Platform.runLater(() -> {
            mapGridPane.getChildren().clear();
            try {
                drawMap(this.map, this.mapGridPane, true);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
