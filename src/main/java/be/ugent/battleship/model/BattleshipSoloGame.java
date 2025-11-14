package be.ugent.battleship.model;

import java.util.List;

public class BattleshipSoloGame implements IBattleshipSoloGame {

    private final Grid grid;
    private int moveCount = 0;

    public BattleshipSoloGame(DAO dao) {

        grid = new Grid(dao.getBreedte(), dao.getHoogte());

        List<Ship> ships = dao.getSchepen();

        for (Ship s : ships) {
            grid.placeShip(s);
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

        if (!c.wasShot()) return "unknown.png";

        if (c.hasShip()) return "fire.png";
        return "sea.png";
    }

    @Override
    public void shoot(Position pos) {
        Cell c = grid.getCell(pos);

        if (!c.wasShot()) {
            grid.shootAt(pos);
            moveCount++;

        }
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

        if (s != null && s.isSunk()) return s.getName();
        return null;
    }
}