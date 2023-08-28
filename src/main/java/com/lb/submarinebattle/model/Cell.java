package com.lb.submarinebattle.model;

import java.util.Objects;

public class Cell {
    private int row;
    private int column;
    private boolean hit = false;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean isHit() {
        return hit;
    }

    public void markAsHit() {
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
