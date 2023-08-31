package com.lb.submarinebattle.model;

public class ThreeCellSubmarine extends Submarine{
    public ThreeCellSubmarine(int size, Shape shape, Orientation orientation) {
        super(size, shape, orientation);
    }

    public static ThreeCellSubmarine randomizeThreeCellSubmarine(){
        Shape randomizedShape = Shape.getRandomShapeOfSize(3);
        return new ThreeCellSubmarine(3, randomizedShape, Orientation
                .getRandomOrientationForShape(randomizedShape));
    }
}
