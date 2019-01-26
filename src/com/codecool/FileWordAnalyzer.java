package com.codecool;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileWordAnalyzer {

    private FilePartReader filePartReader;

    public FileWordAnalyzer(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
    }

    private HashSet<String> getWordsInFile() throws IOException {
        return new HashSet<>(Arrays.asList(filePartReader.readLines().toLowerCase().split("\\s+")));
    }

    public List<String> getWordsOrderedAlphabetically() throws IOException {
        return getWordsInFile().stream().
                sorted().
                collect(Collectors.toList());

    }

    public List<String> getWordsContainingSubstring(String subString) throws IOException {
        return getWordsInFile().stream()
                .filter(word -> word.contains(subString))
                .collect(Collectors.toList());
    }

    public List getStringsWhichArePalindromes() throws IOException {
        return getWordsInFile().stream()
                .filter(this::isPalindrome)
                .collect(Collectors.toList());
    }

    private boolean isPalindrome(String word) {
        return word.length() == 1 || getWordBackwards(word).equals(word);
    }

    private String getWordBackwards(String word) {
        StringBuilder wordBackwards = new StringBuilder();
        IntStream.iterate(word.length() - 1, i -> i - 1)
                .limit(word.length())
                .forEach(i -> wordBackwards.append(word.charAt(i)));
        return wordBackwards.toString();
    }
}
