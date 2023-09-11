package com.lb.submarinebattle.model;

import java.util.ArrayList;
import java.util.List;

public class GameSetup {
    public static List<Submarine> generateSubmarines(){
        List<Submarine> submarinesToPlace = new ArrayList<>();
        submarinesToPlace.add(FourCellSubmarine.randomizeFourCellSubmarine());
        submarinesToPlace.add(ThreeCellSubmarine.randomizeThreeCellSubmarine());
        submarinesToPlace.add(ThreeCellSubmarine.randomizeThreeCellSubmarine());
        submarinesToPlace.add(TwoCellSubmarine.randomizeTwoCellSubmarine());
        submarinesToPlace.add(TwoCellSubmarine.randomizeTwoCellSubmarine());
        submarinesToPlace.add(TwoCellSubmarine.randomizeTwoCellSubmarine());
        submarinesToPlace.add(OneCellSubmarine.createOneCellSubmarine());
        submarinesToPlace.add(OneCellSubmarine.createOneCellSubmarine());
        submarinesToPlace.add(OneCellSubmarine.createOneCellSubmarine());
        submarinesToPlace.add(OneCellSubmarine.createOneCellSubmarine());
        return submarinesToPlace;
    }
}
