package com.datagile.test.util;

import com.datagile.test.weather.deserialized.ResponseRepresentation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ParserToFile {
    private final Path outputFile = Path.of("files/output.txt");


    public ParserToFile() {}


    public void writeToFile(ResponseRepresentation response) {
        if (!Files.exists(outputFile)) {
            try {
                Files.createFile(outputFile);
            } catch (IOException e) {
                e.printStackTrace();
                throw new IllegalStateException("Directory must be made at the root of the project first");
            }
        }
        if (Files.isWritable(outputFile)) {
            try {
                Files.writeString(outputFile, response.toString());
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Couldn't write to file. Check locks");
            }
        }
    }
}
