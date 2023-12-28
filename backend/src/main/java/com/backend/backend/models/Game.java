package com.backend.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Game implements Serializable {

    private Board computerBoard;
    private List<Ship> ships;
    private int shootsRemaining;
    private int shipsRemaining;

    private String shootResultMessage;
    private GameStatus status;

}
