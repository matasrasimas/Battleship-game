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


    public boolean satisfies(Game game) {
        return canPlaceShip(game, getLastShip(game));
    }

    public BattleshipGameRulesChecker(CoordinatesManager<Ship> coordinatesManager) {
        this.coordinatesManager = coordinatesManager;
    }

    public boolean canPlaceShip(Game game, Ship ship) {
        return isShipInsideGameBoard(game, ship) && !isOverlappingWithAdjacentShips(game, ship);
    }

    private boolean isOverlappingWithAdjacentShips(Game game, Ship ship) {
        Coordinates startCoords = coordinatesManager.getStartCoordinates(ship);
        Coordinates endCoords = coordinatesManager.calculateEndCoordinates(ship);

        for(int i = startCoords.getRow() -1; i <= endCoords.getRow()+1; i++) {
            for(int j = startCoords.getCol() -1; j <= endCoords.getCol()+1; j++) {
                if (isCellInsideGameBoard(game, i, j) && cellContainsShip(game, i, j))
                    return true;
            }
        }
        return false;
    }

    private boolean cellContainsShip(Game game, int row, int col) {
        return game.getBoard().getGrid()[row][col].isShip();
    }

    private boolean isCellInsideGameBoard(Game game, int row, int col) {
        return row >= 0 && row < game.getBoard().getSize() && col >= 0 && col < game.getBoard().getSize();
    }

    private boolean isShipInsideGameBoard(Game game, Ship ship) {
        Coordinates endCoords = coordinatesManager.calculateEndCoordinates(ship);
        return endCoords.getRow() < game.getBoard().getSize() && endCoords.getCol() < game.getBoard().getSize();
    }

    private Ship getLastShip(Game game) {
        return game.getShips().get(game.getShips().size()-1);
    }

}
