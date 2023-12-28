package com.backend.backend.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Ship implements Serializable {

    private ShipType type;
    private int hitPoints;
    private int[][] coordinates;

}
