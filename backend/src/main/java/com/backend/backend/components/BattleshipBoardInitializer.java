package com.backend.backend.components;

import com.backend.backend.components.interfaces.BoardInitializer;
import com.backend.backend.models.Board;
import com.backend.backend.models.Cell;
import com.backend.backend.models.Game;
import org.springframework.stereotype.Component;

@Component
public class BattleshipBoardInitializer implements BoardInitializer<Game> {

    private static Game game;
    public void initialize(Game targetGame) {
        game = targetGame;
        game.setBoard(new Board(10));
        initializeBoardGrid();
        initializeGridCells();
    }

    private static void initializeGridCells() {
        for(int col = 0; col < game.getBoard().getSize(); col++) {
            for(int row = 0; row < game.getBoard().getSize(); row++) {
                initializeGridCell(col, row);
                initializeShipProperties(col, row);
            }
        }
    }

    private static void initializeShipProperties(int col, int row) {
        game.getBoard().getGrid()[col][row].setIsShip(false);
        game.getBoard().getGrid()[col][row].setIsHit(false);
    }

    private static void initializeGridCell(int col, int row) {
        game.getBoard().getGrid()[col][row] = new Cell();
    }

    private static void initializeBoardGrid() {
        int boardSize = game.getBoard().getSize();
        game.getBoard().setGrid(new Cell[boardSize][boardSize]);
    }
}
