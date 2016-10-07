/**
 * 
 */
package dataManagementClasses;

import java.util.Scanner;

import interfaces.DataReader;

/**
 * @author J.A. Sanchez Perez
 *
 */
public class CharDataReader implements DataReader {

	private static final int CHARSIZE = Character.BYTES;
	public static final CharDataReader INSTANCE = new CharDataReader();
	/**
	 * 
	 */
	private CharDataReader() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see interfaces.DataReader#readDataFromArrayOfBytes(byte[], int)
	 */
	@Override
	public Character readDataFromArrayOfBytes(byte[] b, int index) {
		// TODO Auto-generated method stub
		int value = 0; 
		int lSB; 
		for (int i=0; i < CHARSIZE; i++) { 
			value = value << 8; 
			lSB = 0x000000ff & b[index + i];
			value = value | lSB; 
		}
		return String.valueOf(value).charAt(0);
		
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
