package agh.ics.oop;

public interface IWorldMap {
    boolean place(Animal animal);
    boolean canMoveTo(Vector2d position);
    Object objectAt(Vector2d position);

}
