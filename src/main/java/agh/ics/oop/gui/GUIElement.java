package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.geometry.Insets;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GUIElement {

    Image grassImage;
    Image jungleImage;
    Image bushImage;
    Image animalImage;

    public GUIElement() throws FileNotFoundException {
        try {
            this.grassImage = new Image(new FileInputStream("src/main/resources/emptyTile.png"));
            this.jungleImage = new Image(new FileInputStream("src/main/resources/jungle.png"));
            this.bushImage = new Image(new FileInputStream("src/main/resources/grass.png"));
            this.animalImage = new Image(new FileInputStream("src/main/resources/animal.png"));
        }
        catch (FileNotFoundException exception) {
            System.out.println("System couldn't find that file: " + exception);
        }
    }

    public StackPane GUIMapElement(IGameElement gameElement, Vector2d position, SimulationEngine engine) {
        ImageView groundTile;
        ImageView gameElementImage;
        Vector2d mapSize = engine.getMap().getUpperRight();
        int biggerCord = Math.max(mapSize.x, mapSize.y);
        int tileSize = 50;
        if(biggerCord > 12) tileSize = 600/biggerCord;
        // setting the background (tile) image depending on if it's the steppe or the forest on the equatorial
        if (engine.isForestTile(position)) {
            groundTile = new ImageView(jungleImage);
        }
        else groundTile = new ImageView(grassImage);
        groundTile.setFitHeight(tileSize);
        groundTile.setFitWidth(tileSize);
        StackPane stackPane = new StackPane();
        if(gameElement instanceof Animal) {
            // getting the animal image facing north
            gameElementImage = new ImageView(animalImage);
            Circle indicator = new Circle(Math.max(0.1 * tileSize, 1));
            indicator.setFill(Color.rgb(Math.min(((Animal) gameElement).getEnergy() * 255/5*engine.getStartingEnergy(), 255), 0, 0));
            indicator.setTranslateX(0.4 * tileSize);
            indicator.setTranslateY(-0.4 * tileSize);
            // rotating the animal depending on its direction
            switch (((Animal) gameElement).getDirection()) {
                case NORTH -> gameElementImage.setRotate(gameElementImage.getRotate() + 0);
                case NORTH_EAST -> gameElementImage.setRotate(gameElementImage.getRotate() + 45);
                case EAST -> gameElementImage.setRotate(gameElementImage.getRotate() + 90);
                case SOUTH_EAST -> gameElementImage.setRotate(gameElementImage.getRotate() + 135);
                case SOUTH -> gameElementImage.setRotate(gameElementImage.getRotate() + 180);
                case SOUTH_WEST -> gameElementImage.setRotate(gameElementImage.getRotate() + 225);
                case WEST -> gameElementImage.setRotate(gameElementImage.getRotate() + 270);
                case NORTH_WEST -> gameElementImage.setRotate(gameElementImage.getRotate() + 315);
            };
            gameElementImage.setFitHeight(0.8 * tileSize);
            gameElementImage.setFitWidth(0.8 * tileSize);
            stackPane.getChildren().addAll(groundTile, gameElementImage, indicator);
        }
        else if (gameElement instanceof Grass) {
            // getting the bush (grass) image
            gameElementImage = new ImageView(bushImage);
            gameElementImage.setFitHeight(0.6 * tileSize);
            gameElementImage.setFitWidth(0.6 * tileSize);
            gameElementImage.setTranslateX(0.2 * tileSize);
            gameElementImage.setTranslateY(0.2 * tileSize);
        }
        else {
            gameElementImage = new ImageView(grassImage);
            gameElementImage.setImage(null);
        }
        if(!(gameElement instanceof Animal)) stackPane.getChildren().addAll(groundTile, gameElementImage);
        return stackPane;
    }
}
