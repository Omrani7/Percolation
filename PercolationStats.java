

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    double[] tab;
    private Percolation p;
    int total;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        total = n * n;
        int row;
        int col;
        tab = new double[n];
        p = new Percolation(n);
        int num = 0;
        for (int i = 0; i < trials; i++) {
            row = StdRandom.uniform(n);
            col = StdRandom.uniform(n);
            if (!p.percolates()) {
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    num++;
                }
            }
            tab[i] = (double) num / (double) n * n;

        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return (double) p.numberOfOpenSites() / (double) total * total;

    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(tab);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(tab.length));

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {

        return mean() + (1.96 * stddev() / Math.sqrt(tab.length));
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]),
                Integer.parseInt(args[1]));

        System.out.format("mean                    = %.16f %n", ps.mean());
        System.out.format("sttdev                  = %.16f %n", ps.stddev());
        System.out.format("95%% confidence interval = [%.16f, %.16f] %n", ps.confidenceLo(),
                ps.confidenceHi());


    }

}
