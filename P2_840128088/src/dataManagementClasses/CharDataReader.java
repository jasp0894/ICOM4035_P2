/**
 * 
 */
package dataManagementClasses;

import java.util.Scanner;

import interfaces.DataReader;

/**
 * Defines a DataReader for Character data types.
 * @author J.A. Sanchez Perez
 *
 */
public class CharDataReader implements DataReader {

	private static final int CHARSIZE = Character.BYTES;
	public static final CharDataReader INSTANCE = new CharDataReader();
	
	//singleton
	private CharDataReader() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see interfaces.DataReader#readDataFromArrayOfBytes(byte[], int)
	 */
	@Override
	public Character readDataFromArrayOfBytes(byte[] b, int index) {
		// TODO Auto-generated method stub
		char value = 0; 
		int lSB; 
		for (int i=0; i < CHARSIZE; i++) { 
			value = (char) (value << 8); 
			lSB = 0x000000ff & b[index + i];
			value = (char) (value | lSB); 
		}
		return value;	
	}
	
	@Override
	public Object readDataFromInputScanner(Scanner input) {
		String s = input.nextLine(); 
		try {
			char v = s.charAt(0); 
			return new Character(v); 
		} catch (Exception e) { 
			return null; 
		}
	}

}
