package com.lb.submarinebattle.model;

public class TwoCellSubmarine extends Submarine{
    public TwoCellSubmarine(int size, Shape shape, Orientation orientation) {
        super(size, shape, orientation);
    }

    public static TwoCellSubmarine randomizeTwoCellSubmarine(){
        return new TwoCellSubmarine(2, Shape.TWO_CELL, Orientation.getRandomOrientationForShape(Shape.TWO_CELL));
    }
}
