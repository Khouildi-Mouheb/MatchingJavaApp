

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RecuperateurCSV implements Recuperateur {
    private String filePath;

    public RecuperateurCSV(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Nom> importData() {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.skip(1) // Skip the header line
                    .map(line -> line.split(",", -1)) // Handle trailing commas
                    .filter(values -> values.length >= 2 && values[0] != null && values[1] != null)
                    .map(values -> new Nom(values[0].trim(), values[1].trim())) // Assuming Nom is the correct class
                    .collect(Collectors.toList()); // Mutable list
        } catch (IOException e) {
            // Log the error properly (e.g., using a logging framework)
            System.err.println("Failed to import data from " + filePath + ": " + e.getMessage());
            return Collections.emptyList(); // Or throw a custom exception
        }
    }

}