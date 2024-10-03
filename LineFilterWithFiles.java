/*  FIRST EXERCISEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileSystemManager {
    private File currentDirectory;

    public FileSystemManager() {
        // Start in the user's current directory
        currentDirectory = new File(System.getProperty("user.dir"));
    }

    // Method to handle the "ls" command
    public void ls(boolean detailed) {
        File[] files = currentDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (detailed) {
                    String type = file.isDirectory() ? "d" : "f";
                    long size = file.length();
                    System.out.printf("%s %10d %s\n", type, size, file.getName());
                } else {
                    System.out.println(file.getName());
                }
            }
        } else {
            System.out.println("Error: Could not list files.");
        }
    }

    // Method to handle the "cd" command
    public void cd(String dir) {
        if (dir.equals("..")) {
            currentDirectory = currentDirectory.getParentFile();
        } else {
            File newDir = new File(currentDirectory, dir);
            if (newDir.exists() && newDir.isDirectory()) {
                currentDirectory = newDir;
            } else {
                System.out.println("Error: Directory does not exist.");
            }
        }
    }

    // Method to handle the "mkdir" command
    public void mkdir(String dir) {
        File newDir = new File(currentDirectory, dir);
        if (newDir.exists()) {
            System.out.println("Error: Directory already exists.");
        } else if (newDir.mkdir()) {
            System.out.println("Directory created: " + dir);
        } else {
            System.out.println("Error: Could not create directory.");
        }
    }

    // Method to handle the "pwd" command
    public void pwd() {
        try {
            System.out.println(currentDirectory.getCanonicalPath());
        } catch (IOException e) {
            System.out.println("Error: Could not get the current directory path.");
        }
    }

    // Method to process commands
    public void processCommand(String[] command) {
        switch (command[0]) {
            case "ls":
                boolean detailed = command.length > 1 && command[1].equals("-l");
                ls(detailed);
                break;
            case "cd":
                if (command.length < 2) {
                    System.out.println("Error: Missing directory argument.");
                } else {
                    cd(command[1]);
                }
                break;
            case "mkdir":
                if (command.length < 2) {
                    System.out.println("Error: Missing directory name.");
                } else {
                    mkdir(command[1]);
                }
                break;
            case "pwd":
                pwd();
                break;
            default:
                System.out.println("Error: Unknown command.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileSystemManager fsm = new FileSystemManager();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            String[] command = input.split("\\s+");

            if (command[0].equals("exit")) {
                System.out.println("Exiting...");
                break;
            }

            fsm.processCommand(command);
        }

        scanner.close();
    }
}

*/

/* SECOND EXERCISEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
import java.util.Scanner;

public class LineFilter {
    public static void main(String[] args) {
        // Check if the pattern is provided as a command-line argument
        if (args.length < 1) {
            System.out.println("Error: No pattern provided.");
            System.out.println("Usage: java LineFilter <pattern>");
            return;
        }

        // The pattern to match
        String pattern = args[0];

        // Create a scanner to read lines from standard input
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter lines (Ctrl+D to stop):");

        // Read lines from input and filter them
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            // Check if the line matches the pattern
            if (line.matches(pattern)) {
                // Print the matching line
                System.out.println("Found coincidence!!!!!!");
            }
        }

        // Close the scanner
        scanner.close();
    }
}
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LineFilterWithFiles {
    public static void main(String[] args) {
        String input = null;
        String output = null;
        String pattern = "";

        // Process command-line arguments
        for (int i = 0; i < args.length; i++) {
            if ((i + 1 < args.length) && args[i].equals("-i")) {
                i++;
                input = args[i];
            } else if ((i + 1 < args.length) && args[i].equals("-o")) {
                i++;
                output = args[i];
            } else if ((i + 1 < args.length) && args[i].equals("-p")) {
                i++;
                pattern = args[i];
            }
        }

        // Check if the pattern was provided
        if (pattern.isEmpty()) {
            System.out.println("Error: No pattern provided.");
            System.out.println("Usage: java LineFilterWithFiles -p <pattern> [-i <inputfile>] [-o <outputfile>]");
            return;
        }

        // Set up input source (file or standard input)
        Scanner scanner = null;
        if (input != null) {
            try {
                scanner = new Scanner(new File(input));
            } catch (FileNotFoundException e) {
                System.out.println("Error: Input file not found.");
                return;
            }
        } else {
            scanner = new Scanner(System.in);
            System.out.println("Enter lines (Ctrl+D to stop):");
        }

        // Set up output destination (file or standard output)
        PrintWriter writer = null;
        if (output != null) {
            try {
                writer = new PrintWriter(new FileWriter(output));
            } catch (IOException e) {
                System.out.println("Error: Could not open output file for writing.");
                return;
            }
        } else {
            writer = new PrintWriter(System.out); // Write to standard output
        }

        // Process the input and filter lines based on the pattern
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            // If the line matches the pattern, write it to the output
            if (line.matches(pattern)) {
                writer.println(line);
            }
        }

        // Close the scanner and writer
        if (scanner != null) {
            scanner.close();
        }
        if (writer != null) {
            writer.close();
        }
    }
}
