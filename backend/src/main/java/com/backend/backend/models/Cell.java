package com.backend.backend.models;

import jakarta.persistence.*;

import java.io.Serializable;

public class Cell implements Serializable {

    private boolean isShip;
    private boolean isHit;

    public boolean getIsHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    public boolean isShip() {
        return isShip;
    }

    public void setIsShip(boolean isShip) {
        this.isShip = isShip;
    }

}
