package be.ugent.battleship.model;

public class Grid {
    private int breedte;
    private int hoogte;
    private Cell[][] cells;

    public Grid(int breedte, int hoogte){
        this.breedte = breedte;
        this.hoogte = hoogte;
        cells = new Cell[hoogte][breedte];
        for(int i = 0; i < hoogte; i++){
            for(int j = 0; j < breedte; j++){
                Position pos = new Position(i, j);
                cells[i][j] = new Cell(pos);
            }
        }
    }
}
