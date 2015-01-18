package codejam2014.qualification.a;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MagicTrick {

	private static final String INPUT_FILENAME = "src/codejam2014/qualification/a/data/A-small-practice.in";

	private static final Integer ROWS = 4;
	private static final Integer COLUMNS = 4;

	private static final Integer LINES_PER_CASE = 10;

	public static void main(String[] args) {

		MagicTrick magicTrick = new MagicTrick(INPUT_FILENAME);

	}

	public MagicTrick(String input) {

		BufferedReader br = readFile(input);

		ArrayList<String> fileContent = parseFile(br);

		int cases = Integer.valueOf(fileContent.get(0));

		for (int i = 0; i < cases; i++) {

			String firstRow = fileContent.get(i * LINES_PER_CASE + 1);

			String firstChoice = fileContent.get(i * LINES_PER_CASE
					+ Integer.valueOf(firstRow) + 1);

			String[] firstChoice_str = firstChoice.split(" ");

			String secondRow = fileContent.get(i * LINES_PER_CASE + 6);

			String secondChoice = fileContent.get(i * LINES_PER_CASE
					+ Integer.valueOf(secondRow) + 6);

			String[] secondChoice_str = secondChoice.split(" ");
			
			Integer number = null;
			
			for ( int j = 0; j < COLUMNS; j++ ) {
				for ( int k = 0; k < ROWS; k++ ) {
					if ( firstChoice_str[j].equals(secondChoice_str[k]) ) {
						if ( number == null ) {
							number = Integer.valueOf(firstChoice_str[j]);
						}
						else {
							number = -1;
						}
						
					}
				}
			}
			
			if ( number == null ) {
				System.out.println("Case #" + (i+1) + ": Volunteer cheated!");
			}
			else if ( number == -1 ) {
				System.out.println("Case #" + (i+1) + ": Bad magician!");
			}
			else {
				System.out.println("Case #" + (i+1) + ": " + number);
			}
		}
	}

	ArrayList<String> parseFile(BufferedReader br) {

		ArrayList<String> fileContent = new ArrayList<String>();

		try {
			while (br.ready()) { // br.read() returns false when the Buffer is
									// not ready (end of file).

				fileContent.add(br.readLine()); // this statement reads a
												// line from the file and
												// prints it to the console
												// (and moves to next line
												// and is "ready" if there
												// IS a next line).
			}

			br.close(); // dispose of the resources after using them.

		} catch (IOException e) { // Error with transaction.
			e.printStackTrace();
			// Put something here like an error message.
			System.out.println("There was an error reading the file.");
		}

		return fileContent;

	}

	BufferedReader readFile(String filename) {

		File file = new File(filename);
		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(file);

			br = new BufferedReader(fr);

		} catch (FileNotFoundException e) { // File not found.
			e.printStackTrace();
			// Put something here like an error message.
			System.out.println("The file could not be found.");
		}

		return br;
	}

}
