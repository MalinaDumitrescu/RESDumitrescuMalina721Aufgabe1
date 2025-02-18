import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private XMLLogParser parser;
    private List<Log> logEntries;

    private final Controller controller;
    private final Scanner scanner = new Scanner(System.in);

    //todo change file format this.parser = new XMLLogParser(); ////// this.logEntries = parser.parse("src/logs/avengers.txt"); // Parse logs //    private XMLLogParser parser;
    public ConsoleView() throws IOException {
        this.parser = new XMLLogParser(); // Initialize parser
        this.logEntries = parser.parse("src/logs/evenimente.xml"); // Parse logs
        this.controller = new Controller(logEntries); // Initialize controller with logs
    }

    public void menu() {
        while (true) {
            System.out.println();
            System.out.println("Press:");
            //todo add prints for each option
            System.out.println("4. Exit");
            System.out.print("Please enter your choice: ");
            String input = scanner.nextLine();

            switch (input) {

                //todo add case for each option
                case "4":
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

    }
}