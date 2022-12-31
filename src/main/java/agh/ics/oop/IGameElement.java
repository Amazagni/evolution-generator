package agh.ics.oop;

// represents in-game elements like Animal and grass
public interface IGameElement {

    boolean isAt(Vector2d pos);
    Vector2d getPosition();
}
