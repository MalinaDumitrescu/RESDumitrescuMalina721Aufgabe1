import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser for XML files.
 * This class implements the LogParser interface to parse log entries from an XML file.
 */
public class XMLLogParser {

    /**
     * Parses an XML file and returns a list of log entries.
     *
     * @param filePath the path to the XML file to be parsed
     * @return a list of Log objects parsed from the XML file
     * @throws IOException if an I/O error occurs reading from the file
     */
    public List<Log> parse(String filePath) throws IOException {
        List<Log> logEntries = new ArrayList<>();
        Path file = Path.of(filePath);

        StringBuilder xml = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                xml.append(line.trim());
            }
        }

        String content = xml.toString();
        String[] logs = content.split("</log>");
        for (String log : logs) {
            if (!log.contains("<log>")) continue;
            log = log.substring(log.indexOf("<log>") + 5).trim();

            int id = Integer.parseInt(extractTagValue(log, "Id"));
            String charaktername = extractTagValue(log, "Charaktername");
            Stufe stufe = Stufe.valueOf(extractTagValue(log, "Stufe").toUpperCase());
            String beschreibung = extractTagValue(log, "Beschreibung");
            LocalDate datum = LocalDate.parse(extractTagValue(log, "Datum"));
            double kraftpunkte = Double.parseDouble(extractTagValue(log, "Kraftpunkte"));

            logEntries.add(new Log(id, charaktername, stufe, beschreibung, datum, kraftpunkte));
        }
        return logEntries;
    }

    /**
     * Extracts the value of a specified XML tag from a log entry.
     *
     * @param log the log entry as a string
     * @param tagName the name of the XML tag to extract the value from
     * @return the value of the specified XML tag
     */
    private String extractTagValue(String log, String tagName) {
        String startTag = "<" + tagName + ">";
        String endTag = "</" + tagName + ">";
        int startIndex = log.indexOf(startTag) + startTag.length();
        int endIndex = log.indexOf(endTag);
        return log.substring(startIndex, endIndex).trim();
    }
}
