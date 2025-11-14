package be.ugent.battleship.model;

import java.util.*;

public class Grid {
    private final int cols;
    private final int rows;
    private final Cell[][] grid;
    private final List<Ship> ships = new ArrayList<>();

    public Grid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        grid = new Cell[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = new Cell(new Position(c, r));
            }
        }
    }

    public int getRowCount() { return rows; }
    public int getColumnCount() { return cols; }

    public Cell getCell(Position p) {
        if (p == null) return null;
        if (p.x < 0 || p.x >= cols || p.y < 0 || p.y >= rows) return null;
        return grid[p.y][p.x];
    }

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
