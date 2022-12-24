package agh.ics.oop;

public interface IWorldMap {
    void place(Animal animal);

    Object objectAt(Vector2d position);
}
