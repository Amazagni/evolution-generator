package agh.ics.oop;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveToFile {
    private int startingNumberOfAnimals = 15;
    private int grassEnergyGain = 80;//będzie trzeba to przypisać w konstruktorze
    private int dailyEnergyLoss = 5;  // - || -
    private int startingNumberOfGrass = 12;// - || -
    private int dailyGrassGrowth = 15;// - || -
    private int startingEnergy = 320;//   // - || -
    private int energyUsedToCreateAnimal = 90; //- || - ilosc energi ktora rodzice łącznie tracą przy rozmnażaniu
    private int minEnergyToReproduce = 110; // - || - min energia zeby zwierze moglo sie rozmnazac ps trzeba zmienić tą nazwe xd
    private int genLength = 22; //Nie pamiętam ile jak długa miała być ta tablica -- to ma być parametr wejściowy
    private int minNumberOfMutations = 6;
    private int maxNumberOfMutations = 11;

    private String name = "config3";
    public SaveToFile(){

    }
    public void CreateFile() throws IOException {
        FileWriter writer = new FileWriter(String.join("", ".\\configs\\", name ));
        writer.write((Integer.toString(startingNumberOfAnimals)));
        writer.write("\n");
        writer.write(Integer.toString(startingNumberOfGrass));
        writer.write("\n");
        writer.write(Integer.toString(dailyGrassGrowth));
        writer.write("\n");
        writer.write(Integer.toString(startingEnergy));
        writer.write("\n");
        writer.write(Integer.toString(dailyEnergyLoss));
        writer.write("\n");
        writer.write(Integer.toString(grassEnergyGain));
        writer.write("\n");
        writer.write(Integer.toString(energyUsedToCreateAnimal));
        writer.write("\n");
        writer.write(Integer.toString(minEnergyToReproduce));
        writer.write("\n");
        writer.write(Integer.toString(genLength));
        writer.write("\n");
        writer.write(Integer.toString(maxNumberOfMutations));
        writer.write("\n");
        writer.write(Integer.toString(minNumberOfMutations));
        writer.write("\n");
        writer.write("0"); //earth
        writer.write("\n");
        writer.write("1"); //hellPortal
        writer.write("\n");
        writer.write("1");//forestedEquators
        writer.write("\n");
        writer.write("0");//toxicCorpses
        writer.write("\n");
        writer.write("1");//slightlyChangedMutation
        writer.write("\n");
        writer.write("0");//randomMutation
        writer.write("\n");
        writer.write("0");//correctGenesOrder
        writer.write("\n");
        writer.write("1");//slightlyChangedGenesOrder
        writer.write("\n");
        writer.close();

    }
}
//    private boolean earth = true;
//    private boolean hellPortal = false;
//    public boolean forestedEquators = true;
//    private boolean toxicCorpses = false;
//    private boolean randomMutation = true;
//    private boolean slightlyChangedMutation = false;
//    private boolean correctGenesOrder = true;
//    private boolean slightlyChangedGenesOrder = false;