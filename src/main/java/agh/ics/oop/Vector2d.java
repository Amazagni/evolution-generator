package agh.ics.oop;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;
    public Vector2d(int x,int y){
        this.x = x;
        this.y = y;
    }
    public String toString(){return "(" + Integer.toString(this.x) + "," +Integer.toString(this.y) + ")";}
    public boolean precedes(Vector2d other){return (this.x <= other.x && this.y <= other.y);}

    public boolean follows(Vector2d other){return (this.x >= other.x && this.y >= other.y);}

    public Vector2d add(Vector2d other){return new Vector2d(this.x+ other.x,this.y + other.y);}

    public Vector2d substract(Vector2d other){return new Vector2d(this.x -other.x, this.y - other.y);}

    public Vector2d upperRight(Vector2d other){return new Vector2d(Math.max(this.x,other.x),Math.max(this.x,other.x));}

    public Vector2d lowerLeft(Vector2d other){return new Vector2d(Math.min(this.x, other.x),Math.min(this.y, other.y));}

    public Vector2d opposite(){return new Vector2d(this.y, this.x);}

    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        if(this.x == that.x && this.y == that.y)return true;
        return false;
    }
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}
