package com.codecool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class FilePartReader {

    private String filePath;
    private Integer fromLine;
    private Integer toLine;

    public FilePartReader() {
        filePath = "/test.txt";
        fromLine = 1;
        toLine = 1;
    }

    public void setup(String filePath, Integer fromLine, Integer toLine) throws IllegalArgumentException {
        if (toLine < fromLine || fromLine < 1) {
            throw new IllegalArgumentException("To line can't be smaller than from line");
        }
        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;
    }

    public String read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder fileContent = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            fileContent.append(line + "/n");
        }
        return fileContent.toString();
    }

    public String readLines() throws IOException {
        List<String> linesInFile = new ArrayList<>(Arrays.asList(read().split("\\n+")));
        StringBuilder requiredLines = new StringBuilder();
        IntStream.range(fromLine, toLine)
                .mapToObj(linesInFile::get)
                .forEach(line -> requiredLines.append(line).append(" "));
        return requiredLines.toString();
    }
}
