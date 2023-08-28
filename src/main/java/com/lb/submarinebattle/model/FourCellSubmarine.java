package com.lb.submarinebattle.model;

import java.util.List;
import java.util.Random;

import static org.hibernate.annotations.UuidGenerator.Style.RANDOM;

public class FourCellSubmarine extends Submarine{



    public FourCellSubmarine(Orientation orientation) {
        super(4, Shape.getRandomShapeOfSize(4), orientation);
    }


}
