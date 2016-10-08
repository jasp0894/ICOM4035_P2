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
import tableCollectionClasses.Record;
import tableCollectionClasses.Table;

public class DataFilePopulator {

	private RandomAccessFile raf;
	private Scanner input;
	private Table table;
	private TableSchema ts;
	private String fname;
	private ArrayList<AttributeInSchema> attrs;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String fname = new String("input9.txt");

		DataFilePopulator datafp = new DataFilePopulator(fname, in);

		datafp.populate();

	}

	public DataFilePopulator(String fName, Scanner input) {
		// TODO Auto-generated constructor stub

		this.fname = fName;
		this.input = input;
		attrs = new ArrayList<>();

	}

	private void populate() {

		System.out.print("Welcome to DataFilePopulator!\n\n");

		// create new File object
		File f = new File(fname);

		boolean invalidFile = false;

		if (f.exists()) {

			// check if it is a valid file according to this project specs
			// if it is, then read its content, might be only the schema but
			// could also be a whole table
			// show its content

			// readSchemaFromFile()
			// isValidFile(f or raf)
			// if not end with appropriate message
			// if valid, then read its content readFileContent(r or raf)
			// showFileContent()
			

			try {
				raf = new RandomAccessFile(f, "rw");
				
				this.ts = TableSchema.getInstance(raf);
				this.table = new Table(ts);
				
				for(int i=0; i<ts.getNumberOfAttrs(); i++){
					table.addRecord(new Record(ts));
				}
				
				if(ts.ge)
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
			
		} else {

			populateSchemaFromEmptyFile(f);
		}

		if (!invalidFile) {
			// code to add data (records)
			System.out.println("It's time to add records containing data for each attribute!");
			// read data for the records
			populateRecords();
		}else{
			System.out.println("\n****Sorry, It seems that File " +  this.fname + " is not valid. Try again! ");
		}
	}

	private void populateSchemaFromEmptyFile(File f) {
		String answer, attributeName;
		int attributeID, dataOffset = 0;
		boolean moreAtrributesToAdd = true;

		try {
			raf = new RandomAccessFile(f, "rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.print("----File " + fname + " has been created.\n");
		System.out.print("\nDo you want to add a new attribute? Y_/N_");
		while (moreAtrributesToAdd && input.hasNextLine()) {
			// read next line and validate it
			answer = DataUtils.getAndValidateAnswerToQuestionFrom(input);

			// if answer was affirmative
			if (answer.equalsIgnoreCase("y")) {

				// read attribute name if answer was affirmative
				attributeName = DataUtils.readAttributeNameFromInput(input);

				// read data type
				attributeID = DataUtils.getTypeID(DataUtils.readAttributeTypeFromInput(input));

				// Therefore, create a new attribute with name, attribute ID
				// (associated to a data type) and dataoffset
				// in the schema. The first one has 0 offset the second one
				// has an offset equals to the size of the first one.
				attrs.add(new AttributeInSchema(attributeName, attributeID, dataOffset));
				dataOffset += DataUtils.getTypeSize(attributeID);

				// ask for another attribute again.
				System.out.print("Do you want to add a new attribute? " + "Y_/N_");

			} else {
				// All attributes were recorded successfully. Should
				// continue to a point
				// where the process is the same for both (existing file or
				// new file)
				System.out.println("\nThank you! All Attributes have been recorded.\n");
				moreAtrributesToAdd = false;
				ts = TableSchema.getSubschema(attrs);
				this.table = new Table(ts);
				try {
					ts.saveSchema(raf);
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	private void populateRecords() {
		String answer;
		boolean moreRecordsToAdd = true;

		// ask if the use wants to add a new record
		System.out.print("\nDo you want to add a new record? _Y/_N");
		while (moreRecordsToAdd && input.hasNextLine()) {
			// read next line and validate it
			answer = DataUtils.getAndValidateAnswerToQuestionFrom(input);

			// if answer was affirmative
			if (answer.equalsIgnoreCase("y")) {
				// add the record to the table
				this.table.addRecord(DataUtils.requestDataForRecord(ts, input));
				// ask for a new record
				System.out.print("\nDo you want to add a new record? _Y/_N");
			} else {
				// no more records to add!
				moreRecordsToAdd = false;
				// write to raf
				try {
					table.writeTableDataToFile(raf);
					table.displayTable();
					raf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
