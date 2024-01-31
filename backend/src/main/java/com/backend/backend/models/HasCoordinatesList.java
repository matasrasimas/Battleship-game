package com.backend.backend.models;

import java.util.List;

public interface HasCoordinatesList {
    List<Coordinates> getCoordinates();
    void setCoordinates(List<Coordinates> coordinates);
}
