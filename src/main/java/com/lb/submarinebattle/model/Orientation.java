package com.lb.submarinebattle.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Orientation {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    private static final Random RANDOM = new Random();

    public static Orientation getRandomOrientationForShape(Shape shape) {
        List<Orientation> availableOrientations = getOrientationsForShape(shape);
        return availableOrientations.get(RANDOM.nextInt(availableOrientations.size()));
    }

    private static List<Orientation> getOrientationsForShape(Shape shape) {
        switch (shape) {
            case TWO_CELL:
            case THREE_CELL_STRAIGHT:
            case FOUR_CELL_STRAIGHT:
                return Arrays.asList(UP, LEFT);
            case THREE_CELL_CURVED:
            case FOUR_CELL_L_SHAPED:
            case FOUR_CELL_FLIP_L_SHAPED:
            case FOUR_CELL_Z_SHAPED:
            case FOUR_CELL_FLIP_Z_SHAPED:
            case FOUR_CELL_PYRAMID:
                // Example, you might have specific orientations for this shape
                return Arrays.asList(UP, LEFT, DOWN, RIGHT);
            default:
                return Collections.emptyList();
        }
    }
}
