package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] threshold;

    /** perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException(
                    "n and t must greater than 0!");
        }

        threshold = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);

            while (!p.percolates()) {
                int rRow = StdRandom.uniform(N);
                int rCol = StdRandom.uniform(N);
                p.open(rRow, rCol);
            }

            threshold[i] = (double) p.numberOfOpenSites() / (N * N);
        }

    }

    /** sample mean of percolation threshold */
    public double mean() {
        return StdStats.mean(threshold);
    }

    /** sample standard deviation of percolation threshold */
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    /** low endpoint of 95% confidence interval */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(threshold.length);
    }

    /** high endpoint of 95% confidence interval */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(threshold.length);
    }

}
