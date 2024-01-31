package com.backend.backend.components;

import com.backend.backend.components.interfaces.CoordinatesManager;
import com.backend.backend.models.Ship;
import com.backend.backend.models.Coordinates;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BattleshipCoordinatesManager implements CoordinatesManager<Ship> {

    public Coordinates generateCoordinatesRandomly(int boardSize) {
        int randomRow = (int) (Math.random() * boardSize);
        int randomCol = (int) (Math.random() * boardSize);
        return new Coordinates(randomRow, randomCol);
    }
    public void removeStartCoordinates(Ship ship) {
        ship.getCoordinates().remove(0);
    }

    public void initializeStartCoordinates(Ship ship, Coordinates startCoords) {
        ship.setCoordinates(new ArrayList<>());
        ship.getCoordinates().add(startCoords);
    }

    public Coordinates getStartCoordinates(Ship ship) {
        return ship.getCoordinates().get(0);
    }

    public Coordinates calculateEndCoordinates(Ship ship) {
        int endRow = calculateShipEndRow(ship);
        int endCol = calculateShipEndColumn(ship);
        return new Coordinates(endRow, endCol);
    }

    private int calculateShipEndColumn(Ship ship) {
        Coordinates startCoords = getStartCoordinates(ship);
        return ship.isHorizontal() ? startCoords.getCol() + ship.getHitPoints() - 1 : startCoords.getCol();
    }

    private int calculateShipEndRow(Ship ship) {
        Coordinates startCoords = getStartCoordinates(ship);
        return ship.isHorizontal() ? startCoords.getRow() : startCoords.getRow() + ship.getHitPoints() - 1;
    }

}
