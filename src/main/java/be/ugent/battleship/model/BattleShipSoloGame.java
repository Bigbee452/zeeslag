package be.ugent.battleship.model;

public class BattleShipSoloGame implements IBattleshipSoloGame {

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public String getCellContent(Position pos, boolean forOwner) {
        return "";
    }

    @Override
    public String getCellContentImage(Position pos) {
        return "";
    }

    @Override
    public void shoot(Position pos) {

    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public int getMoveCount() {
        return 0;
    }

    @Override
    public String shipSunk(Position pos) {
        return "";
    }
}
