package com.backend.backend.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

public class Game implements Serializable {

    private Board board;
    private List<Ship> ships;
    private int shootsRemaining;
    private int shipsRemaining;

    private String shootResultMessage;
    private GameStatus status;

    public Game() {

    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getShipsRemaining() {
        return shipsRemaining;
    }

    public void setShipsRemaining(int shipsRemaining) {
        this.shipsRemaining = shipsRemaining;
    }

    public int getShootsRemaining() {
        return shootsRemaining;
    }

    public void setShootsRemaining(int shotsRemaining) {
        this.shootsRemaining = shotsRemaining;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public String getShootResultMessage() {
        return shootResultMessage;
    }

    public void setShootResultMessage(String shootResultMessage) {
        this.shootResultMessage = shootResultMessage;
    }
}
