package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GUIElement {

    Image grassImage;
    Image jungleImage;
    Image bushImage;
    Image animalImage;

    public GUIElement() throws FileNotFoundException {
        try {
            this.grassImage = new Image(new FileInputStream("../../../../resources/emptyTile.png"));
            this.jungleImage = new Image(new FileInputStream("../../../../resources/jungle.png"));
            this.bushImage = new Image(new FileInputStream("../../../../resources/grass.png"));
            this.animalImage = new Image(new FileInputStream("../../../../resources/animal.png"));
        }
        catch (FileNotFoundException exception) {
            System.out.println("System couldn't find that file: " + exception);
        }
    }

    public StackPane GUIMapElement(EarthMap map, IGameElement gameElement, Vector2d position, SimulationEngine engine) {
        ImageView groundTile;
        ImageView gameElementImage;
        boolean isForest = false;
        if(gameElement instanceof Animal) {
            // getting the animal image facing north
            gameElementImage = new ImageView(animalImage);
            // rotating the animal depending on its direction
            switch (((Animal) gameElement).getDirection()) {
                case NORTH -> gameElementImage.setRotate(0);
                case NORTH_EAST -> gameElementImage.setRotate(45);
                case EAST -> gameElementImage.setRotate(90);
                case SOUTH_EAST -> gameElementImage.setRotate(135);
                case SOUTH -> gameElementImage.setRotate(180);
                case SOUTH_WEST -> gameElementImage.setRotate(225);
                case WEST -> gameElementImage.setRotate(270);
                case NORTH_WEST -> gameElementImage.setRotate(315);
            };
            //changing the view depending on the energy
        }
        if (gameElement instanceof Grass) {
            // getting the bush (grass) image
            gameElementImage = new ImageView(bushImage);
        }
        else {
            gameElementImage = new ImageView(grassImage);
            gameElementImage.setImage(null);
        }
        // setting the background (tile) image depending on if it's the steppe or the forest on the equatorial
        if (engine.isForestTile(position)) {
            groundTile = new ImageView(jungleImage);
        }
        else groundTile = new ImageView(grassImage);
        groundTile.setFitHeight(25);
        groundTile.setFitWidth(25);
        gameElementImage.setFitHeight(20);
        gameElementImage.setFitWidth(20);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(groundTile, gameElementImage);
        return stackPane;
    }
}
