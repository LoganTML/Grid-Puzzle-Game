//The class PuzzleLocation was modified from the class MazeLocation from CSC 115 Assignment 5 at UVIC
public class PuzzleLocation {
    private int row;
    private int col;

    public PuzzleLocation(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String toString() {
        return "(" + this.row + ", " + this.col + ")";
    }

    public boolean equals(PuzzleLocation loc) {
        return (this.row == loc.row && this.col == loc.col);
    }
}