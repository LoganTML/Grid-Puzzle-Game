import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;

public class Level {

    public static int doLevel(String puzzleFile) {
        if (puzzleFile.length() < 1) {
            System.err.println("You must specify a puzzle file");
            System.err.println("Usage: java Level <puzzlefile>\n");
            return 0;
        }

        System.out.println("Beginning Level: "+puzzleFile.substring(8)); //Edit so it doesn't say puzzles/
        Puzzle puzzle = initialize(puzzleFile);
        PuzzleSolver ps = new PuzzleSolver(puzzle);
        PuzzleLocation st = new PuzzleLocation(puzzle.getStart().getRow(), puzzle.getStart().getCol());
        PuzzleLocation fi = new PuzzleLocation(puzzle.getFinish().getRow(), puzzle.getFinish().getCol());
        puzzle.setChar(st.getRow(), st.getCol(), 'a');
        puzzle.setChar(fi.getRow(), fi.getCol(), 'z');
        System.out.println(puzzle);

        ps.getInputFromUser();
        int solved = ps.solvePuzzle(st, fi);


        while (solved == 2) {
            //Gets player input
            ps.getInputFromUser();

            //Checks if input solves puzzle
            solved = ps.resume();
        }




        if (solved == 1) {
            System.out.println("Solved");
            System.out.println("\n"+puzzle);
            ps.printResults(solved);
            return 1;
        } else {
            System.out.println("Not solved");
            System.out.println("\n"+puzzle);
            ps.printResults(solved);
            return 0;
        }
    }

    //The function below, initialize, was modified from an assignment for CSC 115 at UVIC

    public static Puzzle initialize(String data) {

        
        try {
            Scanner infileScanner = new Scanner(new File(data));

            int rows = infileScanner.nextInt();
            int columns = infileScanner.nextInt();
            int startRow = infileScanner.nextInt();
            int startColumn = infileScanner.nextInt();
            int finishRow = infileScanner.nextInt();
            int finishColumn = infileScanner.nextInt();
            infileScanner.nextLine();

            char[][]puzzleData = new char[rows][columns];

            for (int row = 0; row < rows; row++) {
                String line = infileScanner.nextLine();
                for (int col = 0; col < columns; col++) {
                    puzzleData[row][col] = line.charAt(col);
                }
            }
            PuzzleLocation startLocation = new PuzzleLocation(startRow, startColumn);
            PuzzleLocation finishLocation = new PuzzleLocation(finishRow, finishColumn);
            return new Puzzle(puzzleData, startLocation, finishLocation);

        } catch (FileNotFoundException e) {
            System.out.println("Error scanning file "+data);
            System.exit(2);
        } catch (NoSuchElementException e) {
            System.out.println("Puzzle data file is not formatted correctly");
        }
        return null;
    }

}
