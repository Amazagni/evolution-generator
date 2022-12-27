package agh.ics.oop;

import java.util.*;

public class SimulationEngine implements Runnable {
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
        //generuje x i y z przedzialu [0,max]
        //(max jest już poza naszą planszą)
        int newX = (int)(Math.random()*(maxX+1));
        int newY = (int)(Math.random()*(maxY+1));

        Vector2d position = new Vector2d(newX,newY);
        ArrayList<Integer> genes = new ArrayList<Integer>();
        for(int i = 0; i < this.genLength; i++){
            genes.add((int)(Math.random()*8));
        }
        Animal animal = new Animal(position,genes,this.startingEnergy);
        this.animals.add(animal);
        this.map.place(animal);
    }
    //Trzeba napisać jakiekolwiek spawnienie trawy ...
    private void generateRandomGrass(){
//        int maxX = this.map.getUpperRight().x;
//        int maxY = this.map.getUpperRight().y;
//        int newX = (int)(Math.random()*(maxX+1));
//        int newY = (int)(Math.random()*(maxY+1));

    }

    @Override
    public void run() {

        while(true) {
            System.out.println(this.map);
            List<Animal> updatedAnimals = new ArrayList<>();//nowe animals dla engina i na tego podstawie uzupelni sie animals w mapie
            int currGene;
            MapDirection newDirection;
            Vector2d newPosition;

            //czyscimy animals z mapy
            this.map.updateAnimals(new HashMap<>());
            for (Animal animal : this.animals) {
                currGene = animal.getGen();
                newDirection = animal.getDirection().changeDirection(currGene);
                animal.updateDirection(newDirection);
                newPosition = animal.getPosition().add(newDirection.toUnitVector());
                animal.updatePosition(newPosition);
                updatedAnimals.add(animal);
                this.map.place(animal);
            }
            this.animals = updatedAnimals;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Przerwano symulacje: "+ e);

            }

        }

    }
}
