package p2MainClasses;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Scanner;

import generalUtilities.DataUtils;
import tableCollectionClasses.Table;

public class DataFilePopulator {

	private RandomAccessFile raf;
	private Scanner input;
	private Table table;
	private String fname;

	public DataFilePopulator(String fName, Scanner input) {
		// TODO Auto-generated constructor stub

		this.fname = fName;
		this.input = input;
	}

	private void populate() {

		System.out.print("Welcome to DataFilePopulator!/n");

		// create new File object
		File f = new File(fname);

		String answer;

		if (f.exists()) {

			// check if it is a valid file according to this project specs
			// if it is, then read its content, might be only the schema but
			// could also be a whole table
		} else {

			System.out.print("File " + fname + " is empty.\n");
			System.out.print("Do you want to add a new attribute? " + "Y_/N_\n");
			while (input.hasNext()) {
			
				// read next line and validate it
				answer = input.next();
				
				while (!(answer.equalsIgnoreCase("y")||answer.equalsIgnoreCase("n"))){
					System.out.print("Incorrect answer. Do you want to add a new attribute? " + "Y_/N_\n");
					
					// read answer again
					answer = input.next();
				}

				//if answer was affirmative
				if (answer.equalsIgnoreCase("y")) {
					System.out.print("Enter the name of the attribute: ");

					// read answer
					answer = input.next();

					while (!DataUtils.isValidName(answer)) {
						System.out.print("\nInvalid name. Enter a valid name of the attribute: ");
						answer = input.next();
					}
					
					// attribute name is valid at this point
					System.out.print("\nEnter type for the attribute {byte, boolean, char, short, int, float,double, long}: ");
					//read answer
					answer = input.next();
					
					//keep asking if invalid data type
					while (!DataUtils.isValidDataType(answer)) {
						System.out.print("\nInvalid type. Enter a valid type for the attribute {byte, boolean, char, short, int, float,double, long}: ");
						answer = input.next();
					}
					
					//data type is valid at this point
					//ask for another attribute again.
					System.out.print("Do you want to add a new attribute? " + "Y_/N_\n");

				}else{
					//All attributes were recorded successfully. Should continue to a point
					//where the process is the same for both (existing file or new file)
					System.out.println("answer was no!");
					input.next(); //clean buffer?
				}

			}

		

		}
		
		//code to add data

	}

}
