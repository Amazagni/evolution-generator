package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class App extends Application {

    private GridPane mapGridPane = new GridPane();
    private SimulationEngine engine;
    private EarthMap map;

    private final XYChart.Series<Number, Number> animalsChartSeries = new XYChart.Series<>();
    private final XYChart.Series<Number, Number> plantsChartSeries = new XYChart.Series<>();
    private final XYChart.Series<Number, Number> avgEnergyChartSeries = new XYChart.Series<>();
    private final XYChart.Series<Number, Number> avgKidsChartSeries = new XYChart.Series<>();
    private final XYChart.Series<Number, Number> avgLifeSpanChartSeries = new XYChart.Series<>();
    private ArrayList<XYChart.Series<Number, Number>> chartSeriesArrW1;

    @Override
    public void start(Stage primaryStage) throws Exception {

//        EXTERNAL DATA
        ConfigReader data1 = new ConfigReader();
        data1.readFile("config1");
        ConfigReader data2 = new ConfigReader();
        data2.readFile("config2");
        ConfigReader data3 = new ConfigReader();
        data3.readFile("config3");

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
        Label maxNumberOfMutationsLabel = new Label("Maximum number of mutations: ");
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
        TextField mapWidth = new TextField(String.valueOf(data1.mapWidth));
        TextField mapHeight = new TextField(String.valueOf(data1.mapHeight));
        TextField animalsNumber = new TextField(String.valueOf(data1.animalNumber));
        TextField grassNumber = new TextField(String.valueOf(data1.grassNumber));
        TextField dailyGrassGrowth = new TextField(String.valueOf(data1.dailyGrassGrowth));
        TextField startingEnergy = new TextField(String.valueOf(data1.startingEnergy));
        TextField moveEnergy = new TextField(String.valueOf(data1.moveEnergy));
        TextField eatEnergy = new TextField(String.valueOf(data1.eatEnergy));
        TextField reproductionEnergy = new TextField(String.valueOf(data1.reproductionEnergy));
        TextField minReproductionEnergy = new TextField(String.valueOf(data1.minReproductionEnergy));
        TextField genLength = new TextField(String.valueOf(data1.genLength));
        TextField minNumberOfMutations = new TextField(String.valueOf(data1.minNumberOfMutations));
        TextField maxNumberOfMutations = new TextField(String.valueOf(data1.maxNumberOfMutations));

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
        earth.setSelected(data1.earth);
        earth.setToggleGroup(mapRadios);
        hellPortal.setToggleGroup(mapRadios);
        hellPortal.setTranslateX(120);
        hellPortal.setSelected(!data1.earth);

        ToggleGroup mapTypeRadios = new ToggleGroup();
        RadioButton forest = new RadioButton("Equatorial Forest");
        RadioButton corpses = new RadioButton("Toxic Corpses");
        forest.setSelected(data1.forest);
        forest.setToggleGroup(mapTypeRadios);
        corpses.setToggleGroup(mapTypeRadios);
        corpses.setTranslateX(58);
        corpses.setSelected(!data1.forest);

        ToggleGroup mutationRadios = new ToggleGroup();
        RadioButton slight = new RadioButton("Slight gen mutations");
        RadioButton random = new RadioButton("Random gen mutations");
        slight.setSelected(data1.slight);
        slight.setToggleGroup(mutationRadios);
        random.setToggleGroup(mutationRadios);
        random.setTranslateX(38);
        random.setSelected(!data1.slight);

        ToggleGroup behaviourRadios = new ToggleGroup();
        RadioButton following = new RadioButton("Animal follows its genome");
        RadioButton crazy = new RadioButton("Animal randomly choosing genes");
        following.setSelected(data1.following);
        following.setToggleGroup(behaviourRadios);
        crazy.setToggleGroup(behaviourRadios);
        crazy.setTranslateX(8);
        crazy.setSelected(!data1.following);

        ToggleGroup configRadios = new ToggleGroup();
        RadioButton radio1 = new RadioButton("1");
        radio1.setSelected(true);
        radio1.setToggleGroup(configRadios);
        RadioButton radio2 = new RadioButton("2");
        radio2.setPadding(new Insets(0, 20, 0, 20));
        radio2.setToggleGroup(configRadios);
        RadioButton radio3 = new RadioButton("3");
        radio3.setToggleGroup(configRadios);

//        CONFIG RADIOS FUNCTIONALITY
        configRadios.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if(configRadios.getSelectedToggle().equals(radio1)) {
                    mapWidth.setText(String.valueOf(data1.mapWidth));
                    mapHeight.setText(String.valueOf(data1.mapHeight));
                    animalsNumber.setText(String.valueOf(data1.animalNumber));
                    grassNumber.setText(String.valueOf(data1.grassNumber));
                    dailyGrassGrowth.setText(String.valueOf(data1.dailyGrassGrowth));
                    startingEnergy.setText(String.valueOf(data1.startingEnergy));
                    moveEnergy.setText(String.valueOf(data1.moveEnergy));
                    eatEnergy.setText(String.valueOf(data1.eatEnergy));
                    reproductionEnergy.setText(String.valueOf(data1.reproductionEnergy));
                    minReproductionEnergy.setText(String.valueOf(data1.minReproductionEnergy));
                    genLength.setText(String.valueOf(data1.genLength));
                    minNumberOfMutations.setText(String.valueOf(data1.minNumberOfMutations));
                    maxNumberOfMutations.setText(String.valueOf(data1.maxNumberOfMutations));
                    earth.setSelected(data1.earth);
                    hellPortal.setSelected(!data1.earth);
                    forest.setSelected(data1.forest);
                    corpses.setSelected(!data1.forest);
                    slight.setSelected(data1.slight);
                    random.setSelected(!data1.slight);
                    following.setSelected(data1.following);
                    crazy.setSelected(!data1.following);
                }
                else if(configRadios.getSelectedToggle().equals(radio2)) {
                    mapWidth.setText(String.valueOf(data2.mapWidth));
                    mapHeight.setText(String.valueOf(data2.mapHeight));
                    animalsNumber.setText(String.valueOf(data2.animalNumber));
                    grassNumber.setText(String.valueOf(data2.grassNumber));
                    dailyGrassGrowth.setText(String.valueOf(data2.dailyGrassGrowth));
                    startingEnergy.setText(String.valueOf(data2.startingEnergy));
                    moveEnergy.setText(String.valueOf(data2.moveEnergy));
                    eatEnergy.setText(String.valueOf(data2.eatEnergy));
                    reproductionEnergy.setText(String.valueOf(data2.reproductionEnergy));
                    minReproductionEnergy.setText(String.valueOf(data2.minReproductionEnergy));
                    genLength.setText(String.valueOf(data2.genLength));
                    minNumberOfMutations.setText(String.valueOf(data2.minNumberOfMutations));
                    maxNumberOfMutations.setText(String.valueOf(data2.maxNumberOfMutations));
                    earth.setSelected(data2.earth);
                    hellPortal.setSelected(!data2.earth);
                    forest.setSelected(data2.forest);
                    corpses.setSelected(!data2.forest);
                    slight.setSelected(data2.slight);
                    random.setSelected(!data2.slight);
                    following.setSelected(data2.following);
                    crazy.setSelected(!data2.following);
                }
                else if(configRadios.getSelectedToggle().equals(radio3)) {
                    mapWidth.setText(String.valueOf(data3.mapWidth));
                    mapHeight.setText(String.valueOf(data3.mapHeight));
                    animalsNumber.setText(String.valueOf(data3.animalNumber));
                    grassNumber.setText(String.valueOf(data3.grassNumber));
                    dailyGrassGrowth.setText(String.valueOf(data3.dailyGrassGrowth));
                    startingEnergy.setText(String.valueOf(data3.startingEnergy));
                    moveEnergy.setText(String.valueOf(data3.moveEnergy));
                    eatEnergy.setText(String.valueOf(data3.eatEnergy));
                    reproductionEnergy.setText(String.valueOf(data3.reproductionEnergy));
                    minReproductionEnergy.setText(String.valueOf(data3.minReproductionEnergy));
                    genLength.setText(String.valueOf(data3.genLength));
                    minNumberOfMutations.setText(String.valueOf(data3.minNumberOfMutations));
                    maxNumberOfMutations.setText(String.valueOf(data3.maxNumberOfMutations));
                    earth.setSelected(data3.earth);
                    hellPortal.setSelected(!data3.earth);
                    forest.setSelected(data3.forest);
                    corpses.setSelected(!data3.forest);
                    slight.setSelected(data3.slight);
                    random.setSelected(!data3.slight);
                    following.setSelected(data3.following);
                    crazy.setSelected(!data3.following);
                }
            }
        });

//        BUTTONS
        Button setParametersButton = new Button("Start new simulation");
        setParametersButton.setTranslateX(130);
        setParametersButton.setTranslateY(20);
        setParametersButton.setDefaultButton(true);

//        CHECKBOXES
        CheckBox energy = new CheckBox("Show energy indicator");
        CheckBox data = new CheckBox("Export data");
        data.setTranslateX(27);

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
        HBox radiosConfigBox = new HBox(radio1, radio2, radio3);
        HBox checkboxes = new HBox(energy, data);

        VBox settings = new VBox(
                parametersLabel, radiosConfigBox, mapWidthBox, mapHeightBox, animalsNumberBox, grassNumberBox,
                dailyGrassGrowthBox, startingEnergyBox, moveEnergyBox, eatEnergyBox, reproductionEnergyBox,
                minReproductionEnergyBox, genLengthBox, minNumberOfMutationsBox, maxNumberOfMutationsBox, checkboxes,
                mapRadiosBox, mapTypeRadiosBox, mutationRadiosBox, behaviourRadiosBox, setParametersButton
            );

        HBox firstView = new HBox(nameWithImage, settings);
        VBox appBox = new VBox(firstView);

        settings.setTranslateX(350);
        settings.setTranslateY(80);

        Scene scene = new Scene(appBox, 1280, 960);
        primaryStage.setTitle("Evolution Simulator");
        Image Icon = new Image(new FileInputStream("src/main/resources/icon.png"));

        primaryStage.getIcons().add(Icon);
        primaryStage.setScene(scene);
        primaryStage.show();

//        BUTTONS ACTIONS
        setParametersButton.setOnAction(event -> {
            try {
                Scene simulationScene = new Simulation(
                        Integer.parseInt(mapWidth.getText()),
                        Integer.parseInt(mapHeight.getText()),
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
                        Integer.parseInt(maxNumberOfMutations.getText()),
                        earth.isSelected(), forest.isSelected(), slight.isSelected(), following.isSelected(),
                        energy.isSelected(), data.isSelected()).simulationScene;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
