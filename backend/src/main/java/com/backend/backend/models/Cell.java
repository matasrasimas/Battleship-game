package com.backend.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
public class Cell implements Serializable {

    private boolean isShip;
    private boolean isHit;

    public boolean getIsShip() {
        return isShip;
    }

    public boolean getIsHit() {
        return isHit;
    }

    public void setIsShip(boolean isShip) {
        this.isShip = isShip;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }


}
