//The class Puzzle was modified from the class Maze from CSC 115 Assignment 5 at UVIC
public class Puzzle {
    
    private char[][] puzzleData;
    private PuzzleLocation start;
    private PuzzleLocation finish;

    public Puzzle(char[][] textPuzzle, PuzzleLocation startLocation, PuzzleLocation finishLocation) {
        puzzleData = textPuzzle;
        start = startLocation;
        finish = finishLocation;
    }

    public PuzzleLocation getStart() {
        return start;
    }

    public PuzzleLocation getFinish() {
        return finish;
    }

    public char getChar(int row, int col) {
            return puzzleData[row][col];
    }

    public void setChar(int row, int col, char val) {
        puzzleData[row][col] = val;
    }

    public int getSize() {
        if (puzzleData.length < 1) {
            return 0;
        } else {
            return puzzleData.length * puzzleData[0].length;
        }
    }

    public int getRows() {
        return puzzleData.length;
    }

    public int getCols() {
        if (puzzleData.length < 1) {
            return 0;
        } else {
            return puzzleData[0].length;
        }
    }

    public String toString() {
		String details = " ";
		for (int i = 0; i < puzzleData[0].length; i++) {
			details += i%10;	
		}
		details += "\n";
		for (int i = 0; i < puzzleData.length; i++) {
			details += i%10;
			for (int j = 0; j < puzzleData[i].length; j++) {
					details += puzzleData[i][j];	
			}
			details +="\n";
		}
		return details;
	}
}
