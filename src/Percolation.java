import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int virtualTop;
    private int virtualBot;

    private int size;
    private int openSites;
    private boolean[][] grid;

    private boolean percolates;

    private WeightedQuickUnionUF wqUn;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Cannot instantiate model of size <= 0");
        } else {
            size = n;
            openSites = 0;
            percolates = false;
            grid = new boolean[size][size];
            
//            for (int i = 0; i < size; i++) {
//                for (int j = 0; j < size; j++) {
//                    grid[i][j] = false;
//                }
//            }

            virtualTop = size * size;
            virtualBot = size * size + 1;

            wqUn = new WeightedQuickUnionUF(size * size + 2);

            for (int i = 0; i < n; i++) {
                wqUn.union(gridToWQUN(0, i), virtualTop);
                wqUn.union(gridToWQUN(size - 1, i), virtualBot);
            }

//            int openX;
//            int openY;
//            
//            while (!percolates) {
//                openX = StdRandom.uniform(size) + 1;
//                openY = StdRandom.uniform(size) + 1;
//                open(openX, openY);
//                 printGrid();
//            }
//
//             StdOut.println("System percolates!!!");
        }
    }

    public void open(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException("Input is outside grid range");
        }
        else {
            if (!grid[row - 1][col - 1]) {
                grid[row - 1][col - 1] = true;
                openSites++;
                
                performUnions(row - 1, col - 1);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException("Input is outside grid range");
        }
        else {
            return grid[row - 1][col - 1];
        }
    }

    public boolean isFull(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException("Input is outside grid range");
        }
        else {
            int wqunCoord = gridToWQUN(row - 1, col - 1);
            return isOpen(row, col) && wqUn.connected(wqunCoord, virtualTop);
        }
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return wqUn.connected(virtualTop, virtualBot);
    }

    private void performUnions(int row, int col) {
        int up;
        int down;
        int left;
        int right;

        int wqUnCoord = gridToWQUN(row, col);

        if (row != 0) {
            if (grid[row - 1][col]) {
                up = gridToWQUN(row - 1, col);
                wqUn.union(wqUnCoord, up);
            }
        }

        if (row != size - 1) {
            if (grid[row + 1][col]) {
                down = gridToWQUN(row + 1, col);
                wqUn.union(wqUnCoord, down);
            }
        }

        if (col != 0) {
            if (grid[row][col - 1]) {
                left = gridToWQUN(row, col - 1);
                wqUn.union(wqUnCoord, left);
            }
        }

        if (col != size - 1) {
            if (grid[row][col + 1]) {
                right = gridToWQUN(row, col + 1);
                wqUn.union(wqUnCoord, right);
            }
        }

        percolates = percolates();
    }

    public static void main(String[] args) {
        StdOut.println("Specify size of grid: ");
        int runSize = StdIn.readInt();
        Percolation test = new Percolation(runSize);

        while (!test.percolates) {
            int openX = StdRandom.uniform(runSize) + 1;
            int openY = StdRandom.uniform(runSize) + 1;
            test.open(openX, openY);
            test.printGrid();

            if (test.percolates) {
//                StdOut.println("SYSTEM PERCOLATES!!!");
            }
        }
    }

    private void printGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (j < size - 1) {
                    if (grid[i][j] == true) {
                        StdOut.print("1 ");
                    }
                    else {
                        StdOut.print("0 ");
                    }
                } else {
                    if (grid[i][j] == true) {
                        StdOut.print("1\n");
                    }
                    else {
                        StdOut.print("0\n");
                    }
                }
            }
        }
        StdOut.println();
    }

    private int gridToWQUN(int row, int col) {
        return size * row + col;
    }
}
