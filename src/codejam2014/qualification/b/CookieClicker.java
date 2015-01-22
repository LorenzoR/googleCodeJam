package codejam2014.qualification.b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CookieClicker {

	private static final String INPUT_FILENAME = "src/codejam2014/qualification/b/data/B-large-practice.in";

	private static final Integer COOKIES_PER_SECOND = 2;

	public static void main(String[] args) {

		CookieClicker cookieClicker = new CookieClicker(INPUT_FILENAME);
		
//		for ( int i = 0; i < 200; i++ ) {
//			System.out.println(cookieClicker.calculateTime(358.977, 3.1, 770.0, i));
//		}
		
//		System.out.println(cookieClicker.solve(358.977, 3.1, 770.0));

	}

	public CookieClicker(String input) {

		BufferedReader br = readFile(input);

		ArrayList<String> fileContent = parseFile(br);

		int cases = Integer.valueOf(fileContent.get(0));

		for (int i = 1; i <= cases; i++) {
			String[] values = fileContent.get(i).split(" ");
			
			boolean keeprun = true;
			double consumedTime = 0.0;

			double cokkiesRate = 2.0;
	        double C_FARMCOST = Double.valueOf(values[0]);
	        double F_COKKIES_PER_SEC = Double.valueOf(values[1]);
	        double X_TARGET = Double.valueOf(values[2]);

			
	        while(keeprun) {
	            if((X_TARGET - C_FARMCOST)/(cokkiesRate) 
	                    < X_TARGET / (cokkiesRate + F_COKKIES_PER_SEC)){
	                consumedTime = consumedTime + (X_TARGET/cokkiesRate);
	                cokkiesRate+=F_COKKIES_PER_SEC;
	                keeprun = false;
	            }else {

	                consumedTime = consumedTime + (C_FARMCOST/cokkiesRate);
	                cokkiesRate+=F_COKKIES_PER_SEC;
	            }
	        }        
	        
	        System.out.println("Case #"+i+": "+String.format("%.7f", consumedTime));
			
			//System.out.println("Case #" + i + ": " + String.format("%.7f", solve(Double.valueOf(values[0]), Double.valueOf(values[1]), Double.valueOf(values[2]))));
		}
	}

	public double solve(double farmCost, double farmProduction, double goal) {
		double seconds = 0;
		double minSeconds = Double.MAX_VALUE;
		boolean done = false;
		int qtyFarms = 0;

		while ( !done ) {
			seconds = calculateTime(farmCost, farmProduction, goal, qtyFarms++);
			
			if ( seconds > minSeconds ) {
				done = true;
			}
			else {
				minSeconds = seconds;
			}
		}

		return minSeconds;
	}

	public Double calculateTime(double farmCost, double farmProduction, double goal,
			int qtyFarms) {
		double resp = 0;

		if (qtyFarms < 0) {
			return null;
		}

//		if (qtyFarms == 1) {
//			return goal / COOKIES_PER_SECOND;
//		}

		for (int i = 0; i < qtyFarms; i++) {
			resp += farmCost / (COOKIES_PER_SECOND + i * farmProduction);
		}

		return resp + (goal / (COOKIES_PER_SECOND + qtyFarms * farmProduction));
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
