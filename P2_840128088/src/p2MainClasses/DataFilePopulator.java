package p2MainClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

import dataManagementClasses.AttributeInSchema;
import dataManagementClasses.TableSchema;
import generalUtilities.DataUtils;
import tableCollectionClasses.Table;

/**
 * This class calls the execution of data file population according to the project specs.
 * The population could be done in two ways: Reading all data from the user or
 * reading part of the content from a valid file and then read more data from user.
 * 
 * @author J.A. Sanchez Perez
 *
 */
public class DataFilePopulator {

	private RandomAccessFile raf;
	private Scanner input;
	private Table table;
	private TableSchema ts;
	private String fname;
	private ArrayList<AttributeInSchema> attrs;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String fname = new String(args[0]);

		DataFilePopulator datafp = new DataFilePopulator(fname, in);

		datafp.populate();

	}

	/**
	 * Creates an instance of this with a file name and an input scanner.
	 * @param fName the file
	 * @param input the scanner
	 */
	public DataFilePopulator(String fName, Scanner input) {
		// TODO Auto-generated constructor stub

		this.fname = fName;
		this.input = input;
		attrs = new ArrayList<>();

	}

	//populate the file
	private void populate() {

		System.out.print("\nWelcome to DataFilePopulator!\n\n");

		// create new File object
		File f = new File("InputData"+ File.separator + fname);

		boolean invalidFile = false;

		if (f.exists()) {
			try {
				//create a raf with the created file
				raf = new RandomAccessFile(f, "rw");

				//check the validity of the given file
				this.table = DataUtils.isValidProjectFile(raf, "p"); 
				
				if(table ==null)
					invalidFile=true;
				else{
					//file is valid. show its content.
					table.displayTable();
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				
			} 
		}else
			populateSchemaFromEmptyFile(f);
		
		//Check if file was valid or not
		if (!invalidFile) {
			// file was valid and this table has its content.
			//get the table schema of this table 
			ts = table.getTableSchema();
			//create a new instance of Table such that no duplication occurs in the case of a table been read.
			this.table = new Table(ts);
			//trigger the population for records
			populateRecords();
		}else{
			//invalid file!
			System.out.println("\n****Sorry, It seems that File " +  this.fname + " is not valid. Try again! ");
		}
	}
	
	/**
	 *Populate the schema of an empty file, as per the specifications.
	 * @param f the empty file.
	 */
	private void populateSchemaFromEmptyFile(File f) {
		String answer, attributeName;
		int attributeID, dataOffset = 0;
		boolean moreAtrributesToAdd = true;

		try {
			raf = new RandomAccessFile(f, "rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
		}

		System.out.print("----File " + fname + " has been created.\n");
		System.out.print("\nDo you want to add a new attribute? Y_/N_");
		while (moreAtrributesToAdd && input.hasNextLine()) {
			// read answer and validate it
			answer = DataUtils.getAndValidateAnswerToQuestionFrom(input,"Do you want to add a new attribute?");

			// if answer was affirmative
			if (answer.equalsIgnoreCase("y")) {

				// read attribute name if answer was affirmative
				attributeName = DataUtils.readAttributeNameFromInput(input);

				// read data type and convert it to its associated ID
				attributeID = DataUtils.getTypeID(DataUtils.readAttributeTypeFromInput(input));

				// Therefore, create a new attribute with name, attribute ID
				// (associated to a data type) and dataoffset
				// in the schema. The first one has 0 offset the second one
				// has an offset equals to the type size of the first one, and so on.
				attrs.add(new AttributeInSchema(attributeName, attributeID, dataOffset));
				//data offset initially is 0
				dataOffset += DataUtils.getTypeSize(attributeID);

				// ask for another attribute again.
				System.out.print("Do you want to add a new attribute? " + "Y_/N_");

			} else {
				// All attributes were recorded successfully. 
				System.out.println("\nThank you! All Attributes have been recorded.\n");
				moreAtrributesToAdd = false;
				//create a table schema with list of attributes.
				ts = TableSchema.getSubschema(attrs);
				//create a new Table with the created table schema.
				this.table = new Table(ts);
				try {
					//save the schema to the file
					ts.saveSchema(raf);
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	/**
	 * Request data for records.
	 */
	private void populateRecords() {
		String answer;
		boolean moreRecordsToAdd = true;

		// ask if the use wants to add a new record
		System.out.print("\nDo you want to add a new record? _Y/_N");
		while (moreRecordsToAdd && input.hasNextLine()) {
			// read answer to question and validate it
			answer = DataUtils.getAndValidateAnswerToQuestionFrom(input,"Do you want to add a new record?" );

			// if answer was affirmative
			if (answer.equalsIgnoreCase("y")) {
				// Request and add the record to the table
				this.table.addRecord(DataUtils.requestDataForRecord(ts, input));
				// ask for a new record
				System.out.print("\nDo you want to add a new record? _Y/_N");
			} else {
				// no more records to add!
				moreRecordsToAdd = false;
				// write to raf
				try {
					table.writeTableDataToFile(raf);
					raf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
