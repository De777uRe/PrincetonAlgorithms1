import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private static int VIRTUAL_TOP;
	private static int VIRTUAL_BOT;
	
	private static int size;
	private int openSites = 0;
	private int[][] grid;
	
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
				for (int j = 0; j < n; j++) {
					grid[i][j] = 0;
				}
			}
			
			for (int i = 0; i < n; i++) {
				wqUn.union(gridToWQUN(0, i), VIRTUAL_TOP);
				wqUn.union(gridToWQUN(size-1, i), VIRTUAL_BOT);
			}
		}
	}
	
	public void open(int row, int col) {
		if (grid[row][col] != 1)
		{
			grid[row][col] = 1;
			openSites++;
		}
	}
	
	public boolean isOpen(int row, int col) {
		return grid[row][col] == 1;
	}
	
	public boolean isFull(int row, int col) {
		return wqUn.connected(grid[row][col], VIRTUAL_TOP);
	}
	
	public int numberOfOpenSites() {
		return openSites;
	}
	
	public boolean percolates() {
		return wqUn.connected(VIRTUAL_TOP, VIRTUAL_BOT);
	}
	
	public static void main(String[] args) {
		Percolation test = new Percolation(13);
		test.printGrid();
		
		StdOut.println(gridToWQUN(0, 9));
		StdOut.println(gridToWQUN(1, 12));
		StdOut.println(gridToWQUN(5, 11));
	}
	
	public void printGrid() {
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
	}
	
	public static int gridToWQUN(int row, int col)
	{
		return size * row + col;
	}
}
