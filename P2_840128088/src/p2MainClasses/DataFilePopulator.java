package p2MainClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

import dataManagementClasses.Attribute;
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
		String fname = new String("input3.txt");
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

		System.out.print("Welcome to DataFilePopulator!\n");

		// create new File object
		File f = new File(fname);
		// string object to record inputs
		String answer, attributeName;
		int attributeID, dataOffset = 0;
		boolean moreAtrributesToAdd = true, moreRecordsToAdd = true;

		if (f.exists()) {

			// check if it is a valid file according to this project specs
			// if it is, then read its content, might be only the schema but
			// could also be a whole table
			// show its content
			System.out.println("file exists!!!!!!!!!!!");
		} else {

			try {
				raf = new RandomAccessFile(f, "rw");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.print("File " + fname + " has been created.\n");
			System.out.print("Do you want to add a new attribute? Y_/N_");
			while (moreAtrributesToAdd && input.hasNextLine()) {
				// read next line and validate it
				answer = input.nextLine();

				while (!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n"))) {
					System.out.print("Incorrect answer. Do you want to add a new attribute? Y_/N_\n");

					// read answer again
					answer = input.nextLine();
				}

				// if answer was affirmative
				if (answer.equalsIgnoreCase("y")) {
					System.out.print("Enter the name of the attribute: ");

					// read answer
					answer = input.nextLine();

					while (!DataUtils.isValidName(answer)) {
						System.out.print("\nInvalid name. Enter a valid name of the attribute: ");
						answer = input.nextLine();
					}

					// attribute name is valid at this point
					attributeName = answer;
					System.out.print(
							"\nEnter type for the attribute {byte, boolean, char, short, int, float,double, long, date}: ");
					// read answer
					answer = input.nextLine();

					// keep asking if invalid data type
					while (!DataUtils.isValidDataType(answer)) {
						System.out.print(
								"\nInvalid type. Enter a valid type for the attribute {byte, boolean, char, short, int, float,double, long, date}: ");
						answer = input.nextLine();
					}

					// data type is valid at this point
					attributeID = DataUtils.getTypeID(answer);
					// Therefore, create a new attribute with name, attribute ID
					// (associated to a data type) and dataoffset
					// in the schema. The first one has 0 offset the second one
					// has an offset equals to the size of the first one.
					attrs.add(new AttributeInSchema(attributeName, attributeID, dataOffset));
					dataOffset += DataUtils.getTypeSize(attributeID);

					// ask for another attribute again.
					System.out.print("Do you want to add a new attribute? " + "Y_/N_\n");

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

		// code to add data (records)
		System.out.println("It's time to add records containing data for each attribute!");

		// ask if the use wants to add a new record
		System.out.print("Do you want to add a new record? _Y/_N");
		while (moreRecordsToAdd && input.hasNextLine()) {
			// read next line and validate it
			answer = input.nextLine();

			while (!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n"))) {
				System.out.print("Incorrect answer. Do you want to add a new Record? Y_/N_\n");

				// read answer again
				answer = input.nextLine();
			}

			// if answer was affirmative
			if (answer.equalsIgnoreCase("y")) {

				Record r = new Record(ts);
				for (int i = 0; i < table.getNumberOfAttrs(); i++) {
					AttributeInSchema ais = table.getAttribute(i);

					System.out.print("Enter value for the following attribute " + ((Attribute) ais).toString() + ": ");

					// read answer
					Object value = ais.readDataValueFromInputScanner(input);
					while (value == null) {
						System.out.print("\nInvalid value. Enter a valid value for the following attribute "
								+ ((Attribute) ais).toString() + ": ");
						value = ais.readDataValueFromInputScanner(input);
					}

					// At this point, value should be valid and we can write it
					// in the record.
					r.writeData(i, value);

				}
				// add the record to the table
				this.table.addRecord(r);
				System.out.print("Do you want to add a new record? _Y/_N");

			} else {
				// no more records to add!
				moreRecordsToAdd = false;
				// write to raf (for now display the content of the table)
				table.displayTable();
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
