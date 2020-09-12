package life;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Life {
    static private char ACTIVE = 'X';
    static private char INACTIVE = '_';
    static public char empty[][] = {
            {INACTIVE, INACTIVE, INACTIVE},
            {INACTIVE, INACTIVE, INACTIVE},
            {INACTIVE, INACTIVE, INACTIVE}
    };
    static public char centerActive[][] = {
            {INACTIVE, INACTIVE, INACTIVE},
            {INACTIVE, ACTIVE, INACTIVE},
            {INACTIVE, INACTIVE, INACTIVE}
    };

    static public char topLeftActive[][] = {
            {ACTIVE, INACTIVE, INACTIVE},
            {INACTIVE, INACTIVE, INACTIVE},
            {INACTIVE, INACTIVE, INACTIVE}
    };

    static public char topRightActive[][] = {
            {INACTIVE, INACTIVE, ACTIVE},
            {INACTIVE, INACTIVE, INACTIVE},
            {INACTIVE, INACTIVE, INACTIVE}
    };

    static public char example2[][] = {
            {ACTIVE, ACTIVE, INACTIVE},
            {INACTIVE, ACTIVE, INACTIVE},
            {INACTIVE, INACTIVE, INACTIVE}
    };

    static public char example3[][] = {
            {ACTIVE, ACTIVE, ACTIVE},
            {INACTIVE, ACTIVE, INACTIVE},
            {INACTIVE, ACTIVE, INACTIVE}
    };

    static public char example4[][] = {
            {ACTIVE, ACTIVE, ACTIVE},
            {INACTIVE, ACTIVE, INACTIVE},
            {INACTIVE, INACTIVE, INACTIVE}
    };

    private char cells[][];
    private char nextCells[][] = {
            {INACTIVE, INACTIVE, INACTIVE},
            {INACTIVE, INACTIVE, INACTIVE},
            {INACTIVE, INACTIVE, INACTIVE}
    };

    public Life() {
    }

    // for testing
    public Life(char[][] template) {
        cells = template;
    }

    public static void main(String args[]) {
        run(topLeftActive);
        run(topRightActive);
        run(example2);
        run(example3);
        run(example4);
    }

    public static void run(char[][] initialCells) {
        Life life = new Life();

        life.print(initialCells);

        System.out.println("\n----------------------------------------------\n");

        var cells = life.iterate(initialCells);

        life.print(cells);

        System.out.println("\n==============================================\n");
    }

    public void print(char[][] cells) {
        for (char[] row : cells) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    public char[][] iterate(char[][] initialCells) {
        cells = initialCells;
        nextCells = new char[cells.length][cells[0].length];
        for(var row : nextCells) {
            Arrays.fill(row, INACTIVE);
        }

        for (int x = 0; x < initialCells.length; x++) {
            for (int y = 0; y < initialCells[x].length; y++) {
                applyRules(new Cell(x, y));
            }
        }

        return nextCells;
    }

    private void applyRules(Cell cell) {
        int active = activeNeighborCount(cell);
        if (isActive(cell)) {
            if (active == 2 || active == 3) {
                becomeActive(cell);
            }
        } else {
            if (active == 3) {
                becomeActive(cell);
            }
        }
    }

    private int activeNeighborCount(Cell cell) {
        int active = 0;
        List<Cell> neighbors = neighborsOf(cell);

        for (Cell neighbor : neighbors) {
            if (isActive(neighbor)) {
                active++;
            }
        }
        return active;
    }

    private void becomeActive(Cell cell) {
        nextCells[cell.x][cell.y] = ACTIVE;
    }

    private boolean isActive(Cell cell) {
        return cells[cell.x][cell.y] == ACTIVE;
    }

    List<Cell> neighborsOf(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();

        for (int x = cell.x - 1; x <= cell.x + 1; x++) {
            for (int y = cell.y - 1; y <= cell.y + 1; y++) {
                if (x >= 0 && y >= 0 &&
                        x < cells.length &&
                        y < cells[x].length &&
                        !(x == cell.x && y == cell.y)) {
                    neighbors.add(new Cell(x, y));
                }
            }
        }

        return neighbors;
    }
}
