package be.ugent.battleship.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class BattleshipSoloGame implements IBattleshipSoloGame {

    private final Grid grid;
    private final List<Ship> ships = new ArrayList<>();
    private int moveCount = 0;

    public BattleshipSoloGame(File file) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String[] dims = br.readLine().trim().split("\\s+");
            int cols = Integer.parseInt(dims[0]);
            int rows = Integer.parseInt(dims[1]);

            this.grid = new Grid(cols, rows);

            String line;
            while ((line = br.readLine()) != null) {

                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+", 5);

                int length = Integer.parseInt(parts[0]);
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                String orientation = parts[3].toLowerCase();
                String name = parts[4].trim();

                List<Position> positions = new ArrayList<>();

                if (orientation.equals("horizontal")) {
                    for (int i = 0; i < length; i++) {
                        positions.add(new Position(x + i, y));
                    }
                } else {
                    for (int i = 0; i < length; i++) {
                        positions.add(new Position(x, y + i));
                    }
                }

                Ship ship = new Ship(name, positions);
                grid.placeShip(ship);
                ships.add(ship);
            }

            br.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getRowCount() {
        return grid.getRowCount();
    }

    @Override
    public int getColumnCount() {
        return grid.getColumnCount();
    }

    @Override
    public String getCellContent(Position pos, boolean forOwner) {

        Cell c = grid.getCell(pos);

        if (!c.wasShot()) {
            if (forOwner && c.hasShip()) return "S";
            return "~";
        }

        if (c.hasShip()) return "X";
        return ".";
    }

    @Override
    public String getCellContentImage(Position pos) {

        Cell c = grid.getCell(pos);

        if (!c.wasShot()) {
            return "unknown.png";
        }

        if (c.hasShip()) return "fire.png";

        return "sea.png";
    }

    @Override
    public void shoot(Position pos) {

        Cell c = grid.getCell(pos);
        if (c.wasShot()) return;

        grid.shootAt(pos);
        moveCount++;
    }

    @Override
    public boolean isGameOver() {
        return grid.allShipsSunk();
    }

    @Override
    public int getMoveCount() {
        return moveCount;
    }

    @Override
    public String shipSunk(Position pos) {

        Ship s = grid.getShipAt(pos);
        if (s == null) return null;

        if (s.isSunk()) return s.getName();

        return null;
    }
}
