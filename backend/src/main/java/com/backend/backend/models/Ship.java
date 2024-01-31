package com.backend.backend.models;

import java.io.Serializable;
import java.util.List;


public class Ship implements Serializable, HasCoordinatesList {

    private int hitPoints;

    private boolean isHorizontal;

    private List<Coordinates> coordinates;

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public List<Coordinates> getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(List<Coordinates> coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }

}
