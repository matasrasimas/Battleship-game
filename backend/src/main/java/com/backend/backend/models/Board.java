package com.backend.backend.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;


public class Board implements Serializable {

    private Cell[][] grid;
    private int size;

    public Board(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }
}
