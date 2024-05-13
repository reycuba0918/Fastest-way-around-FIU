package Utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class AddingTheReversePath {
    public static void main(String[] args) {
        File pathsFile = new File("Data\\Paths.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(pathsFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        HashSet<String> paths  = new HashSet<>();
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            if (!paths.contains(currentLine))
                paths.add(currentLine);
            else
                System.out.println("Not reversed - " + currentLine);
            String[] currentLineSplit = currentLine.split(", ");
            String reverseCurrentLine = currentLineSplit[1] + ", " + currentLineSplit[0];
            if(!paths.contains(reverseCurrentLine))
                paths.add(reverseCurrentLine);
            else
                System.out.println("reversed - " + reverseCurrentLine);
        }
        scanner.close();

        try {
            FileWriter fileWriter = new FileWriter("Data\\Paths_with_Reverse.txt", true);
            for(String path : paths){
                fileWriter.append(path);
                fileWriter.append("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("File could not be made.");
            e.printStackTrace();
        }
        
    }
}
