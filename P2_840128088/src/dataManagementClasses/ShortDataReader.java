/**
 * 
 */
package dataManagementClasses;

import java.util.Scanner;

import interfaces.DataReader;

/**
 * Defines a DataReader for Short data types.
 * @author J.A. Sanchez Perez
 *
 */
public class ShortDataReader implements DataReader {
	
	private static final int SHORTSIZE = Short.BYTES;
	public static final ShortDataReader INSTANCE = new ShortDataReader();//singleton object

	/**
	 * 
	 */
	private ShortDataReader() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Short readDataFromArrayOfBytes(byte[] b, int index) {
		// TODO Auto-generated method stub
		int value = 0; 
		int lSB; 
		for (int i=0; i < SHORTSIZE; i++) { 
			value = value << 8; 
			lSB = 0x000000ff & b[index + i];
			value = value | lSB; 
		}
		return Short.parseShort(Integer.toString(value)) ;//parse value converted to string 
	}
	
	@Override
	public Object readDataFromInputScanner(Scanner input) {
		String s = input.nextLine(); 
		try {
			short v = Short.parseShort(s); 
			return new Short(v); 
		} catch (Exception e) { 
			return null; 
		}
	}
}
