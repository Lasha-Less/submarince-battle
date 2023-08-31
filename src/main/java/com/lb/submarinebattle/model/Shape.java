package com.lb.submarinebattle.model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public enum Shape {
    SINGLE_CELL(1),
    TWO_CELL(2),
    THREE_CELL_STRAIGHT(3),
    THREE_CELL_CURVED(3),
    FOUR_CELL_STRAIGHT(4),
    FOUR_CELL_L_SHAPED(4),
    FOUR_CELL_FLIP_L_SHAPED(4),
    FOUR_CELL_SQUARE(4),
    FOUR_CELL_PYRAMID(4),
    FOUR_CELL_Z_SHAPED(4),
    FOUR_CELL_FLIP_Z_SHAPED(4);

    private final int size;

    Shape(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public static List<Shape> getShapesOfSize(int size) {
        return Arrays.stream(Shape.values())
                .filter(shape -> shape.size == size)
                .collect(Collectors.toList());
    }

    public static Shape getRandomShapeOfSize(int size) {
        List<Shape> shapesOfSize = getShapesOfSize(size);
        if (shapesOfSize.isEmpty()) return null;
        int randomIndex = new Random().nextInt(shapesOfSize.size());
        return shapesOfSize.get(randomIndex);
    }
}
