package com.backend.backend.components;

import com.backend.backend.components.interfaces.GameInitializer;
import com.backend.backend.components.interfaces.Placer;
import com.backend.backend.models.*;
import org.springframework.stereotype.Component;

@Component
public class BattleshipGameInitializer implements GameInitializer<Game> {

    private final Placer<Game> shipPlacer;

    public BattleshipGameInitializer(Placer<Game> shipPlacer) {
        this.shipPlacer = shipPlacer;
    }
    public Game initialize() {
        Game game = new Game();
        game.setShootsRemaining(25);
        game.setShipsRemaining(10);
        game.setShootResultMessage("");
        game.setStatus(GameStatus.IN_PROGRESS);
        shipPlacer.place(game);
        return game;
    }
}
