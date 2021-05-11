import java.util.Scanner;

public class PuzzleSolver {
    Puzzle puzzleToSolve;
    LinkedQueue<Character> input;
    LinkedQueue<PuzzleLocation> path;
    FilePrinter fileWriter;
    PuzzleLocation curGlobal;
    PuzzleLocation finGlobal;

    public PuzzleSolver(Puzzle aPuzzle) {
        puzzleToSolve = aPuzzle;
        fileWriter = new FilePrinter();
        path = new LinkedQueue<PuzzleLocation>();
        input = new LinkedQueue<Character>();
    }

    public void getInputFromUser() {
        Scanner sc = new Scanner(System.in);  //Creates a scanner
        System.out.print("Enter input (wasd): ");
        String st = "";
        st += sc.nextLine();
        System.out.println("Input: "+st);
        char c;
        for (int i = 0; i < st.length(); i++) {
            c = st.charAt(i);
            System.out.println("Direction " + (i + 1) + ": " + c);
            // switch (c) {
            //     case ('u')
            // }

            input.enqueue(c);
        }
    }

    public int solvePuzzle(PuzzleLocation start, PuzzleLocation finish) {
        fileWriter.println("Going through the puzzle from the start: "+start+" to finish: "+finish);

        return solver(start, finish);
    }

    public int resume() {
        return solver(curGlobal, finGlobal);
    }

    public int solver(PuzzleLocation cur, PuzzleLocation finish) {

        curGlobal = cur;
        finGlobal = finish;

        int row = cur.getRow();
        int col = cur.getCol();

        System.out.println("Current location: "+cur);

        path.enqueue(cur);

        for (int rows = 0; rows < puzzleToSolve.getRows(); rows++) {
            for (int cols = 0; cols < puzzleToSolve.getCols(); cols++) {
                if (puzzleToSolve.getChar(rows, cols) == 'x') {
                    puzzleToSolve.setChar(rows, cols, 'o');
                }
            }
        }

        puzzleToSolve.setChar(row, col, 'x');


        System.out.println("\n"+puzzleToSolve);
        fileWriter.println("\n"+puzzleToSolve);

        if (row == finish.getRow() && col == finish.getCol()) {
            return 1;
        }

        if (input.isEmpty()) {
            System.out.println("Input is empty");
            return 2;
        }

        
            PuzzleLocation newLocation;
        switch (input.peek()) {
            case 'w' -> {
                System.out.println("Top element is up");
                newLocation = new PuzzleLocation(row-1, col);
            }
            case 'a' -> {
                System.out.println("Top element is left");
                newLocation = new PuzzleLocation(row, col-1);
            }
            case 's' -> {
                System.out.println("Top element is down");
                newLocation = new PuzzleLocation(row+1, col);
            }
            case 'd' -> {
                System.out.println("Top element is right");
                newLocation = new PuzzleLocation(row, col+1);
            }
            default -> {
                System.out.println("Direction not found");
                return 2;
            }
        }




//            if (input.peek() == 'u') {
//                System.out.println("Top element is up");
//                newLocation = new PuzzleLocation(row, col-1);
//            } else if (input.peek() == 'd') {
//                System.out.println("Top element is down");
//                newLocation = new PuzzleLocation(row, col+1);
//            } else if (input.peek() == 'l') {
//                System.out.println("Top element is left");
//                newLocation = new PuzzleLocation(row-1, col);
//            } else if (input.peek() == 'r') {
//                System.out.println("Top element is right");
//                newLocation = new PuzzleLocation(row+1, col);
//            } else {
//                System.out.println("Direction not found");
//                return false;
//            }


        //Checks whether the new location is within the bounds of the puzzle
        if (newLocation.getRow() < 0) {
            System.out.println("Upper boundary reached");
            return 0;
        } else if (newLocation.getRow() > puzzleToSolve.getRows()-1) {
            System.out.println("Lower boundary reached");
            return 0;
        } else if (newLocation.getCol() < 0) {
            System.out.println("Left boundary reached");
            return 0;
        } else if (newLocation.getCol() > puzzleToSolve.getCols()-1) {
            System.out.println("Right boundary reached");
            return 0;
        }

        switch (puzzleToSolve.getChar(newLocation.getRow(), newLocation.getCol())) {
            case 'o', 'z' -> {
                return solvePuzzle(newLocation, finish);
            }
            case 'b' -> {
                input.dequeue();
                return solvePuzzle(cur, finish);
            }
        }

//            if (puzzleToSolve.getChar(newLocation.getRow(), newLocation.getCol()) == 'o' || puzzleToSolve.getChar(newLocation.getRow(), newLocation.getCol()) == '' ) {
//                path.enqueue(newLocation);
//                return solvePuzzle(newLocation, finish);
//            } else if (puzzleToSolve.getChar(newLocation.getRow(), newLocation.getCol()) == 'b') {
//                input.dequeue();
//                return solvePuzzle(cur, finish);
//            }
        
        return 2;
    }

    public String getPathToSolution() {
        String details = "";
        while (!path.isEmpty()) {
            details += path.dequeue() + "\n";
        }
        return details;
    }

    public void printResults(int solved) {
        if (solved == 1) {
            System.out.println("\n** Puzzle Solved **");
            System.out.println("Path:");
            System.out.println(getPathToSolution());
            fileWriter.println("\n** Puzzle Solved **");
            fileWriter.println(getPathToSolution());
        } else {
            System.out.println("\n--- No path to solution found ---");
        }
        fileWriter.close();
    }
}
