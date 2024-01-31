package com.backend.backend.components;

import com.backend.backend.components.interfaces.*;
import com.backend.backend.models.Game;
import com.backend.backend.models.Ship;
import com.backend.backend.models.Coordinates;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BattleshipsPlacer implements Placer<Game> {

    private Game game;

    private final BoardInitializer<Game> boardInitializer;
    private final ListGenerator<Ship> listGenerator;
    private final CoordinatesManager<Ship> coordinatesManager;
    private final GameRulesChecker<Game> rulesChecker;

    public BattleshipsPlacer(BoardInitializer<Game> boardInitializer, ListGenerator<Ship> shipsListGenerator,
                             CoordinatesManager<Ship> coordinatesManager, GameRulesChecker<Game> rulesChecker) {
        this.boardInitializer = boardInitializer;
        this.listGenerator = shipsListGenerator;
        this.coordinatesManager = coordinatesManager;
        this.rulesChecker = rulesChecker;
    }
    public void place(Game targetGame) {
        game = targetGame;
        game.setShips(new ArrayList<>());
        boardInitializer.initialize(game);
        List<Ship> ships = listGenerator.generate();
        for (Ship ship : ships)
            placeShipRandomly(ship);
    }

    private void placeShipRandomly(Ship ship) {
        if(game.getShips().size() == 10) return;
        int maxAttempts = 1000;
        attemptToPlaceShip(ship, maxAttempts);
    }

    private void attemptToPlaceShip(Ship ship, int maxAttempts) {
        int attempts = 0;
        while(attempts < maxAttempts) {
            ship.setHorizontal(Math.random() < 0.5);
            if(shipPlacedSuccessfully(ship))
                return;

            coordinatesManager.removeStartCoordinates(ship);
            attempts++;
        }
        place(game);
    }

    private boolean shipPlacedSuccessfully(Ship ship) {
        Coordinates startCoords = coordinatesManager.generateCoordinatesRandomly(game.getBoard().getSize());
        coordinatesManager.initializeStartCoordinates(ship, startCoords);
        if(rulesChecker.satisfies(game, ship)) {
            addShipToGame(ship);
            return true;
        }
        return false;
    }

    private void addShipToGame(Ship ship) {
        placeShip(ship);
        game.getShips().add(ship);
    }

    private void placeShip(Ship ship) {
        if (ship.isHorizontal())
            placeShipHorizontally(ship);
        else
            placeShipVertically(ship);
    }

    private void placeShipVertically(Ship ship) {
        Coordinates startCoords = coordinatesManager.getStartCoordinates(ship);
        int startRow = startCoords.getRow(), startCol = startCoords.getCol();
        for(int i = 0; i < ship.getHitPoints(); i++) {
            ship.getCoordinates().add(new Coordinates(startRow+i, startCol));
            game.getBoard().getGrid()[startRow +i][startCol].setIsShip(true);
        }
    }

    private void placeShipHorizontally(Ship ship) {
        Coordinates startCoords = coordinatesManager.getStartCoordinates(ship);
        int startRow = startCoords.getRow(), startCol = startCoords.getCol();
        for(int i = 0; i < ship.getHitPoints(); i++) {
            ship.getCoordinates().add(new Coordinates(startRow, startCol+i));
            game.getBoard().getGrid()[startRow][startCol +i].setIsShip(true);
        }
    }
}