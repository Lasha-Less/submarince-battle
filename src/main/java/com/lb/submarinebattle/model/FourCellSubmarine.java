package com.lb.submarinebattle.model;

public class FourCellSubmarine extends Submarine{

    private FourCellSubmarine(int size, Shape shape, Orientation orientation) {
        super(size, shape, orientation);
    }

    public static FourCellSubmarine randomizeFourCellSubmarine() {
        Shape randomizedShape = Shape.getRandomShapeOfSize(4);
        return new FourCellSubmarine(4, randomizedShape, Orientation.getRandomOrientationForShape(randomizedShape));
    }


}
