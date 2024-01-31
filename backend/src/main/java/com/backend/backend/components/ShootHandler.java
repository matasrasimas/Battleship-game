package com.backend.backend.components;

import com.backend.backend.models.Cell;
import com.backend.backend.models.Game;
import com.backend.backend.models.GameStatus;
import com.backend.backend.models.Ship;

public class ShootHandler {
    public static void shoot(Game currentGame, int row, int col) {

        // Check, if player already shot to this cell
        if (currentGame.getBoard().getGrid()[row][col].getIsHit()) return;
        // Check, if shoot is within gameBoard bounds
        if (row >= 0 && row < currentGame.getBoard().getSize() && col >= 0 && col < currentGame.getBoard().getSize()) {
            Cell targetCell = currentGame.getBoard().getGrid()[row][col];
            targetCell.setIsHit(true);
            if (!targetCell.isShip()) {
                // Missed shot
                currentGame.setShootResultMessage("You've missed!");
                currentGame.setShootsRemaining(currentGame.getShootsRemaining() - 1);
                // Check if player still have remaining shoots
                if (currentGame.getShootsRemaining() <= 0) {
                    currentGame.setStatus(GameStatus.LOST);
                }
            } else {
                // Successful shot
                // Find ship, that was hit
                Ship targetShip = findShipByCoordinates(currentGame, row, col);
                if (targetShip == null) return;
                targetShip.setHitPoints(targetShip.getHitPoints() - 1);
                if (targetShip.getHitPoints() <= 0) {
                    // Handle ship destruction
                    handleShipDestruction(currentGame, targetShip);
                    currentGame.setShootResultMessage("You've destroyed a ship!");
                    // Check, if it was the last ship
                    if (currentGame.getShips().size() <= 0) {
                        currentGame.setStatus(GameStatus.WON);
                    }
                } else {
                    currentGame.setShootResultMessage("You've hit a ship!");
                }
            }
        }
    }

    private static Ship findShipByCoordinates(Game currentGame, int targetRow, int targetCol) {

        for (int i = 0; i < currentGame.getShips().size(); i++) {
            Ship currentShip = currentGame.getShips().get(i);
            // Loop through current ship's coordinates
            for (int j = 0; j < currentShip.getCoordinates().size(); j++) {
                if (currentShip.getCoordinates().get(j).getRow() == targetRow && currentShip.getCoordinates().get(j).getCol() == targetCol) {
                    return currentShip;
                }
            }
        }
        // No ship was found with given coordinates
        return null;
    }

    private static void handleShipDestruction(Game currentGame, Ship targetShip) {

        // Make shoots around target ship
        for (int i = 0; i < targetShip.getCoordinates().size(); i++) {

            int targetRow = targetShip.getCoordinates().get(i).getRow();
            int targetCol = targetShip.getCoordinates().get(i).getCol();

            for (int row = targetRow - 1; row <= targetRow + 1; row++) {
                for (int col = targetCol - 1; col <= targetCol + 1; col++) {
                    // Check if the current cell is within the board boundaries
                    if (row >= 0 && row < currentGame.getBoard().getSize() && col >= 0 && col < currentGame.getBoard().getSize()) {
                        currentGame.getBoard().getGrid()[row][col].setIsHit(true);
                    }
                }
            }
        }
        currentGame.getShips().remove(targetShip);
    }
}
