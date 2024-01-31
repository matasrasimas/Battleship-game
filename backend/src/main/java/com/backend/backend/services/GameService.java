package com.backend.backend.services;

import com.backend.backend.models.Game;

public interface GameService<T> {
    T startNewGame();
}
