package agh.ics.oop.gui;

import agh.ics.oop.EarthMap;
import javafx.scene.image.Image;
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

//    public StackPane GUIMapElement(EarthMap map, )
}
