package com.backend.backend.services;

import com.backend.backend.components.interfaces.GameInitializer;
import com.backend.backend.components.ShootHandler;
import com.backend.backend.models.*;
import org.springframework.stereotype.Service;
@Service
public class BattleshipGameService implements IBattleshipGameService<Game> {

    private final GameInitializer<Game> gameInitializer;

    public BattleshipGameService(GameInitializer<Game> gameInitializer) {
        this.gameInitializer = gameInitializer;
    }

    public Game startNewGame() {
        return gameInitializer.initialize();
    }

    public void handleShoot(Game currentGame, int row, int col) {
        ShootHandler.shoot(currentGame, row, col);
    }

}
