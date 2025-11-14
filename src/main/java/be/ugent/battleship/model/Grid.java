package be.ugent.battleship.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grid {
    private int breedte;
    private int hoogte;
    private Cell[][] cells;
    private final List<Ship> ships = new ArrayList<>();

    public Grid(int breedte, int hoogte){
        this.breedte = breedte;
        this.hoogte = hoogte;
        cells = new Cell[hoogte][breedte];
        for(int i = 0; i < hoogte; i++){
            for(int j = 0; j < breedte; j++){
                Position pos = new Position(j, i);
                cells[i][j] = new Cell(pos);
            }
        }
    }

    public int getRowCount() { return hoogte; }
    public int getColumnCount() { return breedte; }

    public Cell getCell(Position p) {
        if (p.x < 0 || p.x >= breedte || p.y < 0 || p.y >= hoogte) return null;
        return cells[p.y][p.x]; }

    public boolean placeShip(Ship ship) {
        for (Position p : ship.getPositions()) {
            if (getCell(p) == null) return false;
            if (getCell(p).hasShip()) return false;
        }
        ships.add(ship);
        for (Position p : ship.getPositions()) {
            getCell(p).setShip(ship);
        }
        return true;
    }

    public boolean shootAt(Position p) {
        Cell c = getCell(p);
        if (c == null) throw new IllegalArgumentException("Position out of bounds: " + p);
        if (c.wasShot()) return false;
        c.markShot();
        if (c.hasShip()) {
            c.getShip().registerHit(p);
            return true;
        } else return false;
    }

    public boolean allShipsSunk() {
        for (Ship s : ships) if (!s.isSunk()) return false;
        return true;
    }

    public Ship getShipAt(Position p) {
        Cell c = getCell(p);
        return (c == null) ? null : c.getShip();
    }

    public List<Ship> getShips() {
        return Collections.unmodifiableList(ships);
    }
}
