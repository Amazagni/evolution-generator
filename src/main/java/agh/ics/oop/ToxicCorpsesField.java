package agh.ics.oop;

public class ToxicCorpsesField {
    int corspes = 0;
    int randomNumber = (int)(Math.random()*1000); // zapobiega spawnieniu sie trawy tylko w jednym miejscu na początku symulacji
    Vector2d position;
    public ToxicCorpsesField(Vector2d position){
        this.position = position;
    }

    public int getCorspes() {
        return this.corspes;
    }
    public int getRandomNumber(){
        return this.randomNumber;
    }

    public Vector2d getPosition() {
        return this.position;
    }
    public void icrementCorpses(){
        this.corspes += 1;
    }

    @Override
    public String toString() {
        return String.valueOf(this.corspes);
    }
}
