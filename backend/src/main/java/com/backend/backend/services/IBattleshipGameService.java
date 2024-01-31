package com.backend.backend.services;

public interface IBattleshipGameService<T> extends GameService<T> {
    void handleShoot(T game, int row, int col);
}
