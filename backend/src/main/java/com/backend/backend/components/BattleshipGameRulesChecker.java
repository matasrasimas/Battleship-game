package com.backend.backend.components;

import com.backend.backend.components.interfaces.CoordinatesManager;
import com.backend.backend.components.interfaces.GameRulesChecker;
import com.backend.backend.models.Game;
import com.backend.backend.models.Ship;
import com.backend.backend.models.Coordinates;
import org.springframework.stereotype.Component;

@Component
public class BattleshipGameRulesChecker implements GameRulesChecker<Game> {

    private final CoordinatesManager<Ship> coordinatesManager;


    public boolean satisfies(Game game, Ship ship) {
        return canPlaceShip(game, ship);
    }

    public BattleshipGameRulesChecker(CoordinatesManager<Ship> coordinatesManager) {
        this.coordinatesManager = coordinatesManager;
    }

    public boolean canPlaceShip(Game gameToCheck, Ship ship) {
        return isShipInsideGameBoard(gameToCheck, ship) && !isOverlappingWithAdjacentShips(gameToCheck, ship);
    }

    private boolean isOverlappingWithAdjacentShips(Game gameToCheck, Ship ship) {
        Coordinates startCoords = coordinatesManager.getStartCoordinates(ship);
        Coordinates endCoords = coordinatesManager.calculateEndCoordinates(ship);

        for(int i = startCoords.getRow() -1; i <= endCoords.getRow()+1; i++) {
            for(int j = startCoords.getCol() -1; j <= endCoords.getCol()+1; j++) {
                if (isCellInsideGameBoard(gameToCheck, i, j) && cellContainsShip(gameToCheck, i, j))
                    return true;
            }
        }
        return false;
    }

    private boolean cellContainsShip(Game gameToCheck, int row, int col) {
        return gameToCheck.getBoard().getGrid()[row][col].isShip();
    }

    private boolean isCellInsideGameBoard(Game gameToCheck, int row, int col) {
        return row >= 0 && row < gameToCheck.getBoard().getSize() && col >= 0 && col < gameToCheck.getBoard().getSize();
    }

    private boolean isShipInsideGameBoard(Game gameToCheck, Ship ship) {
        Coordinates endCoords = coordinatesManager.calculateEndCoordinates(ship);
        return endCoords.getRow() < gameToCheck.getBoard().getSize() && endCoords.getCol() < gameToCheck.getBoard().getSize();
    }

}
