package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class App extends Application implements IAnimalMovementObserver {

    private GridPane mapGridPane = new GridPane();
    private SimulationEngine engine;
    private EarthMap map;

//    public void init() {}

    private void drawMap(EarthMap map, GridPane mapGridPane, boolean flag) {
        mapGridPane.setGridLinesVisible(false);
        Label yx = new Label("y/x");
        yx.setFont(new Font(20));
        mapGridPane.add(yx, 0, 0);
        GridPane.setHalignment(yx, HPos.CENTER);
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

    @Override
    public void start(Stage primaryStage) throws Exception {

//        LABELS
        Label name = new Label("Evolution Simulation");
        Label authors = new Label("by Marcin Chudy and Filip Dziurdzia");
        Label mapWidthLabel = new Label("Map width: ");
        Label mapHeightLabel = new Label("Map height: ");
        Label animalsNumberLabel = new Label("Starting number of animals: ");
        Label grassNumberLabel = new Label("Starting number of grass: ");
        Label dailyGrassGrowthLabel = new Label("Daily grass growth: ");
        Label startingEnergyLabel = new Label("Starting energy: ");
        Label moveEnergyLabel = new Label("Energy consumed on move: ");
        Label eatEnergyLabel = new Label("Energy gained by eating: ");
        Label reproductionEnergyLabel = new Label("Energy passed upon new animal by parents: ");
        Label minReproduceEnergyLabel = new Label("Minimum energy needed for reproduction: ");
        Label genLengthLabel = new Label("Number of genes in genome: ");
        Label minNumberOfMutationsLabel = new Label("Minimum number of mutations: ");
        Label maxNumberOfMutationsLabel = new Label("Minimum number of mutations: ");
        Label parametersLabel = new Label("Set your simulation's parameters");

//        LABELS SETTINGS
        name.setFont(new Font(48));
        name.setPadding(new Insets(0, 0, 20, 0));
        authors.setFont(new Font(20));
        authors.setTranslateX(52);
        authors.setPadding(new Insets(20, 0, 0, 0));
        mapHeightLabel.setTranslateY(4);
        mapHeightLabel.setPadding(new Insets(0, 10, 0, 0));
        mapWidthLabel.setTranslateY(4);
        mapWidthLabel.setPadding(new Insets(0, 10, 0, 0));
        animalsNumberLabel.setTranslateY(4);
        animalsNumberLabel.setPadding(new Insets(0, 10, 0, 0));
        grassNumberLabel.setTranslateY(4);
        grassNumberLabel.setPadding(new Insets(0, 10, 0, 0));
        dailyGrassGrowthLabel.setTranslateY(4);
        dailyGrassGrowthLabel.setPadding(new Insets(0, 10, 0, 0));
        startingEnergyLabel.setTranslateY(4);
        startingEnergyLabel.setPadding(new Insets(0, 10, 0, 0));
        moveEnergyLabel.setTranslateY(4);
        moveEnergyLabel.setPadding(new Insets(0, 10, 0, 0));
        eatEnergyLabel.setTranslateY(4);
        eatEnergyLabel.setPadding(new Insets(0, 10, 0, 0));
        reproductionEnergyLabel.setTranslateY(4);
        reproductionEnergyLabel.setPadding(new Insets(0, 10, 0, 0));
        minReproduceEnergyLabel.setTranslateY(4);
        minReproduceEnergyLabel.setPadding(new Insets(0, 10, 0, 0));
        genLengthLabel.setTranslateY(4);
        genLengthLabel.setPadding(new Insets(0, 10, 0, 0));
        minNumberOfMutationsLabel.setPadding(new Insets(0, 10, 0, 0));
        minNumberOfMutationsLabel.setTranslateY(4);
        maxNumberOfMutationsLabel.setPadding(new Insets(0, 10, 0, 0));
        maxNumberOfMutationsLabel.setTranslateY(4);
        parametersLabel.setFont(new Font(24));
        parametersLabel.setPadding(new Insets(0, 0, 20, 0));

//        TEXT FIELDS
        TextField mapWidth = new TextField("15");
        TextField mapHeight = new TextField("15");
        TextField animalsNumber = new TextField("10");
        TextField grassNumber = new TextField("20");
        TextField dailyGrassGrowth = new TextField("20");
        TextField startingEnergy = new TextField("120");
        TextField moveEnergy = new TextField("5");
        TextField eatEnergy = new TextField("40");
        TextField reproductionEnergy = new TextField("30");
        TextField minReproductionEnergy = new TextField("25");
        TextField genLength = new TextField("32");
        TextField minNumberOfMutations = new TextField("3");
        TextField maxNumberOfMutations = new TextField("5");

//        TEXT FIELDS SETTINGS
        mapWidth.setMaxWidth(60);
        mapHeight.setMaxWidth(60);
        animalsNumber.setMaxWidth(60);
        grassNumber.setMaxWidth(60);
        dailyGrassGrowth.setMaxWidth(60);
        startingEnergy.setMaxWidth(60);
        moveEnergy.setMaxWidth(60);
        eatEnergy.setMaxWidth(60);
        reproductionEnergy.setMaxWidth(60);
        minReproductionEnergy.setMaxWidth(60);
        genLength.setMaxWidth(60);
        minNumberOfMutations.setMaxWidth(60);
        maxNumberOfMutations.setMaxWidth(60);

//        RADIO BUTTONS
        ToggleGroup mapRadios = new ToggleGroup();
        RadioButton earth = new RadioButton("Earth");
        RadioButton hellPortal = new RadioButton("Hell Portal");
        earth.setSelected(true);
        earth.setToggleGroup(mapRadios);
        hellPortal.setToggleGroup(mapRadios);
        hellPortal.setTranslateX(120);

        ToggleGroup mapTypeRadios = new ToggleGroup();
        RadioButton forest = new RadioButton("Equatorial Forest");
        RadioButton corpses = new RadioButton("Toxic Corpses");
        forest.setSelected(true);
        forest.setToggleGroup(mapTypeRadios);
        corpses.setToggleGroup(mapTypeRadios);
        corpses.setTranslateX(58);

        ToggleGroup mutationRadios = new ToggleGroup();
        RadioButton slight = new RadioButton("Slight gen mutations");
        RadioButton random = new RadioButton("Random gen mutations");
        slight.setSelected(true);
        slight.setToggleGroup(mutationRadios);
        random.setToggleGroup(mutationRadios);
        random.setTranslateX(38);

        ToggleGroup behaviourRadios = new ToggleGroup();
        RadioButton following = new RadioButton("Animal follows its genome");
        RadioButton crazy = new RadioButton("Animal randomly choosing genes");
        following.setSelected(true);
        following.setToggleGroup(behaviourRadios);
        crazy.setToggleGroup(behaviourRadios);
        crazy.setTranslateX(8);

//        BUTTONS
        Button setParametersButton = new Button("Start new simulation");
        setParametersButton.setTranslateX(130);
        setParametersButton.setTranslateY(20);
        Button startSimulationButton = new Button("Start simulation");
        Button stopSimulationButton = new Button("Stop simulation");

//        IMAGE
        Image mainImage = new Image(new FileInputStream("src/main/resources/mainImage.png"));
        ImageView mainImageView = new ImageView(mainImage);
        mainImageView.setX(20);

//        HBOX'ES AND VBOX'ES
        VBox nameWithImage = new VBox(name, mainImageView, authors);
        nameWithImage.setTranslateX(100);
        nameWithImage.setTranslateY(65);
        HBox mapWidthBox = new HBox(mapWidthLabel, mapWidth);
        mapWidthBox.setPadding(new Insets(10, 0, 10, 0));
        HBox mapHeightBox = new HBox(mapHeightLabel, mapHeight);
        mapHeightBox.setPadding(new Insets(0, 0, 10, 0));
        HBox animalsNumberBox = new HBox(animalsNumberLabel, animalsNumber);
        animalsNumberBox.setPadding(new Insets(0, 0, 10, 0));
        HBox grassNumberBox = new HBox(grassNumberLabel, grassNumber);
        grassNumberBox.setPadding(new Insets(0, 0, 10, 0));
        HBox dailyGrassGrowthBox = new HBox(dailyGrassGrowthLabel, dailyGrassGrowth);
        dailyGrassGrowthBox.setPadding(new Insets(0, 0, 10, 0));
        HBox startingEnergyBox = new HBox(startingEnergyLabel, startingEnergy);
        startingEnergyBox.setPadding(new Insets(0, 0, 10, 0));
        HBox moveEnergyBox = new HBox(moveEnergyLabel, moveEnergy);
        moveEnergyBox.setPadding(new Insets(0, 0, 10, 0));
        HBox eatEnergyBox = new HBox(eatEnergyLabel, eatEnergy);
        eatEnergyBox.setPadding(new Insets(0, 0, 10, 0));
        HBox reproductionEnergyBox = new HBox(reproductionEnergyLabel, reproductionEnergy);
        reproductionEnergyBox.setPadding(new Insets(0, 0, 10, 0));
        HBox minReproductionEnergyBox = new HBox(minReproduceEnergyLabel, minReproductionEnergy);
        minReproductionEnergyBox.setPadding(new Insets(0, 0, 10, 0));
        HBox genLengthBox = new HBox(genLengthLabel, genLength);
        genLengthBox.setPadding(new Insets(0, 0, 10, 0));
        HBox mapRadiosBox = new HBox(earth, hellPortal);
        mapRadiosBox.setPadding(new Insets(10, 0, 10, 0));
        HBox mapTypeRadiosBox = new HBox(forest, corpses);
        mapTypeRadiosBox.setPadding(new Insets(0, 0, 10, 0));
        HBox mutationRadiosBox = new HBox(slight, random);
        mutationRadiosBox.setPadding(new Insets(0, 0, 10, 0));
        HBox behaviourRadiosBox = new HBox(following, crazy);
        behaviourRadiosBox.setPadding(new Insets(0, 0, 10, 0));
        HBox minNumberOfMutationsBox = new HBox(minNumberOfMutationsLabel, minNumberOfMutations);
        minNumberOfMutationsBox.setPadding(new Insets(0, 0, 10, 0));
        HBox maxNumberOfMutationsBox = new HBox(maxNumberOfMutationsLabel, maxNumberOfMutations);
        maxNumberOfMutationsBox.setPadding(new Insets(0, 0, 10, 0));
        HBox buttonsBox = new HBox(startSimulationButton, stopSimulationButton);

        VBox settings = new VBox(
                parametersLabel, mapWidthBox, mapHeightBox, animalsNumberBox, grassNumberBox,
                dailyGrassGrowthBox, startingEnergyBox, moveEnergyBox, eatEnergyBox, reproductionEnergyBox,
                minReproductionEnergyBox, genLengthBox, minNumberOfMutationsBox, maxNumberOfMutationsBox,
                mapRadiosBox, mapTypeRadiosBox, mutationRadiosBox, behaviourRadiosBox, setParametersButton
            );

        HBox firstView = new HBox(nameWithImage, settings);
        VBox appBox = new VBox(firstView);

        settings.setTranslateX(350);
        settings.setTranslateY(80);

        VBox mapBox = new VBox(this.mapGridPane);
        mapBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(appBox, 1280, 960);
        primaryStage.setTitle("Evolution Simulator");
        Image Icon = new Image(new FileInputStream("src/main/resources/icon.png"));

        primaryStage.getIcons().add(Icon);
        primaryStage.setScene(scene);
        primaryStage.show();



//        BUTTONS ACTIONS
        setParametersButton.setOnAction(event -> {
//            Getting data from the text areas

//            changing the view
//            firstView.setVisible(false);
//            firstView.setManaged(false);
            this.map = new EarthMap(new Vector2d(
                    Integer.parseInt(mapWidth.getText()),
                    Integer.parseInt(mapHeight.getText())));
            this.engine = new SimulationEngine(
                    this.map,
                    Integer.parseInt(animalsNumber.getText()),
                    Integer.parseInt(grassNumber.getText()),
                    Integer.parseInt(dailyGrassGrowth.getText()),
                    Integer.parseInt(startingEnergy.getText()),
                    Integer.parseInt(moveEnergy.getText()),
                    Integer.parseInt(eatEnergy.getText()),
                    Integer.parseInt(reproductionEnergy.getText()),
                    Integer.parseInt(minReproductionEnergy.getText()),
                    Integer.parseInt(genLength.getText()),
                    Integer.parseInt(minNumberOfMutations.getText()),
                    Integer.parseInt(maxNumberOfMutations.getText())
                    );
            this.engine.addObserver(this);
            Thread engineThread = new Thread(this.engine);
            engineThread.start();
            drawMap(this.map, this.mapGridPane, false);
            Scene simulationScene = new Scene(mapBox, 1280, 960);
            Stage simulationStage = new Stage();
            simulationStage.setScene(simulationScene);
            simulationStage.show();
        });

    }

    @Override
    public void animalMoved() {
        Platform.runLater(() -> {
//            UPDATE EVERY MAP
            mapGridPane.getChildren().clear();
            drawMap(this.map, this.mapGridPane, true);

//            UPDATE THE CHARTS
        });
    }
}
