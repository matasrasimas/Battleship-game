package com.backend.backend.services;

import com.backend.backend.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class GameService {


    public Game startNewGame() {
        Game game = new Game();
        game.setShootResultMessage("");
        game.setShips(new ArrayList<Ship>());
        Board board = initializeBoard(10);
        game.setComputerBoard(board);
        if(!placeShips(game)) return null;
        game.setShootsRemaining(25);
        game.setShipsRemaining(10);
        game.setStatus(GameStatus.IN_PROGRESS);


        return game;
    }

    public void handleShoot(Game currentGame, int row, int col) {

        // Check, if player already shot to this cell
        if(currentGame.getComputerBoard().getGrid()[row][col].getIsHit()) return;
        // Check, if shoot is within gameBoard bounds
        if(row >= 0 && row < currentGame.getComputerBoard().getSize() && col >= 0 && col < currentGame.getComputerBoard().getSize()) {
            Cell targetCell = currentGame.getComputerBoard().getGrid()[row][col];
            targetCell.setIsHit(true);
            if(!targetCell.getIsShip()) {
                // Missed shot
                currentGame.setShootResultMessage("You've missed!");
                currentGame.setShootsRemaining(currentGame.getShootsRemaining()-1);
                // Check if player still have remaining shoots
                if(currentGame.getShootsRemaining() <= 0) {
                    currentGame.setStatus(GameStatus.LOST);
                }
            } else {
                // Successful shot
                // Find ship, that was hit
                Ship targetShip = findShipByCoordinates(currentGame, row, col);
                if (targetShip == null) return;
                targetShip.setHitPoints(targetShip.getHitPoints()-1);
                if(targetShip.getHitPoints() <= 0) {
                    // Handle ship destruction
                    handleShipDestruction(currentGame, targetShip);
                    currentGame.setShootResultMessage("You've destroyed a ship!");
                    // Check, if it was the last ship
                    if(currentGame.getShips().size() <= 0) {
                        currentGame.setStatus(GameStatus.WON);
                    }
                } else {
                    currentGame.setShootResultMessage("You've hit a ship!");
                }
            }
        }
    }

    private Ship findShipByCoordinates(Game currentGame, int targetRow, int targetCol) {

        for(int i = 0; i < currentGame.getShips().size(); i++) {
            Ship currentShip = currentGame.getShips().get(i);
            // Loop through current ship's coordinates
            for(int j = 0; j < currentShip.getCoordinates().length; j++) {
                if(currentShip.getCoordinates()[j][0] == targetRow && currentShip.getCoordinates()[j][1] == targetCol) {
                    return currentShip;
                }
            }
        }
        // No ship was found with given coordinates
        return null;
    }


    private void handleShipDestruction(Game currentGame, Ship targetShip) {

        // Make shoots around target ship
        for(int i = 0; i < targetShip.getCoordinates().length; i++) {

            int targetRow = targetShip.getCoordinates()[i][0];
            int targetCol = targetShip.getCoordinates()[i][1];

            for(int row = targetRow-1; row <= targetRow+1; row++) {
                for(int col = targetCol-1; col <= targetCol+1; col++) {
                    // Check if the current cell is within the board boundaries
                    if (row >= 0 && row < currentGame.getComputerBoard().getSize() && col >= 0 && col < currentGame.getComputerBoard().getSize()) {
                        currentGame.getComputerBoard().getGrid()[row][col].setIsHit(true);
                    }
                }
            }
        }
        currentGame.getShips().remove(targetShip);
    }

    private Board initializeBoard(int boardSize) {
        Board board = new Board();
        board.setGrid(new Cell[boardSize][boardSize]);
        board.setSize(boardSize);
        for(int i = 0; i < board.getSize(); i++) {
            for(int j = 0; j < board.getSize(); j++) {
                board.getGrid()[i][j] = new Cell();
                board.getGrid()[i][j].setIsShip(false);
                board.getGrid()[i][j].setIsHit(false);
            }
        }
        return board;
    }

    private boolean placeShips(Game currentGame) {
        List<ShipType> shipTypes = Arrays.asList(
                ShipType.CARRIER, ShipType.CARRIER, ShipType.CARRIER,
                ShipType.BATTLESHIP, ShipType.BATTLESHIP, ShipType.BATTLESHIP,
                ShipType.CRUISER, ShipType.CRUISER,
                ShipType.SUBMARINE,
                ShipType.DESTROYER
        );

        Collections.shuffle(shipTypes);

        for (ShipType shipType : shipTypes) {
            if(!placeShipRandomly(currentGame, shipType)) return false;
        }
        return true;
    }

    private boolean placeShipRandomly(Game currentGame, ShipType shipType) {

        Ship ship = new Ship();
        ship.setType(shipType);

        int shipSize = switch (shipType) {
            case CARRIER -> 1;
            case BATTLESHIP -> 2;
            case CRUISER -> 3;
            case SUBMARINE -> 4;
            case DESTROYER -> 5;
        };

        ship.setHitPoints(shipSize);

        int attempts = 0;
        int maxAttempts = 1000;

        while(attempts < maxAttempts) {
            int row = (int) (Math.random() * currentGame.getComputerBoard().getSize());
            int col = (int) (Math.random() * currentGame.getComputerBoard().getSize());
            boolean isHorizontal = Math.random() < 0.5;



            if(canPlaceShip(currentGame.getComputerBoard(), row, col, shipSize, isHorizontal)) {
                // Place the ship on the board
                ship.setCoordinates(new int[shipSize][2]);
                for(int i = 0; i < shipSize; i++) {
                    if(isHorizontal) {
                        currentGame.getComputerBoard().getGrid()[row][col+i].setIsShip(true);
                        ship.getCoordinates()[i][0] = row;
                        ship.getCoordinates()[i][1] = col+i;
                    } else {
                        currentGame.getComputerBoard().getGrid()[row+i][col].setIsShip(true);
                        ship.getCoordinates()[i][0] = row+i;
                        ship.getCoordinates()[i][1] = col;
                    }
                }
                currentGame.getShips().add(ship);
                return true;
            }
            attempts++;
        }

        return false;

    }

    private boolean canPlaceShip(Board board, int startRow, int startCol, int shipSize, boolean isHorizontal) {
        int endRow = isHorizontal ? startRow : startRow + shipSize - 1;
        int endCol = isHorizontal ? startCol + shipSize - 1 : startCol;

        // Check if the ship goes out of bounds
        if(endRow >= board.getSize() || endCol >= board.getSize()) {
            return false;
        }

        // Check if the ship overlaps with existing ships or is too close to them
        for(int i = startRow-1; i <= endRow+1; i++) {
            for(int j = startCol-1; j <= endCol+1; j++) {
                // Check if the current cell is within the board boundaries
                if (i >= 0 && i < board.getSize() && j >= 0 && j < board.getSize()) {
                    // Check if the cell or its neighbors contain a ship
                    if (board.getGrid()[i][j].getIsShip()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
