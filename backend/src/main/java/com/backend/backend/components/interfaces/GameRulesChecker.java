package com.backend.backend.components.interfaces;

import com.backend.backend.models.Ship;

public interface GameRulesChecker<T> {
    boolean satisfies(T game, Ship ship);
}
