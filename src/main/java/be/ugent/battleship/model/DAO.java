package be.ugent.battleship.model;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DAO {
    private List<Ship> schepen = new ArrayList<>();
    private int breedte;
    private int hoogte;

    public DAO(File file) {
        try(Scanner scanner = new Scanner(file)){
            String firstLine = scanner.nextLine();
            Scanner sc = new Scanner(firstLine);
            sc.useDelimiter(" ");
            this.breedte = sc.nextInt();
            this.hoogte = sc.nextInt();
            while(scanner.hasNextLine()){
                String line = scanner.nextLine().trim();
                Scanner shipScanner = new Scanner(line);
                int lengte = shipScanner.nextInt();
                int x = shipScanner.nextInt();
                int y = shipScanner.nextInt();
                String orientation = shipScanner.next().toLowerCase();
                String name = shipScanner.next();

                List<Position> positions = new ArrayList<>();

                if (orientation.equals("horizontal")) {
                    for (int i = 0; i < lengte; i++) {
                        positions.add(new Position(x + i, y));
                    }
                } else {
                    for (int i = 0; i < lengte; i++) {
                        positions.add(new Position(x, y + i));
                    }
                }

                Ship ship = new Ship(name, positions);
                schepen.add(ship);
            }
        } catch  (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public int getBreedte() {
        return breedte;
    }

    public int getHoogte() {
        return hoogte;
    }

    public List<Ship> getSchepen() {
        return schepen;
    }
}
