package p2MainClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

import dataManagementClasses.AttributeInSchema;
import generalUtilities.DataUtils;
import tableCollectionClasses.Table;
import tableCollectionClasses.TupleInTable;
import tableCollectionClasses.ValueInTuple;

/**
 * This class has the purpose of analyzing tables contained in RAFs that comply
 * with the specifications of this project. The analysis is nothing but tables
 * with tuples frequency distribution and percentage.
 * 
 * @author J.A. Sanchez Perez
 *
 */
public class TableAnalyzer {

	private String fname;
	private Scanner input;
	private Table table;
	private ArrayList<Integer> attributesToanalyze;
	private ArrayList<TupleInTable> tuples;

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
		
		String answer = new String("");

		// check if file exists
		if (f.exists()) {

			try {
				//create  raf instance with the created file
				RandomAccessFile raf = new RandomAccessFile(f, "r");

				//check the validity of the given file.
				table = DataUtils.isValidProjectFile(raf, "a");

				if (table == null)
					//file was not valid
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
						// read answer to the question and validate it. Will keep asking if not valid.
						answer = DataUtils.getAndValidateAnswerToQuestionFrom(input,
								"Do you want to analyze data from " + fname + "? ");

						// if answer was affirmative
						if (answer.equalsIgnoreCase("y")) {
							// request the attributes to be analyzed. By their
							// columns as shown in the small table.
							this.attributesToanalyze = requestAttributesToAnalyze(input);
							
							//check the size of the list of attributes to analyze
							if (attributesToanalyze.size() == 0) {
								System.out.println("No Attributes were selected to be analyzed...");
							} else {

								// analyze the attributes specified by the user.
								analyzeAttributes();

								// show results of analysis
								showAnalysisResultsTable();
							}
						} else {
							// answer was negative
							System.out.println("\nThanks for using Table Analyzer!");
							analyzeMoreData = false;
						}

						if (analyzeMoreData)
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

	/**
	 * Request the attributes to analyze from the user.
	 * 
	 * @param in
	 *            the Scanner input source.
	 * @return a list containing the indexes of the attributes to analyze.
	 */
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
						System.out.println(
								"\nAll attributes to analyze have been recorded. The analysis will start...\n");
						moreAttributes = false;
					} else {
						System.out.println(
								"\n****Invalid answer. Valid numbers are from 1 to " + table.getNumberOfAttrs() + "\n");
					}

				} else {
					//check if the given attribute had been already chosen 
					boolean inList = false;
					for (int i = 0; i < list.size() && !inList; i++)
						if (list.get(i).equals(n))
							inList = true;

					if (!inList)
						//if not repeated, then add it to the list
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

	/**
	 * Analyze the attributes chosen by the user.
	 */
	private void analyzeAttributes() {
		// initialize the list of tuples.
		tuples = new ArrayList<>();

		// iterate over each record in this table
		for (int i = 0; i < table.getNumberOfRecords(); i++) {
			// create a new TupleInTable to hold the current new Tuple.
			TupleInTable tup = new TupleInTable();

			// for each attribute of interest (inside list of attributes to analyze):
			for (int j = 0; j < attributesToanalyze.size(); j++) {
				// Get the attribute at the given column in this Table (by the user).
				// The column is inside the list of attributes to analyze
				// (filled after requesting values from the user)
				// and we need to subtract 1 from it, since the columns are in
				// the range
				// 1->n and the actual positions are from 0 to n-1
				int attrPosition = attributesToanalyze.get(j) - 1;
				AttributeInSchema attr = table.getAttribute(attrPosition);

				// get the value of this attribute inside the current record.
				Object value = table.getRecord(i).readData(attrPosition);

				// create a new ValueInTuple
				ValueInTuple val = new ValueInTuple(attr, value);

				// add the current ValueInTuple to the current tuple.
				tup.addValue(val);
			}
			// at this point we have a valid tuple of values. But, we don't know if it is already in list or not.
			
			// check occurrences
			boolean inList = false;
			// initialized outside the loop to be able to access the element in
			// list if the loop is interrupted
			int k = 0;
			for (; k < tuples.size() && !inList; k++)
				if (tuples.get(k).equals(tup))
					inList = true;

			if (inList) {
				//get the repeated tuple. Accessed by means of k
				TupleInTable repeatedTuple = tuples.get(k - 1);
				// increase the occurrence of the tuple
				repeatedTuple.increasOcurrenceByOne();

				// set the partial percentage of appearance of this tuple in
				// table.
				repeatedTuple.setPercentage(
						(((double) repeatedTuple.getOcurrence() / (double) table.getNumberOfRecords()) * 100));

			} else {
				//the current tuple is not in list, so we can add it.
				tuples.add(tup);

				// set the partial percentage of appearance of this tuple in
				// table.
				tup.setPercentage((((double) tup.getOcurrence() / (double) table.getNumberOfRecords()) * 100));
			}
		}
	}

	/**
	 * Show the results of the analysis in the form of a table.
	 */
	private void showAnalysisResultsTable() {
		System.out.println("\nAnalysis results: \n");

		String s = "|";
		for (int i = 0; i < attributesToanalyze.size(); i++)
			// Show the name of the analyzed attributes in the header. Recall
			// that the list of attributes to analyze contains
			// all the attributes columns, so we have to subtract one for them
			// to be valid positions
			s += String.format(DataUtils.STRINGFORMAT, table.getAttribute(attributesToanalyze.get(i) - 1).getName());

		// show the last elements in header (frequency and percentage)
		s += String.format(DataUtils.STRINGFORMAT, "Frequency") + String.format(DataUtils.STRINGFORMAT, "Percentage")
				+ " |";
		s += "\n";

		for (int i = 0; i <= (this.attributesToanalyze.size() + 2) * DataUtils.VALUEWIDE + 2; i++)
			s += '=';

		s += "\n";
		
		//iterate over the list of tuple
		for (int i = 0; i < tuples.size(); i++) {
			// show the values of the current tuple
			TupleInTable currentTuple = tuples.get(i);
			for (int j = 0; j < currentTuple.size(); j++) {
				// get the current ValueInTuple
				ValueInTuple val = currentTuple.getValue(j);

				// call the toString of the current val attribute
				s += val.getAttr().toString(val.getValue());
			}
			// show the occurrence of the current tuple
			s += String.format(DataUtils.INTEGERFORMAT, currentTuple.getOcurrence());

			// show the percentage of the current tuple
			s += String.format(DataUtils.FLOATFORMAT, currentTuple.getPercentageInTable());
			s += "\n";
		}

		// print table
		System.out.println(s);
	}
}
