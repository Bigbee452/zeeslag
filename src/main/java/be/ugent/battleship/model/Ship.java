package be.ugent.battleship.model;

public class Ship {
    private final String name;
    private final int lengte;
    private final int x;
    private final int y;

    public Ship(String name, int lengte, int x, int y){
        this.name = name;
        this.lengte = lengte;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }
    public int getLengte() {
        return lengte;
    }


}
