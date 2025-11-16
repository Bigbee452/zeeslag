package be.ugent.battleship.model;

public class Cell {
    private final Position pos;
    private Ship ship;
    private boolean wasShot = false;

    public Cell(Position pos) {
        this.pos = pos;
    }

    public boolean hasShip() {
        return ship != null;
    }

    public Position getPosition() {
        return pos;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }

    public boolean wasShot() {
        return wasShot;
    }

    public void markShot() {
        this.wasShot = true;
    }

}
