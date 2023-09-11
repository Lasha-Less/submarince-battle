package com.lb.submarinebattle.model;

import java.util.Objects;

public class Cell {
    private int row;
    private int column;
    private boolean hit = false;
    private boolean isOccupied = false;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean getHit() {
        return hit;
    }

    public void setAsHit() {
        this.hit = true;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public boolean getOccupied() {
        return isOccupied;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell that = (Cell) o;
        return row == that.row && column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}
