package agh.ics.oop;

import java.util.*;

public class SimulationEngine implements Runnable {
    private List<Animal> animals = new ArrayList<>();
    private int energyGain;       //będzie trzeba to przypisać w konstruktorze
    private int dailyEnergyLoss = 5;  // - || -
    private int startingNumberOfGrass = 10;// - || -
    private int dailyGrassGrowth = 8;// - || -
    private int startingEnergy = 50;   // - || -
    private int energyUsedToCreateAnimal = 30; //- || - ilosc energi ktora rodzice łącznie tracą przy rozmnażaniu
    private int minEnergyToStartReproduce = 25; // - || - min energia zeby zwierze moglo sie rozmnazac ps trzeba zmienić tą nazwe xd
    private int genLength = 10; //Nie pamiętam ile jak długa miała być ta tablica
    //zmienne dotyczace wyboru mapy     |
    //(trzeba dopisać w konstruktorze)  V
    private Vector2d equatorLowerLeft;
    private Vector2d equatorUpperRight;
    private boolean earth = true;
    private boolean hellPortal = false;
    private boolean forestedEquators = true;
    private boolean toxicCorpses = false;
    private boolean randomMutation = true;
    private boolean slightlyChangedMutation = false;
    private boolean correctGenesOrder = true;
    private boolean slightlyChangedGenesOrder = false;
    //                                   ʌ
    //zmienne dotyczące wyboru mapy      |
    private EarthMap map;

    public SimulationEngine(EarthMap map, int startingNumberOfAnimals){
        this.map = map;
        for(int i = 0; i < startingNumberOfAnimals; i++){
            generateRandomAnimal();
        }
        //jeżeli jest to mapa z rownikiem, to tworzymy nowe lowerLeft i upperRight
        //około 20% mapy to rownik (jak w poleceniu)
        if(forestedEquators){
            Vector2d upperRight = this.map.getUpperRight();
            this.equatorLowerLeft = new Vector2d(0,(int)(0.4 * upperRight.y));
            this.equatorUpperRight = new Vector2d(upperRight.x, (int)(0.6* upperRight.y));
            generateRandomGrass(startingNumberOfGrass);
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

    //generowanie trawy tylko dla mapy z równikiemmm
    //drugą trzeba bedzie dopisać
    private void generateRandomGrass(int n){
        //generowanie dla mapy z rownikiem
        if(forestedEquators){
            int grassOnEquator = (int)(0.8*n);
            //generuje trawe na rowniku
            for(int i = 0; i < grassOnEquator; i++){
                int newX = (int)(Math.random()*(this.equatorUpperRight.x + 1));
                int newY = (int)(Math.random()*(this.equatorUpperRight.y - this.equatorLowerLeft.y+1))+this.equatorLowerLeft.y;
                Vector2d newPosition = new Vector2d(newX,newY);
                if(!map.isOccupied(newPosition)){
                    //klade tylko jesli jest wolne, nie szukam kolejnego wolnego miejsca, aby zapobiec przepelnieniu
                    map.placeGrass(newPosition);
                }
            }
            //generuje trawę poza równikiem
            for(int i = grassOnEquator; i < n; i ++){
                //losuje ponad równikiem
                int newX = (int)(Math.random()*(this.equatorUpperRight.x + 1));
                int newY;
                if(i%2==0){
                    newY = (int)(Math.random()*this.equatorLowerLeft.y);
                }
                else{
                    newY = (int)(Math.random()*(this.map.getUpperRight().y - this.equatorUpperRight.y + 1))+this.equatorUpperRight.y;
                }
                Vector2d newPosition = new Vector2d(newX,newY);
                if(!map.isOccupied(newPosition)){
                    map.placeGrass(newPosition);
                }

            }
        }

    }
    //obie wersje wyjscia zwierzęcia poza mapę
    private void sendBackToBorder(Animal animal){
        Vector2d position = animal.getPosition();
        int newX = position.x;
        int newY = position.y;
        if(this.earth){
            if(!position.follows(new Vector2d(0,0))){
                if(position.x < 0){
                    newX = this.map.getUpperRight().x;
                }
                if(position.y < 0){
                    newY = this.map.getUpperRight().y;
                }
            }
            if(!position.precedes(this.map.getUpperRight())){
                if(position.x > this.map.getUpperRight().x){
                    newX = 0;
                }
                if(position.y > this.map.getUpperRight().y){
                    newY = 0;
                }
            }
        }
        if(this.hellPortal){
            newX = (int)(Math.random()*(this.map.getUpperRight().x+1));
            newY = (int)(Math.random()*(this.map.getUpperRight().y+1));
            animal.updateEnergy(-dailyEnergyLoss);
        }
        animal.updatePosition(new Vector2d(newX,newY));
    }
    //obie wersje otrzymywania kolejnego genu
    private void updateGeneIndex(Animal animal){
        //jesli mamy ustawioną odpowiednia kolejnosc, index zmienia sie tylko o 1
        if(this.correctGenesOrder){
            animal.updateIndex(1);
        }
        // w przeciwnym przypadku sprawdzamy czy wylosowalismy zmiane kolejnosci, czy może jednak wciaz mieniamy indeks o 1
        if(this.slightlyChangedGenesOrder){
            int randomPercent = (int)(Math.random()*10); //wartosci 0,1,...,9
            if(randomPercent <= 1) {
                animal.updateIndex((int) (Math.random() * (genLength + 1)));
            }
            else{
                animal.updateIndex(1);
            }
        }

    }

    @Override
    public void run() {

        while(true) {
            //testy dla wielu zwierzat na jednym polu
//            Animal animal1 = new Animal(new Vector2d(1,1),new ArrayList<>(),3);
//            Animal animal2 = new Animal(new Vector2d(1,1),new ArrayList<>(),4);
//            Animal animal3 = new Animal(new Vector2d(1,1),new ArrayList<>(),1);
//            this.map.place(animal1);
//            this.map.place(animal2);
//            this.map.place(animal3);
//            System.out.println(this.map.returnAnimals().get(new Vector2d(1,1)).get(0).getEnergy());
//
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
                animal.updateEnergy(-this.dailyEnergyLoss);
                updateGeneIndex(animal);
                updatedAnimals.add(animal);
                if(!(newPosition.follows(new Vector2d(0,0))&&newPosition.precedes(this.map.getUpperRight()))){
                    sendBackToBorder(animal);
                }
                animal.updateEnergy(-dailyEnergyLoss);
                if(animal.getEnergy()>0){
                    this.map.place(animal);
                }
            }
            this.animals = updatedAnimals;
            generateRandomGrass(this.dailyGrassGrowth);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("Przerwano symulacje: "+ e);

            }

        }

    }
}
