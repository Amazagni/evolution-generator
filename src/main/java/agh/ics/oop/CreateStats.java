package agh.ics.oop;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CreateStats {
    private int numberOfAnimals;
    private int numberOfGrass;
    private int numberOfEmptyTiles;
    private int averageEnergyLevel;
    private int averageLifeLength;
    ArrayList<Integer> genes;
    private String name;

    public CreateStats(int numberOfAnimals, int numberOfGrass, int numberOfEmptyTiles,
                       int averageEnergyLevel, int averageLifeLength,ArrayList<Integer> genes, String name){
        this.numberOfAnimals = numberOfAnimals;
        this.numberOfGrass = numberOfGrass;
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.averageEnergyLevel = averageEnergyLevel;
        this.averageLifeLength = averageLifeLength;

        this.name = name;

    }
    private void PrintToFile() throws IOException {
        //FileWriter writer = new FileWriter();
    }


}
