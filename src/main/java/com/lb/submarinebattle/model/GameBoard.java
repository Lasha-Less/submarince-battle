package com.lb.submarinebattle.model;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    private static final int BOARD_SIZE = 10;
    private Cell[][] board = new Cell[BOARD_SIZE][BOARD_SIZE];
    private List<Submarine> submarines = new ArrayList<>();

    public GameBoard() {
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }

    public boolean placeSubmarine(Submarine submarine) {
        // 1. Randomly choose a starting cell for the submarine.
        // 2. Based on the submarine's shape and orientation, determine the list of cells it will occupy.
        // 3. Check if all those cells are available (not already occupied by another submarine).
        // 4. If available, place the submarine on those cells and add it to the submarines list.
        // 5. If not available, retry or return false.

        // Return true if the submarine is successfully placed, false otherwise.
        return false;
    }

    public boolean attackCell(Cell targetCell) {
        // Loop over all submarines and check if any submarine is hit by the attack on targetCell.
        // If a submarine is hit, mark that cell as hit and return true.
        // If no submarine is hit, mark the cell as a miss and return false.
        return false;
    }

    public boolean isAllSubmarinesSunk() {
        // Check if all submarines on the board have been sunk.
        for (Submarine sub : submarines) {
            if (!sub.isSunk()) {
                return false;
            }
        }
        return true;
    }

    // More methods as needed...
}
