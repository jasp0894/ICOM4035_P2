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

		boolean invalidFile = false, analyzeMoreData = true;;
		String answer = new String("");
		
		// check if file exists
		if (f.exists()) {

			try {
				RandomAccessFile raf = new RandomAccessFile(f, "r");

				Table t = DataUtils.isValidProjectFile(raf, "a");

				if (t == null)
					invalidFile = true;
				else {
					// file is valid

					// show table content and attributes summary table
					t.displayTable();
					System.out.println("\n");
					t.showAttributeSummaryTable();

					// Ask for analyze data
					System.out.print("\nDo you want to analyze data from " + fname + "? Y_/N_");
					while (analyzeMoreData && input.hasNextLine()) {
						// read next line and validate it
						answer = DataUtils.getAndValidateAnswerToQuestionFrom(input);

						// if answer was affirmative
						if (answer.equalsIgnoreCase("y")) {

							
							
						} else {
							// answer was negative
							System.out.println("Thanks for using Table Analyzer!");
							analyzeMoreData = false;
						}
						
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
	
	private ArrayList<AttributeInSchema> requestAttributesToAnalyze(Scanner in){
		return null;
		
	}
}
