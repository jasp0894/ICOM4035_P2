package dataManagementClasses;

import java.util.Scanner;

import interfaces.DataReader;

/**
 * Defines a DataWriter for Integer data types.
 * @author J.A. Sanchez Perez
 *
 */
public class IntDataReader implements DataReader {

	private static final int INTSIZE = Integer.BYTES; 
	public static final IntDataReader INSTANCE = new IntDataReader(); 
	
	//singleton
	private IntDataReader() {}; 
	
	/**
	 * read Integer data from array of bytes.
	 */
	public Integer readDataFromArrayOfBytes(byte[] b, int index) {
		int value = 0; 
		int lSB; 
		for (int i=0; i < INTSIZE; i++) { 
			value = value << 8; 
			lSB = 0x000000ff & b[index + i];
			value = value | lSB; 
		}
		return value; 
	}
	
	@Override
	public Object readDataFromInputScanner(Scanner input) {
		String s = input.nextLine(); 
		try {
			int v = Integer.parseInt(s); 
			return new Integer(v); 
		} catch (Exception e) { 
			return null; 
		}
	}

}

