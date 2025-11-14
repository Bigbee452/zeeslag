package be.ugent.battleship.model;

import java.util.*;

public class Ship {
    private final String name;
    private final List<Position> positions;
    private final Set<Position> hits = new HashSet<>();

    public Ship(String name, List<Position> positions){
        this.name = name;
        this.positions = new ArrayList<>(positions);
    }

    public String getName() {
        return name;
    }

    public List<Position> getPositions() {
        return Collections.unmodifiableList(positions);
    }

    public boolean occupies(Position p) {
        return positions.contains(p);
    }

    public void registerHit(Position p) {
        if (occupies(p)) hits.add(p);
    }

    public boolean isSunk() {
        return hits.size() >= positions.size();
    }


}