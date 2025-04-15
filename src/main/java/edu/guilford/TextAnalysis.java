package edu.guilford;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * TextAnalysis class to read a text file, store all the words, and write the results to new files.
 * It uses a priority queue to store words in alphabetical order and their frequencies.
 * @author Ray Wang
 * @version 1.0
 */

public class TextAnalysis {
    public static void main(String[] args) {

        PriorityQueue<String> alphabeticalPQ = new PriorityQueue<>();
        PriorityQueue<WordFrequency> frequencyPQ = new PriorityQueue<>();
        Scanner scanFile = null;

        try {
            FileReader dataFile = new FileReader("target/classes/frankenstein.txt");
            BufferedReader dataBuffer = new BufferedReader(dataFile);
            scanFile = new Scanner(dataBuffer);

            readFile(scanFile, alphabeticalPQ, frequencyPQ);
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.flush();
            System.exit(1);
        }
        scanFile.close();

        // write the words in a new file
        try {
            FileWriter fileLocation = new FileWriter("alphabetical.txt");
            BufferedWriter writer = new BufferedWriter(fileLocation);
            while (!alphabeticalPQ.isEmpty()) {
                writer.write(alphabeticalPQ.poll());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.flush();
            System.exit(1);
        }
        
        // write the word frequencies in a new file
        try {
            FileWriter fileLocation = new FileWriter("wordFrequencies.txt");
            BufferedWriter writer = new BufferedWriter(fileLocation);
            while (!frequencyPQ.isEmpty()) {
                writer.write(frequencyPQ.poll().toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.flush();
            System.exit(1);
        }


    }

    public static void readFile(Scanner scanFile, PriorityQueue<String> alphabeticalPQ, PriorityQueue<WordFrequency> frequencyPQ) {
        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        
        while (scanFile.hasNext()) {
            String word = scanFile.next();
            // remove symbols and punctuation
            word = word.replaceAll("[^a-zA-Z0-9]", "");
            // contains letters and is not pure number
            if (!word.isEmpty() && word.matches(".*[a-zA-Z].*") && !word.matches("^[0-9]+$")) {
                word = word.toLowerCase();

                alphabeticalPQ.add(word.toLowerCase());

                wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            frequencyPQ.add(new WordFrequency(entry.getKey(), entry.getValue()));
        }
    }
}