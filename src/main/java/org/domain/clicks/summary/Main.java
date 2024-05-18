package org.domain.clicks.summary;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.domain.clicks.summary.components.CSVReader;
import org.domain.clicks.summary.components.DomainCounter;
import org.domain.clicks.summary.model.input.FilePathInputDTO;

public class Main {
    public static void main(String... args) {
        try {
            var fileInput = getInputFromArgs(args);
            var csvReader = CSVReader.getInstance();
            System.out.println(DomainCounter.getInstance().count(csvReader.read(fileInput.getPathStr())));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static FilePathInputDTO getInputFromArgs(String... args) {
        try {
            var filePathInput = new FilePathInputDTO();
            JCommander.newBuilder()
                    .addObject(filePathInput)
                    .build()
                    .parse(args);

            return filePathInput;
        } catch (ParameterException e) {
            throw new RuntimeException("Invalid parameter: %s%n".formatted(e.getMessage()), e);
        }
    }
}