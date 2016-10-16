package p2MainClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

import dataManagementClasses.AttributeInSchema;
import generalUtilities.DataUtils;
import tableCollectionClasses.Table;

/**
 * This class has the purpose of analyzing tables contained in RAFs that comply
 * with the specifications of this project. The analysis is nothing but table
 * tuples frequency distribution.
 * 
 * @author J.A. Sanchez Perez
 *
 */
public class TableAnalyzer {

	private String fname;
	private Scanner input;
	private Table table;
	private ArrayList<Integer> attributesToanalyze;

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		TableAnalyzer analyzer = new TableAnalyzer(args[0], in);
		analyzer.analyze();

	}

	/**
	 * Creates a new instance of this with given file name and input.
	 * 
	 * @param fname
	 *            the file name
	 * @param input
	 *            the input source
	 */
	public TableAnalyzer(String fname, Scanner input) {
		// TODO Auto-generated constructor stub
		this.fname = fname;
		this.input = input;

	}

	/**
	 * Triggers the analysis.
	 */
	public void analyze() {

		System.out.print("\nWelcome to Table Analyzer!\n\n");

		// create new File object
		File f = new File("InputData" + File.separator + fname);

		boolean invalidFile = false, analyzeMoreData = true;
		;
		String answer = new String("");

		// check if file exists
		if (f.exists()) {

			try {
				RandomAccessFile raf = new RandomAccessFile(f, "r");

				table = DataUtils.isValidProjectFile(raf, "a");

				if (table == null)
					invalidFile = true;
				else {
					// file is valid

					// show table content and attributes summary table
					table.displayTable();
					System.out.println("\n");
					table.showAttributeSummaryTable();

					// Ask for analyze data
					System.out.print("\nDo you want to analyze data from " + fname + "? Y_/N_");
					while (analyzeMoreData && input.hasNextLine()) {
						// read next line and validate it
						answer = DataUtils.getAndValidateAnswerToQuestionFrom(input, "Do you want to analyze data from " + fname + "? ");

						// if answer was affirmative
						if (answer.equalsIgnoreCase("y")) {
							
							//request the attributes to be analyzed. By their columns as shown in the small table.
							this.attributesToanalyze = requestAttributesToAnalyze(input);
							
						} else {
							// answer was negative
							System.out.println("\nThanks for using Table Analyzer!");
							analyzeMoreData = false;
						}


						if(analyzeMoreData)
						System.out.print("\nDo you want to analyze data from " + fname + "? Y_/N_");

					}
					
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//
			}

		} else {
			// file does not exist.
			System.out.println("Sorry, " + fname + " does not exist. Try again later!");
		}

		if (invalidFile)
			System.out.println("Sorry, " + fname + " is not a valid file to analyze. Try again with another file.");
	}

	private ArrayList<Integer> requestAttributesToAnalyze(Scanner in) {
		String answer;
		boolean moreAttributes = true;
		ArrayList<Integer> list = new ArrayList<>();

		// ask the first time to make the user type in console.
		System.out.print("Next Attribute Index: ");
		while (moreAttributes && in.hasNextLine()) {
			// read answer
			answer = in.nextLine();

			// try to parse the input to an integer
			try {
				int n = Integer.parseInt(answer);
				// n has the integer answer.
				if (n < 1 || n > table.getNumberOfAttrs()) {
					
					if (n < 0) {
						System.out.println("\nAll attributes to analyze have been recorded. The analysis will start...\n");
						moreAttributes = false;
					}else{
						System.out.println(
								"\n****Invalid answer. Valid numbers are from 1 to " + table.getNumberOfAttrs() + "\n");
					}

				}else {
					boolean inList = false;
					for (int i = 0; i < list.size() && !inList; i++)
						if (list.get(i).equals(n))
							inList = true;

					if (!inList)
						list.add(n);
					else
						System.out.println(
								"\n****The attribute has been already selected. Choose a different attribute to analyze.\n");

				}
			} catch (NumberFormatException e) {
				// failed to parse. The given number is not a valid int.
				System.out.println("\n****Invalid answer. Type a valid integer number\n");
			}

			// ask for the next attribute to add.
			if (moreAttributes)
				System.out.print("Next Attribute Index: ");
		}
		
		return list;

	}
}
