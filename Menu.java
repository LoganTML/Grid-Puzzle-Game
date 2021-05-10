import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;

public class Menu {


    public static void main(String[] args) {

        FileWriter fw;
        String dirLocation = "C:/Users/Logan/Documents/GitHub/Grid-Puzzle-Game/out/production/Grid-Puzzle-Game/puzzles";

        try {
            List<File> fileList = Files.list(Paths.get(dirLocation))
                    .map(Path::toFile)
                    .collect(Collectors.toList());

            int levelNumber = 0;
            int i = 0;
            int levelScore = 0;



            try {
                //Creates an array with as many lines as there are puzzle files.  Sets the score for each puzzle to zero.
                Path path = Paths.get("Scores.txt");
                List<String> lines = new LinkedList<String>();
                for (int j = 0; j < fileList.size(); j++) {
                    lines.add("0");
                }
                Files.write(path, lines, StandardCharsets.UTF_8);
            } catch (IOException e) {
                System.out.println("Error setting up scores document");
                return;
            }


            do {

                //Prints out the selection menu
                for (i = 0; i < fileList.size(); i++) {
                    System.out.println(i + 1 + ") " + fileList.get(i).getName());
                }
                System.out.println(i+1 + ") Help");
                System.out.println(i + 2 + ") Settings");
                System.out.println(i + 3 + ") Exit");
                System.out.println("Score: " + totalComplete() + "/" + i);

                Scanner sc = new Scanner(System.in);
                System.out.print("Choose puzzle: ");

                levelNumber = sc.nextInt()-1;

                if (levelNumber == i) {
                    //Explain the instructions
                    System.out.println("Help: ");
                } else if (levelNumber == i + 1) {
                    //Display settings
                    System.out.println("Settings: ");
                } else if (levelNumber == i + 2) {
                    //Exit program
                    System.out.println("Exiting");
                    return;
                } else if (levelNumber < i) {
                    //Load and run the level
                    levelScore = Level.doLevel("puzzles/"+fileList.get(levelNumber).getName());
                    if (levelScore > isComplete(levelNumber)) {
                        updateScore(levelNumber, levelScore);
                    }
                } else {
                    System.out.println("Number unrecognized, please try again");
                }

                if (totalComplete() == i) {
                    System.out.println("Game over!");
                    return;
                }

            } while (levelNumber != i+2); //Keeps the loop running

        } catch (IOException e) {
            System.out.println("Error in Menu");
        }


    }

    private static int isComplete(int n) {

        try {
            Path path = Paths.get("Scores.txt");
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            return lines.get(n).charAt(0) - 48;
        } catch (IOException e) {
            System.out.println("Error reading Scores.txt");
        }
        return 0;
    }

    private static int totalComplete() {
        try {
            Path path = Paths.get("Scores.txt");
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            int total = 0;
            for (int i = 0; i < lines.size(); i++) {
                total += lines.get(i).charAt(0) - 48;
            }
            return total;
        } catch (IOException e) {
            System.out.println("Error reading Scores.txt");
        }
        return 0;
    }

    private static int updateScore(int level, int score) {
        try {
            Path path = Paths.get("Scores.txt");
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            lines.set(level, score+"");
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Error reading Scores.txt");
        }
        return 0;
    }

}