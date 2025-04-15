package edu.guilford;

/**
 * WordFrequency class to store a word and its frequency.
 * It implements Comparable interface to allow sorting by frequency and then by word.
 */
public class WordFrequency implements Comparable<WordFrequency> {
    private String word;
    private int frequency;

    /**
     * Constructor to initialize the word and its frequency.
     * @param word the word to be stored
     * @param frequency the frequency of the word
     */
    public WordFrequency(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    /**
     * Getter for the word.
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * Getter for the frequency.
     * @return the frequency of the word
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * compareTo method to compare two WordFrequency objects.
     * It sorts by frequency in descending order and then by word in ascending order.
     * @param other the other WordFrequency object to compare to
     * @return 1 if this object is less than the other, -1 if greater, and 0 if equal
     */
    @Override
    public int compareTo(WordFrequency other) {
        if (frequency < other.frequency) {
            return 1; // sort by descending frequency
        } else if (frequency > other.frequency) {
            return -1;
        } else {
            return word.compareTo(other.word);
        }
    }

    /**
     * toString method to return the string representation of the WordFrequency object.
     * @return the string representation of the word and its frequency
     */
    @Override
    public String toString() {
        return word + " " + frequency;
    }
}
