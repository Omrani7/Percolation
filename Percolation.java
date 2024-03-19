/* *****************************************************************************
 *  Name:             omrani
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int numOpenSites;
    private final WeightedQuickUnionUF uf;
    private boolean[][] grid;
    private int size;

    public Percolation(int n) {
        grid = new boolean[n][n];
        size = n;
        uf = new WeightedQuickUnionUF(n * n + 2);


    }

    public void open(int row, int col) {
        if (row < 0 || row >= size - 1 || col < 0 || col >= size - 1) {
            throw new IllegalArgumentException("please check site indices");
        }
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            numOpenSites++;

        }
        if (row >= 0 && isOpen(row + 1, col)) {
            uf.union(getIndex(row, col), getIndex(row + 1, col));

        }
        if (row <= size - 1 && isOpen(row - 1, col)) {
            uf.union(getIndex(row, col), getIndex(row - 1, col));

        }
        if (col >= 0 && isOpen(row, col + 1)) {
            uf.union(getIndex(row, col), getIndex(row, col + 1));

        }
        if (col < size - 1 && isOpen(row, col - 1)) {
            uf.union(getIndex(row, col), getIndex(row, col - 1));

        }
    }

    public boolean isOpen(int row, int col) {
        return grid[row][col];
    }

    public int getIndex(int row, int col) {
        return size * row + col + 1;

    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IllegalArgumentException("wrong indices");
        }
        return isOpen(row, col) && uf.connected(getIndex(row, col), 0);
    }

    public int numberOfOpenSites() {
        return numOpenSites;
    }

    public boolean percolates() {
        return uf.connected(0, size * size + 1);
    }
}
