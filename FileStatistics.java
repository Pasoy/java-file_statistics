import java.io.File;

import data.FileData;

public class FileStatistics {
	
	/*
	 * Number of characters (DONE)
	 * Number of lines (DONE)
	 * Frequencies of all letters (DONE)
	 * Mean length of lines (STILL TO DO)
	 * Number of words (DONE)
	 * Mean length of words (DONE)
	 * Longest and shortest line (DONE)
	 * Longest and shortest word (DONE)
	 * At the end it should output the text of the file with line numbers (DONE)
	 * The filename is passed to the program as a command line parameter (DONE)
	 */

	public static String desktopPath = System.getProperty("user.home") + "/Desktop/";
	public static File file = new File(desktopPath + "file.txt");
	public static FileData fd;
	
	public static void main(String[] args) {
		for(String s : args) {
			fd = new FileData(new File(s));
		}
	}

}
