package agh.ics.oop;


import java.util.*;

public class EarthMap implements IWorldMap {
    Vector2d lowerLeft = new Vector2d(0,0);
    Vector2d upperRight;
    Map<Vector2d, ArrayList<Animal>> animals = new HashMap<>();
    Map<Vector2d,Grass> clumpsOfGrass = new HashMap<>();

    MapVisualizer toVisualize;
    public EarthMap(Vector2d upperRight){

        this.upperRight = new Vector2d(upperRight.x - 1, upperRight.y - 1);
        if(this.upperRight.x < 1) this.upperRight = new Vector2d(1, this.upperRight.y);
        if(this.upperRight.x > 100) this.upperRight = new Vector2d(100, this.upperRight.y);
        if(this.upperRight.y < 1) this.upperRight = new Vector2d(this.upperRight.y, 1);
        if(this.upperRight.y > 100) this.upperRight = new Vector2d(this.upperRight.y, 1);
        this.toVisualize = new MapVisualizer(this);

    }

    public void place(Animal animal){
        Vector2d position = animal.getPosition();
        //tworze nowy array
        ArrayList<Animal> tmp;
        if(this.animals.get(position) == null){
            tmp = new ArrayList<Animal>();
            tmp.add(animal);
            this.animals.put(position,tmp);
        }
        //dodaje do arraya
        else {
            tmp = new ArrayList<Animal>(this.animals.get(position));
            tmp.add(animal);
            this.animals.remove(position);
            //array zawsze utrzymuje posortowany po aktualnej energii;
            tmp.sort(new AnimalEnergyComparator());
            this.animals.put(position,tmp);

        }
//        System.out.println(position);
//        System.out.println(isOccupied(position));
    }

    @Override
    public Object objectAt(Vector2d position) {
         if(this.animals.get(position) == null) return this.clumpsOfGrass.get(position);
         else return this.animals.get(position).get(0);
    }

    public void placeGrass(Vector2d position){
        this.clumpsOfGrass.put(position,new Grass(position));

    }
    public boolean isGrassAt(Vector2d position){
        if(this.clumpsOfGrass.get(position) == null){
            return false;
        }
        return true;
    }
    public void deleteGrassAt(Vector2d position){
        this.clumpsOfGrass.remove(position);
    }
    public Vector2d getUpperRight(){
        return this.upperRight;
    }
    public String toString(){
        return this.toVisualize.draw(new Vector2d(0,0),getUpperRight());}

    //isOccupied potrzebne do rysowania mapy
    public boolean isOccupied(Vector2d position){
        if(this.animals.get(position) != null || this.clumpsOfGrass.get(position)!= null)return true;
        return false;
    }

    public Map<Vector2d,ArrayList<Animal>> returnAnimals(){
        return animals;
    }

    public void updateAnimals(Map<Vector2d, ArrayList<Animal>> animals){
        this.animals = animals;
    }

    public void updateAnimalsAt(Vector2d position, ArrayList<Animal> animals){
        this.animals.remove(position);
        this.animals.put(position,animals);
    }

    public Map<Vector2d, ArrayList<Animal>> getAnimals() {
        return animals;
    }
    //    public void plantTheGrass(){
//
//    }
}
