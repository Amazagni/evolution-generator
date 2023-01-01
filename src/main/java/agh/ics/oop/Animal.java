package agh.ics.oop;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
public class Animal implements Comparable<Animal>, IGameElement {
    private MapDirection direction;
    private ArrayList<Integer> genes;
    private int genIndex = 0;
    private Vector2d position;
    private int energy;
    private int age = 0; //ilosc dni które zwierze przeżyło
    private int numberOfChildren = 0; // ilosc dzieci (age i numberOfChildren potrzebne do walki o trawe)
    private int genLength = 10;


    public Animal(Vector2d startingPosition, ArrayList<Integer> genes, int energy, int genLength){
        this.energy = energy;
        this.position = startingPosition;
        this.genes = genes;
        this.genLength = genLength;
        if(this.genLength < 1) this.genLength = 1;
        //losowo generuje początkowy kierunek zwierzaka
        this.direction = MapDirection.NORTH.toMapDirection((int)(Math.random()*8));
    }

    //zwraca gen ktorego teraz użyjemy;
    public int getGen(){
        return this.genes.get(genIndex);
    }
    public ArrayList<Integer> getGenes(){
        return this.genes;
    }

    public int getGenIndex() {
        return this.genIndex;
    }

    public int getGenAt(int a){
        return this.genes.get(a);
    }

    @Override
    public boolean isAt(Vector2d pos) {
        if (pos.equals(this.position)) return true;
        return false;
    }

    public Vector2d getPosition(){
        return this.position;
    }
    public void updateIndex(int  a){
        this.genIndex = (this.genIndex + a)%genLength; //dzielimy przez dlugosc genu, dalej nie pamietam ile to było
    }
    public String toString(){
        return "A";
    }

    public MapDirection getDirection() {
        return direction;
    }
    public void updateDirection(MapDirection direction){
        this.direction = direction;
    }
    public void updatePosition(Vector2d position){
        this.position = position;
    }
    public void updateEnergy(int energy){
        this.energy += energy;
    }
    public int getEnergy(){
        return this.energy;
    }
    public void icrementAge(){
        this.age += 1;
    }
    public int getAge(){
        return this.age;
    }
    public void icrementNumberOfChildren(){
        this.numberOfChildren += 1;
    }
    public int getNumberOfChildren(){
        return this.numberOfChildren;
    }

    @Override
    public int compareTo(Animal o) {
        return this.energy - o.energy;
    }

    //W przypadku poza mape wysyła zwierze z powrotem w odpowiednie miejsce
    public void sendBackToBorder( EarthMap map, boolean earth, boolean hellPortal, int dailyEnergyLoss){
        Vector2d position = this.getPosition();
        int newX = position.x;
        int newY = position.y;
        if(earth){
            if(!position.follows(new Vector2d(0,0))){
                if(position.x < 0){
                    newX = map.getUpperRight().x;
                }
                if(position.y < 0){
                    newY = map.getUpperRight().y;
                }
            }
            if(!position.precedes(map.getUpperRight())){
                if(position.x > map.getUpperRight().x){
                    newX = 0;
                }
                if(position.y > map.getUpperRight().y){
                    newY = 0;
                }
            }
        }
        if(hellPortal){
            newX = (int)(Math.random()*(map.getUpperRight().x+1));
            newY = (int)(Math.random()*(map.getUpperRight().y+1));
            this.updateEnergy(-dailyEnergyLoss);
        }
        this.updatePosition(new Vector2d(newX,newY));
    }
    //ustalamy indeks genu z którego skorzysta nastepnie zwierze, (kolejnosc poprawna inkrementuje indeks
    //drugi wariant w 20% przypadkow zmienia indeks na losowy)
    public void updateGeneIndex(boolean correctGenesOrder, boolean slightlyChangedGenesOrder){
        //jesli mamy ustawioną odpowiednia kolejnosc, index zmienia sie tylko o 1
        if(correctGenesOrder){
            this.updateIndex(1);
        }
        // w przeciwnym przypadku sprawdzamy czy wylosowalismy zmiane kolejnosci, czy może jednak wciaz mieniamy indeks o 1
        if(slightlyChangedGenesOrder){
            int randomPercent = (int)(Math.random()*10); //wartosci 0,1,...,9
            if(randomPercent <= 1) {
                this.updateIndex((int) (Math.random() * genLength)); // ZAMIAST 10 WPISAC DLUGOSC GENUUU!!!!!
            }
            else{
                this.updateIndex(1);
            }
        }
    }
}
