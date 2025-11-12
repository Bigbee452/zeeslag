package be.ugent.battleship.model;

public class Cell {
    public final Position pos;
    public Ship ship;
    public boolean wasShot = false;

    public Cell(Position pos) {
        this.pos = pos;
    }

    public boolean hasShip() {
        return ship != null;
    }
}
