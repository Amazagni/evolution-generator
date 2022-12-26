package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SimulationEngine {
    private List<Animal> animals = new ArrayList<>();
    private int energyGain;       //będzie trzeba to przypisać w konstruktorze
    private int dailyEnergyLoss;  // - || -
    private int startingEnergy = 50;   // - || -
    private int genLength = 10; //Nie pamiętam ile jak długa miała być ta tablica
    private EarthMap map;

    public SimulationEngine(EarthMap map, int animalsQuantity
    , int grassQuantity){
        this.map = map;
        for(int i = 0; i < animalsQuantity; i++){
            generateRandomAnimal();
        }
        for(int i = 0; i < grassQuantity; i++){
            generateRandomGrass();
        }

    }
    private void generateRandomAnimal(){
        int maxX = this.map.getUpperRight().x;
        int maxY = this.map.getUpperRight().y;
        //generuje x i y z przedzialu [0,max)
        //(max jest już poza naszą planszą)
        int newX = (int)(Math.random()*(maxX));
        int newY = (int)(Math.random()*(maxY));

        Vector2d position = new Vector2d(newX,newY);
        ArrayList<Integer> genes = new ArrayList<Integer>();
        for(int i = 0; i < this.genLength; i++){
            genes.add((int)(Math.random()*8));
        }
        Animal animal = new Animal(position,genes,this.startingEnergy);
        this.animals.add(animal);
        this.map.place(animal);
    }
    private void generateRandomGrass(){
        int maxX = this.map.getUpperRight().x;
        int maxY = this.map.getUpperRight().y;
        int newX = (int)(Math.random()*(maxX));
        int newY = (int)(Math.random()*(maxY));


    }
}
