package agh.ics.oop;

public enum MapDirection {
    NORTH,//0
    NORTH_EAST,//1
    EAST,//2
    SOUTH_EAST,//3
    SOUTH,//4
    SOUTH_WEST,//5
    WEST,//6
    NORTH_WEST;//7



    public MapDirection changeDirection(int gene){
        int currentOrientation = this.toInt();
        currentOrientation += gene;
        currentOrientation = currentOrientation % 8;
        return toMapDirection(currentOrientation);


    }
    public int toInt() {
        switch(this){
            case NORTH:
                return 0;
            case NORTH_EAST:
                return 1;
            case EAST:
                return 2;
            case SOUTH_EAST:
                return 3;
            case SOUTH:
                return 4;
            case SOUTH_WEST:
                return 5;
            case WEST:
                return 6;
            case NORTH_WEST:
                return 7;
            default:
                return 0;
        }
    }
    public MapDirection toMapDirection(int id){
        switch(id){
            case 0:
                return NORTH;
            case 1:
                return NORTH_EAST;
            case 2:
                return EAST;
            case 3:
                return SOUTH_EAST;
            case 4:
                return SOUTH;
            case 5:
                return SOUTH_WEST;
            case 6:
                return WEST;
            case 7:
                return NORTH_WEST;
            default:
                return NORTH;
        }
    }
    public Vector2d toUnitVector(){
        switch(this){
            case NORTH:
                return new Vector2d(0,1);
            case NORTH_EAST:
                return new Vector2d(1,1);
            case EAST:
                return new Vector2d(1,0);
            case SOUTH_EAST:
                return new Vector2d(1,-1);
            case SOUTH:
                return new Vector2d(0,-1);
            case SOUTH_WEST:
                return new Vector2d(-1,-1);
            case WEST:
                return new Vector2d(-1,0);
            case NORTH_WEST:
                return new Vector2d(-1,1);
            default:
                return new Vector2d(0,0);

        }

    }
}
