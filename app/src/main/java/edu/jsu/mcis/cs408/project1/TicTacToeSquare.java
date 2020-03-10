package edu.jsu.mcis.cs408.project1;

public class TicTacToeSquare {

    // This class encapsulates the row and column of a single square in the grid, so that they can
    // be passed around as a single (immutable) object.

    private final int row;
    private final int col;

    public TicTacToeSquare(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}