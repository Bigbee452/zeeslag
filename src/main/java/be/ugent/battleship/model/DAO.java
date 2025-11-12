package be.ugent.battleship.model;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DAO {
    private List<Ship> schepen = new ArrayList<>();
    Grid grid;

    public DAO(String bestandsnaam) throws FileNotFoundException {
        try(Scanner scanner = new Scanner(new File(bestandsnaam))){
            String firtLine = scanner.nextLine();
            Scanner sc = new Scanner(firtLine);
            sc.useDelimiter(" ");
            int breedte = sc.nextInt();
            int hoogte = sc.nextInt();
            grid = new Grid(breedte, hoogte);
            while(scanner.hasNext()){
                String line = scanner.nextLine();
                Scanner shipScanner = new Scanner(line);
                shipScanner.useDelimiter(" ");
                int lengte = shipScanner.nextInt();
                int x = shipScanner.nextInt();
                int y = shipScanner.nextInt();
                Ship ship = new Ship(lengte, x, y);
                schepen.add(ship);
            }
        }
    }
}
