package com.backend.backend.models;

public class Coordinates {

    private int col;
    private int row;

    public Coordinates() {

    }
    public Coordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

}
