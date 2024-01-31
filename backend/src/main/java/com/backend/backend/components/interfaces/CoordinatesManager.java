package com.backend.backend.components.interfaces;

import com.backend.backend.models.Coordinates;
import com.backend.backend.models.Ship;

import java.util.List;

public interface CoordinatesManager<T> {

    Coordinates generateCoordinatesRandomly(int boardSize);
    void removeStartCoordinates(T ship);
    void initializeStartCoordinates(T ship, Coordinates startCoords);
    Coordinates getStartCoordinates(T ship);
    Coordinates calculateEndCoordinates(T ship);
}
