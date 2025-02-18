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
        this.logEntries = parser.parse("src/evenimente.xml"); // Parse logs
        this.controller = new Controller(logEntries); // Initialize controller with logs
    }

    public void menu() {
        while (true) {
            System.out.println();
            System.out.println("Press:");
            System.out.println("1. Show all ninjas with power points above a given threshold");
            System.out.println("2. Show all Jonin events sorted by date");
            System.out.println("3. Write total number of events per ninja rank to file");
            System.out.println("4. Exit");
            System.out.print("Please enter your choice: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    displayNinjasAboveThreshold();
                    break;
                case "2":
                    displayJoninEvents();
                    break;
                case "3":
                    writeStufeStatsToFile();
                    break;

                //todo add case for each option
                case "4":
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

    }

    private void displayNinjasAboveThreshold() {
        System.out.print("Enter minimum power points: ");
        double minPower = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        List<String> ninjas = controller.getNinjasAbovePowerThreshold(minPower);
        if (ninjas.isEmpty()) {
            System.out.println("No ninjas found above the power threshold.");
        } else {
            System.out.println("Ninjas with power points above " + minPower + ":");
            ninjas.forEach(System.out::println);
        }
    }

    private void displayJoninEvents() {
        List<String> events = controller.getJoninEventsSortedByDate();
        if (events.isEmpty()) {
            System.out.println("No events found for rank Jonin.");
        } else {
            System.out.println("Jonin events sorted by date:");
            events.forEach(System.out::println);
        }
    }

    private void writeStufeStatsToFile() {
        try {
            controller.writeStufeStatsToFile("gesammtzahl.txt");
            System.out.println("Statistics written to gesammtzahl.txt successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

}