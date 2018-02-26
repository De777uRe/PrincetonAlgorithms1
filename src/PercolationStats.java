import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int numTrials = 0;
    private double[] thresholds;

    public PercolationStats(int size, int trials) {
        numTrials = trials;
        thresholds = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percSim = new Percolation(size);
            
          int openX;
          int openY;
          
          while (!percSim.percolates()) {
              openX = StdRandom.uniform(size) + 1;
              openY = StdRandom.uniform(size) + 1;
              percSim.open(openX, openY);
//               printGrid();
          }

            thresholds[i] = (double) percSim.numberOfOpenSites() / (double) (size * size);
        }

        StdOut.println("mean\t\t\t = " + mean());
        StdOut.println("stddev\t\t\t = " + stddev());
        StdOut.println("95% confidence interval\t = [" + confidenceLo() + ", " + confidenceHi() + "]");
    }

    public double mean() {
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        return StdStats.stddevp(thresholds);
    }

    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(numTrials));
    }

    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(numTrials));
    }

    public static void main(String[] args) {
        StdOut.println("Enter size of grid: ");
        int size = StdIn.readInt();
        StdOut.println("Enter number of trials: " );
        int trials = StdIn.readInt();

        PercolationStats statSim = new PercolationStats(size, trials);
    }
}
