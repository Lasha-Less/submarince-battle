package com.lb.submarinebattle.model;

public class OneCellSubmarine extends Submarine{
    public OneCellSubmarine(int size, Shape shape, Orientation orientation) {
        super(size, shape, orientation);
    }

    public static OneCellSubmarine createOneCellSubmarine(){
        return new OneCellSubmarine(1, Shape.SINGLE_CELL, Orientation.UP);
    }
}
