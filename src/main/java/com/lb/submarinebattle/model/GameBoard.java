package com.lb.submarinebattle.model;

import java.util.*;


public class GameBoard {
    private static final Random RANDOM = new Random();
    private static final int BOARD_SIZE = 10;
    private static final int MAX_RETRIES = 500;
    private Cell[][] board = new Cell[BOARD_SIZE][BOARD_SIZE];
    private List<Submarine> submarines = new ArrayList<>();
    private Set<Cell> occupiedCells = new HashSet<>();
    private Set<Cell>adjacentCells = new HashSet<>();
    private List<Cell> allCells;



    public GameBoard() {
        initializeBoard();
    }



    /**
     * Input: none
     * Output: initializes 2d bard.
     * Comment:
     */
    private void initializeBoard() {
        allCells = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Cell cell = new Cell(i, j);
                board[i][j] = cell;
                allCells.add(cell);
            }
        }
    }



    /**
     * Input: none
     * Output: all cells of the board in list.
     * Comment:
     */
    private List<Cell> getAllCells() {
        return allCells;
    }



    /**
     * Input: a submarine
     * Output: suitable spot/cell on the board from which the provides submarine can be placed without conflict.
     * Comment:
     */
    private Cell findSuitableSpot(Submarine submarine) {
        while (true) {  // Infinite loop until a suitable spot is found.
            // 1. Randomly select a starting cell on the board.
            Cell startingCell = getRandomCell();

            // 2. Get the list of cells the submarine would occupy based on its shape and orientation.
            List<Cell> cellsToOccupy = computeCellsToOccupy(submarine, startingCell);

            boolean isSuitable = true;

            // 3. Check whether all those cells are unoccupied and within the board's boundaries.
            for (Cell cell : cellsToOccupy) {
                if (cell.getOccupied() || !isCellWithinBoard(cell)) {
                    isSuitable = false;
                    break;
                }
            }

            // 4. Ensure the submarine is not adjacent to another, if the above checks passed.
            if (isSuitable && !isAdjacentToAnotherSubmarine(cellsToOccupy)) {
                return startingCell;  // This is the suitable spot we found!
            }

            // If not suitable, the loop continues and tries with a new random starting cell.
        }
    }



    /**
     * Input: none
     * Output: random available cell on board.
     * Comment:
     */
    private Cell getRandomCell() {
        List<Cell> availableCells = getAllCells();
        availableCells.removeAll(occupiedCells);
        availableCells.removeAll(adjacentCells);

        if(availableCells.isEmpty()) {
            // Handle the scenario where there are no available cells.
            // This shouldn't occur given your game rules, but it's a good safety check.
            throw new IllegalStateException("No available cells. This is unexpected given the current game rules and " +
                    "setup.");
        }

        int randomIndex = RANDOM.nextInt(availableCells.size());
        return availableCells.get(randomIndex);
    }



    /**
     * Input: list of submarines
     * Output: places all submarines of the list on the board.
     * Comment:
     */
    public void placeAllSubmarines(List<Submarine> submarinesToPlace) {
        if (submarinesToPlace == null) {
            throw new IllegalArgumentException("The submarinesToPlace list must not be null.");
        }

        for (Submarine submarine : submarinesToPlace) {
            boolean isPlaced = false;
            int retries = 0;
            while (!isPlaced && retries < MAX_RETRIES) {
                isPlaced = placeSubmarine(submarine);
                retries++;
            }

            if (!isPlaced) {
                throw new RuntimeException("Failed to place submarine after maximum retries.");
            }
        }
    }



    /**
     * Input: cell
     * Output: boolean whether any submarine was hit or not.
     * Comment:
     */
    public boolean attackCell(Cell targetCell) {
        if (targetCell == null) {
            throw new IllegalArgumentException("Target cell cannot be null.");
        }

        if (targetCell.getHit()) {
            throw new IllegalStateException("Cell has already been attacked.");
        }

        // Check if the target cell is part of any submarine's location.
        for (Submarine submarine : submarines) {
            if (submarine.getLocationCells().contains(targetCell)) {
                targetCell.setHit(true);
                return true;  // This was a hit!
            }
        }

        // If we reached here, it was a miss.
        return false;
    }



    /**
     * Input: none
     * Output: boolean whether all submarines were sunk.
     * Comment:
     */
    public boolean haveAllSubmarinesSunk() {
        if (submarines == null || submarines.isEmpty()) {
            throw new IllegalStateException("Submarines list is not initialized or empty.");
        }

        // Check if all submarines on the board have been sunk.
        for (Submarine sub : submarines) {
            if (!sub.isSunk()) {
                return false;
            }
        }
        return true;
    }



    @FunctionalInterface
    interface ShapeOrientationFunction {
        List<Cell> compute(GameBoard gameBoard, Cell startCell);
    }

    private static final Map<Shape, Map<Orientation, ShapeOrientationFunction>> shapeOrientations = new HashMap<>();

    static {
        Map<Orientation, ShapeOrientationFunction> singleCellMappings = new HashMap<>();
        singleCellMappings.put(Orientation.UP, (board, startCell) -> Collections.singletonList(startCell));
        shapeOrientations.put(Shape.SINGLE_CELL, singleCellMappings);

        Map<Orientation, ShapeOrientationFunction> twoCellMappings = new HashMap<>();
        twoCellMappings.put(Orientation.UP, (board, startCell) -> Arrays.asList(startCell,
                board.getCell(startCell.getRow() + 1, startCell.getColumn())));
        twoCellMappings.put(Orientation.LEFT, (board, startCell) -> Arrays.asList(startCell,
                board.getCell(startCell.getRow(), startCell.getColumn() - 1)));
        shapeOrientations.put(Shape.TWO_CELL, twoCellMappings);

        // Continue to map other shapes and their orientations...
    }

    private List<Cell> computeCellsToOccupy(Submarine submarine, Cell startingCell) {
        Shape shape = submarine.getShape();
        Orientation orientation = submarine.getOrientation();

        ShapeOrientationFunction func = shapeOrientations.get(shape).get(orientation);
        if (func == null) {
            throw new IllegalArgumentException("Unsupported shape and orientation combination.");
        }
        return func.compute(this, startingCell);
    }

    // Helper method to fetch a cell
    public Cell getCell(int row, int col) {
        return board[row][col];
    }


    private boolean isCellWithinBoard(Cell cell) {
        int row = cell.getRow();
        int col = cell.getColumn();

        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }



    private boolean isAdjacentToAnotherSubmarine(List<Cell> cellsToOccupy) {
        for (Cell cell : cellsToOccupy) {
            if (adjacentCells.contains(cell)) {
                return true; // One of the cells is adjacent to another submarine.
            }
        }
        return false; // None of the cells are adjacent to another submarine.
    }



    public boolean placeSubmarine(Submarine submarine) {
        Cell startingCell = findSuitableSpot(submarine);
        if (startingCell != null) {
            ArrayList<Cell> cellsToOccupy = (ArrayList<Cell>) computeCellsToOccupy(submarine, startingCell);

            for (Cell cell : cellsToOccupy) {
                cell.setOccupied(true);
                submarine.setLocationCells(cellsToOccupy);
                // Assuming you have this method in Submarine class.
            }

            // After placing the submarine, compute its adjacent cells and add them to the board's set.
            addAdjacentCellsToBoard(submarine);

            return true;
        }
        return false;
    }



    private Set<Cell> computeAdjacentCells(List<Cell> locationCells) {
        Set<Cell> adjacentCellsSet = new HashSet<>();

        for (Cell cell : locationCells) {
            int row = cell.getRow();
            int col = cell.getColumn();

            // Iterate over the neighbors of the cell.
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    // Skip the central cell itself.
                    if (i == 0 && j == 0) {
                        continue;
                    }

                    int newRow = row + i;
                    int newCol = col + j;

                    // Check if the new cell is within the board and is not one of the location cells.
                    if (isCellWithinBoard(getCell(newRow, newCol)) && !locationCells
                            .contains(getCell(newRow, newCol))) {adjacentCellsSet.add(getCell(newRow, newCol));
                    }
                }
            }
        }

        return adjacentCellsSet;
    }



    public void addAdjacentCellsToBoard(Submarine submarine) {
        Set<Cell> cellsToBeAdded = computeAdjacentCells(submarine.getLocationCells());
        adjacentCells.addAll(cellsToBeAdded);
    }








    // More methods as needed...
}
