package org.domain.clicks.summary.components;

import org.domain.clicks.summary.exception.CSVReaderException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CSVReader {
    private static final String CSV_COLUMN_SEPARATOR = ",";

    private static CSVReader instance;

    private CSVReader() {
    }

    public List<List<String>> read(String pathStr) {
        try (var lineStream = Files.lines(getPathFromString(pathStr))) {
            return lineStream
                    .filter(l -> !l.isBlank())
                    .map(l -> Arrays.stream(l.split(CSV_COLUMN_SEPARATOR)).toList())
                    .toList();
        } catch (Exception e) {
            throw new CSVReaderException("Error reading .csv file");
        }
    }

    private Path getPathFromString(String pathStr) {
        var filePath = Paths.get(pathStr);
        if (!Files.exists(filePath)) {
            throw new CSVReaderException("File not found: " + filePath);
        }

        return filePath;
    }

    public static CSVReader getInstance() {
        if (Objects.isNull(instance)) {
            instance = new CSVReader();
        }
        return instance;
    }
}
