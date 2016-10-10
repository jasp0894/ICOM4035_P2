package dataManagementClasses;

import java.util.Scanner;

import generalUtilities.DataUtils;
import interfaces.DataReader;

/**
 * Defines a DataReader for Data data types.
 * @author J.A. Sanchez Perez
 *
 */
public class DateDataReader implements DataReader {

	public static final DateDataReader INSTANCE = new DateDataReader(); 
	
	//singleton
	private DateDataReader() {}; 

	
	/**
	 * read Date data types from array bytes.
	 */
	public Date readDataFromArrayOfBytes(byte[] b, int index) {
		try {
			return new Date(b[index], b[index+1], ShortDataReader.INSTANCE.readDataFromArrayOfBytes(b, index+2));
		} catch (InvalidDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;// If date is not valid, then returns null;
		} 
	}

	@Override
	public Object readDataFromInputScanner(Scanner input) {

		try{ 
			System.out.print("\n\tmonth: "); 
			byte month = input.nextByte(); 

			System.out.print("\tday: "); 
			byte day = input.nextByte(); 

			System.out.print("\tyear: "); 
			short year = input.nextShort(); 

			input.nextLine();    // clean the buffer
			if (DataUtils.isValidDate(month, day, year))
				return new Date(month, day, year); 
			
		} catch (Exception e) { }
			
		input.nextLine();    // clean the buffer
		
		return null;
	}
	
}
