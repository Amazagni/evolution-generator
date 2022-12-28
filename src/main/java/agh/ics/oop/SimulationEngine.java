package agh.ics.oop;

import java.util.*;

public class SimulationEngine implements Runnable {
    private List<Animal> animals = new ArrayList<>();
    private int grassEnergyGain = 40;//będzie trzeba to przypisać w konstruktorze
    private int dailyEnergyLoss = 5;  // - || -
    private int startingNumberOfGrass = 10;// - || -
    private int dailyGrassGrowth = 20;// - || -
    private int startingEnergy = 120;//   // - || -
    private int energyUsedToCreateAnimal = 30; //- || - ilosc energi ktora rodzice łącznie tracą przy rozmnażaniu
    private int minEnergyToReproduce = 25; // - || - min energia zeby zwierze moglo sie rozmnazac ps trzeba zmienić tą nazwe xd
    private int genLength = 10; //Nie pamiętam ile jak długa miała być ta tablica

    // Do statystyk
    private int day = 0;
    private int totalDead = 0;
    private int deadToday = 0;
    private int totalBorn = 0;
    private int bornToday = 0;


    //zmienne dotyczace wyboru mapy     |
    //(trzeba dopisać w konstruktorze)  V
    private Vector2d equatorLowerLeft;
    private Vector2d equatorUpperRight;
    private ArrayList<ToxicCorpsesField> corpses;
    private boolean earth = false;
    private boolean hellPortal = true;
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
        if(this.forestedEquators){
            Vector2d upperRight = this.map.getUpperRight();
            this.equatorLowerLeft = new Vector2d(0,(int)(0.4 * upperRight.y));
            this.equatorUpperRight = new Vector2d(upperRight.x, (int)(0.6* upperRight.y));
        }
        if(this.toxicCorpses){
            this.corpses = new ArrayList<>();
            for(int i = 0; i <= this.map.getUpperRight().x; i++){
                for(int j = 0; j <= this.map.getUpperRight().y; j++){
                    this.corpses.add(new ToxicCorpsesField(new Vector2d(i,j)));
                }
            }
        }
        generateRandomGrass(startingNumberOfGrass);
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
        if(this.forestedEquators){
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
                if(!this.map.isOccupied(newPosition)){
                    this.map.placeGrass(newPosition);
                }

            }
        }
        if(this.toxicCorpses){
            //corpses to tablica posortowana po ilosci trupow (rosnąco)
            //"lepsza część" to pierwsze 20% elementów tej tablicy
            int grassOnBetterFields = (int)(0.8 * n);
            int betterFields = (int)(0.2 * this.corpses.size());
            this.corpses.sort(new ToxicCorpsesComparator());
            for(int i = 0; i < grassOnBetterFields; i++){
                int betterFieldId = (int)(Math.random()*(betterFields));
                if(!this.map.isOccupied(this.corpses.get(betterFieldId).getPosition())){
                    this.map.placeGrass(this.corpses.get(betterFieldId).getPosition());
                }
            }
            for(int i = grassOnBetterFields; i < n; i++){
                int worseFieldId = (int)(Math.random()*(this.corpses.size()-betterFields)+betterFields);
                if(!this.map.isOccupied(this.corpses.get(worseFieldId).getPosition())){
                    this.map.placeGrass(this.corpses.get(worseFieldId).getPosition());
                }
            }
        }
    }

    private ArrayList<Integer> CreateChildGenes(Animal firstParent, Animal secondParent){
        int genesFromFirstParent = (int)(this.genLength *
                (firstParent.getEnergy()/(double)(firstParent.getEnergy()+secondParent.getEnergy())));

        ArrayList<Integer> childGenes = new ArrayList<>();
        for(int j = 0; j < genesFromFirstParent; j++){
            int tmpGene = firstParent.getGenAt(j);
            if(Math.random()<0.5){
                //mutujemy gen
                if(this.slightlyChangedMutation){
                    if(Math.random()<0.5){
                        tmpGene = (tmpGene+1)%8;
                    }
                    else{
                        tmpGene -= 1;
                        if(tmpGene < 0)tmpGene = 7;
                    }
                }
                if(this.randomMutation){
                    tmpGene = (int)(Math.random()*8);
                }
            }
            childGenes.add(tmpGene);
        }
        for(int j = genesFromFirstParent; j < this.genLength; j++){
            int tmpGene = secondParent.getGenAt(j);
            if(Math.random()<0.5){
                //mutujemy gen
                if(this.slightlyChangedMutation){
                    if(Math.random()<0.5){
                        tmpGene = (tmpGene+1)%8;
                    }
                    else{
                        tmpGene -= 1;
                        if(tmpGene < 0)tmpGene = 7;
                    }
                }
                if(this.randomMutation){
                    tmpGene = (int)(Math.random()*8);
                }
            }
            childGenes.add(tmpGene);
        }
        return childGenes;
    }

    @Override
    public void run() {

        while(true) {
            this.totalBorn += this.bornToday;
            this.totalDead += this.deadToday;

            System.out.println(this.map);
            System.out.print("Dzień: ");
            System.out.println(day);
            System.out.print("Ilość żywych zwierząt: ");
            System.out.println(this.animals.size());
            System.out.print("Ilość urodzonych zwierząt: ");
            System.out.println(this.totalBorn);
            System.out.print("Dziś: ");
            System.out.println(this.bornToday);
            System.out.print("Ilość zmarłych zwierząt: ");
            System.out.println(this.totalDead);
            System.out.print("Dziś: ");
            System.out.println(this.deadToday);

            this.bornToday = this.animals.size();
            this.day += 1;
            this.deadToday = 0;


            List<Animal> updatedAnimals = new ArrayList<>();//nowe animals dla engina i na tego podstawie uzupelni sie animals w mapie
            //List<Vector2d> positions = new ArrayList<>();// lista pozycji okupowanych przez zwierzęta
            int currGene;
            MapDirection newDirection;
            Vector2d newPosition;

            //czyscimy animals z mapy
            this.map.updateAnimals(new HashMap<>());

            //każde zwierze sie porusza
            for (Animal animal : this.animals) {
                currGene = animal.getGen();
                newDirection = animal.getDirection().changeDirection(currGene);
                animal.updateDirection(newDirection);
                newPosition = animal.getPosition().add(newDirection.toUnitVector());
                animal.updatePosition(newPosition);
                animal.updateEnergy(-this.dailyEnergyLoss);
                animal.updateGeneIndex(this.correctGenesOrder,this.slightlyChangedGenesOrder);
                //updateGeneIndex(animal);

                //jesli zwierze wyszło poza granice mapy wysylamy je w odpowiednie miejsce
                if(!(newPosition.follows(new Vector2d(0,0))&&newPosition.precedes(this.map.getUpperRight()))){
                    //sendBackToBorder(animal);
                    animal.sendBackToBorder(this.map,this.earth,this.hellPortal,this.energyUsedToCreateAnimal);
                }
                animal.updateEnergy(-dailyEnergyLoss);
                animal.icrementAge();
                if(animal.getEnergy()>=0){
                    updatedAnimals.add(animal);
                    this.map.place(animal);
                }
                //zwierze umiera
                else{
                    if(this.toxicCorpses){
                        for(int i = 0; i < this.corpses.size(); i++){
                            if(this.corpses.get(i).getPosition().equals(newPosition)){
                                ToxicCorpsesField tmp = corpses.get(i);
                                tmp.icrementCorpses();
                                this.corpses.set(i,tmp);
                                break;

                            }
                        }
                    }
                    this.deadToday += 1;
                }

            }
            this.animals = updatedAnimals;

            Map<Vector2d, ArrayList<Animal>> mapAniamls = this.map.getAnimals();
            mapAniamls.forEach((position,animalList)->{
                //zjadanie trawy
                if(this.map.isGrassAt(position)){
                    Animal updatedAnimal = animalList.get(0);
                    updatedAnimal.updateEnergy(grassEnergyGain);
                    animalList.set(0,updatedAnimal);
                    //this.map.updateAnimalsAt(position,animalList);
                    this.map.deleteGrassAt(position);
                }
                //rozmnażanie
                int animalsOnPosition = animalList.size();
                if(animalsOnPosition>=2){
                    for(int i = 0 ; i < animalsOnPosition -1; i+=2){
                        Animal firstParent = animalList.get(i);
                        Animal secondParent = animalList.get(i+1);
                        if(secondParent.getEnergy()<this.minEnergyToReproduce)break;
                        //50% szans na to że potomek dziedziczy lewą część od rodzica z wiekszą ilośćią energii
                        if(Math.random()<0.5){
                            firstParent = animalList.get(i+1);
                            secondParent = animalList.get(i);
                        }
                        ArrayList<Integer> childGenes = this.CreateChildGenes(firstParent,secondParent);

                        //zmniejszamy energie rodzicow;
                        int firstParentEnergyLoss = (int)(this.energyUsedToCreateAnimal *
                                (firstParent.getEnergy()/(double)(firstParent.getEnergy()+secondParent.getEnergy())));
                        firstParent.updateEnergy(-firstParentEnergyLoss);
                        secondParent.updateEnergy(-(this.energyUsedToCreateAnimal - firstParentEnergyLoss));

                        //dodajemy nowe zwierze
                        Animal child = new Animal(position,childGenes,this.energyUsedToCreateAnimal);
                        animalList.add(child);
                        this.animals.add(child);
                        }

                }
            });
            this.bornToday = this.animals.size() + this.deadToday - this.bornToday;
            generateRandomGrass(this.dailyGrassGrowth);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Przerwano symulacje: "+ e);


            }
        }

    }
}
//obie wersje wyjscia zwierzęcia poza mapę
//FUNKCJA PRZEROBIONA NA METODE W ANIMAL!!!
//    private void sendBackToBorder(Animal animal){
//        Vector2d position = animal.getPosition();
//        int newX = position.x;
//        int newY = position.y;
//        if(this.earth){
//            if(!position.follows(new Vector2d(0,0))){
//                if(position.x < 0){
//                    newX = this.map.getUpperRight().x;
//                }
//                if(position.y < 0){
//                    newY = this.map.getUpperRight().y;
//                }
//            }
//            if(!position.precedes(this.map.getUpperRight())){
//                if(position.x > this.map.getUpperRight().x){
//                    newX = 0;
//                }
//                if(position.y > this.map.getUpperRight().y){
//                    newY = 0;
//                }
//            }
//        }
//        if(this.hellPortal){
//            newX = (int)(Math.random()*(this.map.getUpperRight().x+1));
//            newY = (int)(Math.random()*(this.map.getUpperRight().y+1));
//            animal.updateEnergy(-this.energyUsedToCreateAnimal);
//        }
//        animal.updatePosition(new Vector2d(newX,newY));
//    }
//obie wersje otrzymywania kolejnego genu
//FUNKCJA PRZEROBIONA NA METODE W ANIMAL!!!
//    private void updateGeneIndex(Animal animal){
//        //jesli mamy ustawioną odpowiednia kolejnosc, index zmienia sie tylko o 1
//        if(this.correctGenesOrder){
//            animal.updateIndex(1);
//        }
//        // w przeciwnym przypadku sprawdzamy czy wylosowalismy zmiane kolejnosci, czy może jednak wciaz mieniamy indeks o 1
//        if(this.slightlyChangedGenesOrder){
//            int randomPercent = (int)(Math.random()*10); //wartosci 0,1,...,9
//            if(randomPercent <= 1) {
//                animal.updateIndex((int) (Math.random() * (genLength + 1)));
//            }
//            else{
//                animal.updateIndex(1);
//            }
//        }
//    }