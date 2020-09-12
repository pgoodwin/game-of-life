package life;

import java.util.ArrayList;
import java.util.List;

class Life {
    static private char ACTIVE = 'X';
    static private char INACTIVE = '_';
    static public char empty[][] = {
            { INACTIVE, INACTIVE, INACTIVE},
            { INACTIVE, INACTIVE, INACTIVE},
            { INACTIVE, INACTIVE, INACTIVE}
    };
    static public char centerActive[][] = {
            { INACTIVE, INACTIVE, INACTIVE},
            { INACTIVE, ACTIVE, INACTIVE},
            { INACTIVE, INACTIVE, INACTIVE}
    };

    static public char topLeftActive[][] = {
            { ACTIVE, INACTIVE, INACTIVE},
            { INACTIVE, INACTIVE, INACTIVE},
            { INACTIVE, INACTIVE, INACTIVE}
    };

    static public char topRightActive[][] = {
            { INACTIVE, INACTIVE, ACTIVE},
            { INACTIVE, INACTIVE, INACTIVE},
            { INACTIVE, INACTIVE, INACTIVE}
    };

    static public char example2[][] = {
            { ACTIVE, ACTIVE, INACTIVE},
            { INACTIVE, ACTIVE, INACTIVE},
            { INACTIVE, INACTIVE, INACTIVE}
    };

    static public char example3[][] = {
            { ACTIVE, ACTIVE, ACTIVE},
            { INACTIVE, ACTIVE, INACTIVE},
            { INACTIVE, ACTIVE, INACTIVE}
    };

    static public char example4[][] = {
            { ACTIVE, ACTIVE, ACTIVE},
            { INACTIVE, ACTIVE, INACTIVE},
            { INACTIVE, INACTIVE, INACTIVE}
    };

    private char cells[][];
    private char nextCells[][] = {
            { INACTIVE, INACTIVE, INACTIVE},
            { INACTIVE, INACTIVE, INACTIVE},
            { INACTIVE, INACTIVE, INACTIVE}
    };

    public Life(char[][] initialState) {
        cells = initialState;
    }

    public static void main(String args[]) {
        run(topLeftActive);
        run(topRightActive);
        run(example2);
        run(example3);
        run(example4);
    }

    public static void run(char[][] cells) {
        Life life = new Life(cells);

        life.print();

        System.out.println("\n----------------------------------------------\n");

        life.iterate();

        life.print();

        System.out.println("\n==============================================\n");
    }

    private void iterate() {
        for (int x=0; x < 3; x++) {
            for (int y=0; y < 3; y++) {
                applyRules(new Cell(x, y));
            }
        }

        cells = nextCells;
        nextCells = new char[][] {
                { INACTIVE, INACTIVE, INACTIVE},
                { INACTIVE, INACTIVE, INACTIVE},
                { INACTIVE, INACTIVE, INACTIVE}
        };
    }

    private void applyRules(Cell cell) {
        int active = activeNeighborCount(cell);
        if (isActive(cell)) {
            if (activeNeighborCount(cell) > 3) {
                becomeInactive(cell);
            }
            if (active == 2 || active == 3) {
                becomeActive(cell);
            }
            if (activeNeighborCount(cell) < 2) {
                becomeInactive(cell);
            }
        } else {
            if (active == 3) {
                becomeActive(cell);
            }
        }
    }

    private int activeNeighborCount(Cell cell) {
        int active = 0;
        List<Cell> neighbors = neighborOf(cell);

        for (Cell neighbor : neighbors) {
            if (isActive(neighbor)) {
                active++;
            }

        }
        return active;
    }

    private void becomeInactive(Cell cell) {
        nextCells[cell.x][cell.y] = INACTIVE;
    }

    private void becomeActive(Cell cell) {
        nextCells[cell.x][cell.y] = ACTIVE;
    }

    private boolean isActive(Cell cell) {
        return cells[cell.x][cell.y] == ACTIVE;
    }

    List<Cell> neighborOf(Cell cell) {
        List<Cell> neighbor = new ArrayList<>();
        for (int x=0; x < 3; x++) {
            for (int y=0; y < 3; y++) {
                int xDistance = cell.x - x;
                int yDistance = cell.y - y;

                if (xDistance == 0 && yDistance == 0) {
                    continue;
                }

                if (!(Math.abs(xDistance) > 1 || Math.abs(yDistance) > 1))  {
                    neighbor.add(new Cell(x, y));
                }
            }
        }

        return neighbor;
    }

    private void print() {
        for (char[] row : cells) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}

class Cell {
    public int x, y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
}