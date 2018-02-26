import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private static int VIRTUAL_TOP;
	private static int VIRTUAL_BOT;
	
	private static int size;
	private int openSites = 0;
	private int[][] grid;
	
	private boolean percolates = false;
	
	private WeightedQuickUnionUF wqUn;
	
	public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("Cannot instantiate model of size <= 0");
		}
		else {
			size = n;
			grid = new int[size][size];
			
			VIRTUAL_TOP = size * size;
			VIRTUAL_BOT = size * size + 1;
			
			wqUn = new WeightedQuickUnionUF(size*size + 2);
			
			for (int i = 0; i < n; i++) {
				wqUn.union(gridToWQUN(0, i), VIRTUAL_TOP);
				wqUn.union(gridToWQUN(size-1, i), VIRTUAL_BOT);
			}
			
			StdRandom.setSeed(System.currentTimeMillis());
			
			int openX = StdRandom.uniform(size) + 1;
			int openY = StdRandom.uniform(size) + 1;
			
			while (!percolates) {
				openX = StdRandom.uniform(size) + 1;
				openY = StdRandom.uniform(size) + 1;
				open(openX, openY);
//				printGrid();
			}
			
//			StdOut.println("System percolates!!!");
		}
	}
	
	public void open(int row, int col) {
		if (grid[row - 1][col - 1] != 1)
		{
			grid[row - 1][col - 1] = 1;
			openSites++;
		}
		
		performUnions(row - 1, col - 1);
	}
	
	public boolean isOpen(int row, int col) {
		return grid[row - 1][col - 1] == 1;
	}
	
	public boolean isFull(int row, int col) {
		int wqunCoord = gridToWQUN(row-1, col-1);
		return isOpen(row, col) && wqUn.connected(wqunCoord, VIRTUAL_TOP);
	}
	
	public int numberOfOpenSites() {
		return openSites;
	}
	
	public boolean percolates() {
		return wqUn.connected(VIRTUAL_TOP, VIRTUAL_BOT);
	}
	
	private void performUnions(int row, int col) {
		int up;
		int down;
		int left;
		int right;
		
		int wqUnCoord = gridToWQUN(row, col);
		
		if (row != 0) {
			if (grid[row - 1][col] == 1) {
				up = gridToWQUN(row - 1, col);
				wqUn.union(wqUnCoord, up);
			}
		}
		
		if (row != size - 1) {
			if (grid[row + 1][col] == 1) {
				down = gridToWQUN(row + 1, col);
				wqUn.union(wqUnCoord, down);
			}
		}
		
		if (col != 0) {
			if (grid[row][col - 1] == 1) {
				left = gridToWQUN(row, col - 1);
				wqUn.union(wqUnCoord, left);
			}
		}
		
		if (col != size - 1) {
			if (grid[row][col + 1] == 1) {
				right = gridToWQUN(row, col + 1);
				wqUn.union(wqUnCoord, right);
			}
		}
		
		percolates = percolates();
//		StdOut.println("Number of Components: " + wqUn.count());
	}
	
	private void runSim() {
		StdRandom.setSeed(System.currentTimeMillis());
		
		int openX = StdRandom.uniform(size) + 1;
		int openY = StdRandom.uniform(size) + 1;
		
		while (!percolates) {
			openX = StdRandom.uniform(size) + 1;
			openY = StdRandom.uniform(size) + 1;
			open(openX, openY);
//			printGrid();
		}
	}
	
	public static void main(String[] args) {
		StdOut.println("Specify size of grid: " );
		int runSize = StdIn.readInt();
		Percolation test = new Percolation(runSize);
		StdRandom.setSeed(System.currentTimeMillis());
		
		while (!test.percolates) {
			int openX = StdRandom.uniform(runSize) + 1;
			int openY = StdRandom.uniform(runSize) + 1;
			test.open(openX, openY);
			test.printGrid();

			if (test.percolates) {
				StdOut.println("SYSTEM PERCOLATES!!!");
				System.exit(0);
			}
		}
	}
	
	private void printGrid() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (j < size - 1) {
					StdOut.print(grid[i][j] + " ");
				}
				else {
					StdOut.print(grid[i][j] + "\n");
				}
			}
		}
		StdOut.println();
	}
	
	private static int gridToWQUN(int row, int col)
	{
		return size * row + col;
	}
}
