import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {

    private final List<Log> logEntries;

    public Controller(List<Log> logEntries) {
        this.logEntries = logEntries;
    }

    /**
     * Returns a list of unique ninja names with power points above a given threshold.
     */
    public List<String> getNinjasAbovePowerThreshold(double minPower) {
        return logEntries.stream()
                .filter(log -> log.getKraftpunkte() > minPower)
                .map(Log::getCharaktername)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of all events for rank "Jonin" sorted in descending order by date.
     */
    public List<String> getJoninEventsSortedByDate() {
        return logEntries.stream()
                .filter(log -> log.getStufe() == Stufe.JONIN)
                .sorted((log1, log2) -> log2.getDatum().compareTo(log1.getDatum()))
                .map(log -> log.getDatum() + ": " + log.getCharaktername() + " - " + log.getBeschreibung())
                .collect(Collectors.toList());
    }

    /**
     * Writes the total number of events per ninja rank (Stufe) to "gesammtzahl.txt".
     * Format: Stufe%AnzahlEreignisse#Gesamtpunkte
     */
    public void writeStufeStatsToFile(String filePath) throws IOException {
        Map<Stufe, Long> eventCounts = logEntries.stream()
                .collect(Collectors.groupingBy(Log::getStufe, Collectors.counting()));

        Map<Stufe, Double> totalPowerPoints = logEntries.stream()
                .collect(Collectors.groupingBy(Log::getStufe, Collectors.summingDouble(Log::getKraftpunkte)));

        // Create a list for sorting
        List<String> sortedResults = eventCounts.entrySet().stream()
                .map(entry -> {
                    Stufe stufe = entry.getKey();
                    long count = entry.getValue();
                    double totalPower = totalPowerPoints.getOrDefault(stufe, 0.0);
                    return stufe + "%" + count + "#" + String.format("%.2f", totalPower).replace(",", "\".");
                })
                //todo modificat aici in loc de int, parsez long
                .sorted((a, b) -> {
                    String[] partsA = a.split("[%#]");
                    String[] partsB = b.split("[%#]");
//counting() returns a long after that we convert to
                    long countA = Long.parseLong(partsA[1]);
                    long countB = Long.parseLong(partsB[1]);
                    double powerA = Double.parseDouble(partsA[2].replace("\".", "."));
                    double powerB = Double.parseDouble(partsB[2].replace("\".", "."));

                    if (countA == countB) {
                        return Double.compare(powerA, powerB);
                    }
                    return Long.compare(countB, countA);
                })
                .collect(Collectors.toList());

        // Write to file
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String line : sortedResults) {
                writer.write(line + "\n");
            }
        }
    }
}