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


    //todo stop commit d
}
