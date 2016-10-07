package interfaces;

import java.util.Scanner;

/**
 * Specializes in reading a particular data type from a specified 
 * location in an array of type byte[]
 * @author pedroirivera-vega
 *
 */
public interface DataReader {
	/**
	 * Reads data value from an array of bytes and return the value of certain data type which is a sub-class of object. 
	 * the data starts at a[starting] an continues to a[starting+1] an so on. If the internal structure of the 
	 * array is known, then the data value in the array will end at an index that is not necessarily a.length -1.
	 * @param a the array of bytes
	 * @param starting starting index
	 * @return data value
	 */
	Object readDataFromArrayOfBytes(byte[] a, int starting);
	
	/**
	 * Reads a data value from an input scanner. 
	 * @param input A reference to an input scanner.
	 * @return the data value of certain type. 
	 */
	Object readDataFromInputScanner(Scanner input); 
}
