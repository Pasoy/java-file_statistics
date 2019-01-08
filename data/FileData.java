package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class FileData {

	public static final String NEWLINE = System.getProperty("line.separator");
	private File file;

	/**
	 * Constructor for class: FileData.java
	 */
	public FileData(File file) {
		setFile(file);
	}

	/**
	 * Returns the number of characters in the text file
	 * @return
	 */
	public int getNumberCharacters() {
		int characters = 0;
		for (String s : getLines()) {
			characters += s.length();
		}
		return characters;
	}

	/**
	 * Returns a HashMap with the frequences of characters in it
	 * @return
	 */
	public HashMap<Character, Integer> getFrequencies() {
		HashMap<Character, Integer> map = new HashMap<>();

		for (String s : getLines()) {
			s = s.toLowerCase();
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				Integer val = map.get(c);
				if (val != null) {
					map.put(c, new Integer(val + 1));
				} else {
					map.put(c, 1);
				}
			}
		}

		return map;
	}

	/**
	 * Returns the average length of lines
	 * @return
	 */
	public int getMeanLengthLines() {
		int number = 0;
		number = getNumberCharacters() / getLines().size();
		return number;
	}

	/**
	 * Returns the number of words in the file
	 * @return
	 */
	public int getNumberWords() {
		int number = 0;
		for (String s : getLines()) {
			number += s.trim().split("\\s+").length;
		}
		return number;
	}

	/**
	 * Returns the average length of words
	 * @return
	 */
	public int getMeanLengthWords() {
		int number = 0;
		number = getNumberCharacters() / getNumberWords();
		return number;
	}

	/**
	 * Returns the number of words of a line
	 * @param line
	 * @return
	 */
	public int getNumberWords(int line) {
		int number = 0;
		if (line > getLines().size() || line < 0) {
			throw new IllegalArgumentException("The line does not exist");
		}
		for (int i = 0; i < getLines().size(); i++) {
			String s = getLines().get(i);
			if (i == line) {
				number = s.trim().split("\\s+").length;
			}
		}
		return number;
	}

	/**
	 * Finds the longest and shortest line and returns them in an ArrayList
	 * @return
	 */
	public ArrayList<String> getLongestShortestLine() {
		ArrayList<String> line = new ArrayList<>();
		ArrayList<Integer> numbers = getLinesCharacters();
		int longest = Integer.MIN_VALUE, smallest = Integer.MAX_VALUE;
		
		longest = getLargestNumber(numbers);
		smallest = getSmallestNumber(numbers);
		
		line.add(getLineByNumberCharacters(longest));
		line.add(getLineByNumberCharacters(smallest));

		return line;
	}

	/**
	 * Finds the longest and shortest word and returns them in an ArrayList
	 * @return
	 */
	public ArrayList<String> getLongestShortestWord() {
		ArrayList<String> line = new ArrayList<>();
		String longest = "", smallest = "";
		for(String s : getLines()) {
			longest = Arrays.stream(s.split(" ")).max(Comparator.comparingInt(String::length)).orElse("No Strings");
			smallest = Arrays.stream(s.split(" ")).min(Comparator.comparingInt(String::length)).orElse("No Strings");
		}
		line.add(longest);
		line.add(smallest);
		return line;
	}

	/*
	 * ****************** * OWN METHODS * ******************
	 */

	/**
	 * Reads the file and saves all the lines from the text file into an ArrayList
	 * @return
	 */
	public ArrayList<String> getLines() {
		ArrayList<String> lines = new ArrayList<>();

		try {

			BufferedReader reader = new BufferedReader(new FileReader(getFile()));

			String line = reader.readLine();
			while (line != null) {
				lines.add(line);
				line = reader.readLine();
			}
			reader.close();

		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("The file was not found!");
		} catch (IOException e) {
			throw new IllegalArgumentException("An error occured while reading the file!");
		}

		return lines;
	}
	
	/**
	 * Returns the line by giving the number of the line as parameter
	 * @param line
	 * @return
	 */
	public String getLine(int line) {
		String result = "";
		if (line > getLines().size() || line < 0) {
			throw new IllegalArgumentException("The line does not exist");
		}
		for (int i = 0; i < getLines().size(); i++) {
			String s = getLines().get(i);
			if (i == line) {
				result = s;
			}
		}
		return result;
	}

	/**
	 * Returns the line by giving the number of characters as parameter
	 * @param number
	 * @return
	 */
	public String getLineByNumberCharacters(int number) {
		String result = "";
		int length = 0;
		for (int i = 0; i < getLines().size(); i++) {
			String s = getLines().get(i);
			length = s.length();
			if (number == length) {
				result = s;
			}
		}
		return result;
	}
	
	/**
	 * Returns an ArrayList with the numbers of every line in it
	 * @return
	 */
	public ArrayList<Integer> getLinesCharacters() {
		ArrayList<Integer> numbers = new ArrayList<>();

		for(String s : getLines()) {
			numbers.add(s.length());
		}
		
		return numbers;
	}
	
	/**
	 * Returns the largest number in an Array
	 * @param numbers
	 * @return
	 */
	public int getLargestNumber(int... numbers) {
		int largest = Integer.MIN_VALUE;
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] > largest) {
				largest = numbers[i];
			}
		}
		return largest;
	}

	/**
	 * Returns the largest number of an ArrayList
	 * @param list
	 * @return
	 */
	public int getLargestNumber(ArrayList<Integer> list) {
		return Collections.max(list);
	}

	/**
	 * Returns the smallest number in an Array
	 * @param numbers
	 * @return
	 */
	public int getSmallestNumber(int... numbers) {
		int smallest = Integer.MAX_VALUE;
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] < smallest) {
				smallest = numbers[i];
			}
		}
		return smallest;
	}

	/**
	 * Returns the smallest number of an ArrayList
	 * @param list
	 * @return
	 */
	public int getSmallestNumber(ArrayList<Integer> list) {
		return Collections.min(list);
	}

	/**
	 * Basically prints out the file in the console
	 * 
	 * @param file
	 */
	public void print() {
		for (String s : getLines()) {
			System.out.println(s);
		}
	}
	
	/**
	 * Returns the file as a String
	 * @return
	 */
	public String getFileAsString() {
		String result = "";
		for(String s : getLines()) {
			result += s + NEWLINE;
		}
		return result;
	}

	/**
	 * Prints the frequency of the in the file contained characters in a row
	 */
	public void printFrequencies() {
		HashMap<Character, Integer> map = getFrequencies();
		map.forEach((c, i) -> System.out.println(c + ": " + i));
	}

	/**
	 * Overriden toString method
	 */
	@Override
	public String toString() {
		return "------------ SCHMIEDJELL FILE STATISTICS ------------"
				+ NEWLINE + "Number of characters: " + getNumberCharacters()
				+ NEWLINE + "Number of lines: " + getLines().size()
				+ NEWLINE + "Frequencies of all letters: " + getFrequencies()
				+ NEWLINE + "Mean length of lines: " + getMeanLengthLines()
				+ NEWLINE + "Number of words: " + getNumberWords()
				+ NEWLINE + "Mean length of words: " + getMeanLengthWords()
				+ NEWLINE + "Longest and shortest line: " + getLongestShortestLine()
				+ NEWLINE + "Longest and shortest word: " + getLongestShortestWord()
				+ NEWLINE
				+ getFileAsString()
				+ NEWLINE + "------------ SCHMIEDJELL FILE STATISTICS ------------" + NEWLINE;
	}
	
	/*
	 * ****************** * SETTER - GETTER * ******************
	 */

	/**
	 * Returns the File file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Set the File file to the given value
	 */
	public void setFile(File file) {
		if (file == null) {
			throw new IllegalArgumentException("File does not exist!");
		}
		this.file = file;
	}

}
