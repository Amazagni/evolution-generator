package agh.ics.oop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConfigReader {
    public int mapWidth;
    public int mapHeight;
    public int animalNumber;
    public int grassNumber;
    public int dailyGrassGrowth;
    public int startingEnergy;
    public int moveEnergy;
    public int eatEnergy;
    public int reproductionEnergy;
    public int minReproductionEnergy;
    public int genLength;
    public int minNumberOfMutations;
    public int maxNumberOfMutations;
    public boolean earth;
    public boolean forest;
    public boolean slight;
    public boolean following;

    public void readFile(String fileName) throws FileNotFoundException {
        File file = new File("configs/" + fileName);
        Scanner scanner = new Scanner(file);
        this.mapWidth = Integer.parseInt(scanner.nextLine());
        this.mapHeight = Integer.parseInt(scanner.nextLine());
        this.animalNumber = Integer.parseInt(scanner.nextLine());
        this.grassNumber = Integer.parseInt(scanner.nextLine());
        this.dailyGrassGrowth = Integer.parseInt(scanner.nextLine());
        this.startingEnergy = Integer.parseInt(scanner.nextLine());
        this.moveEnergy = Integer.parseInt(scanner.nextLine());
        this.eatEnergy = Integer.parseInt(scanner.nextLine());
        this.reproductionEnergy = Integer.parseInt(scanner.nextLine());
        this.minReproductionEnergy = Integer.parseInt(scanner.nextLine());
        this.genLength = Integer.parseInt(scanner.nextLine());
        this.minNumberOfMutations = Integer.parseInt(scanner.nextLine());
        this.maxNumberOfMutations = Integer.parseInt(scanner.nextLine());
        this.earth = "1".equals(scanner.nextLine());
        this.forest = "1".equals(scanner.nextLine());
        this.slight = "1".equals(scanner.nextLine());
        this.following = "1".equals(scanner.nextLine());
    }
}
