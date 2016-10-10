/**
 * 
 */
package dataManagementClasses;

import java.util.Scanner;

import interfaces.DataReader;


/**
 * Defines a DataReader for Long data types.
 * @author J.A. Sanchez Perez
 *
 */
public class LongDataReader implements DataReader {

	private static final int LONGSIZE = Long.BYTES;
	public static final LongDataReader INSTANCE = new LongDataReader();
	
	//singleton
	private LongDataReader() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see interfaces.DataReader#readDataFromArrayOfBytes(byte[], int)
	 */
	@Override
	public Long readDataFromArrayOfBytes(byte[] a, int starting) {
		// TODO Auto-generated method stub
		long value = 0;
		int lSB =0;
		for (int i = 0; i < LONGSIZE; i++) {
			value = value <<8;
			lSB = 0x0000ff & a[starting +i];
			value = value | lSB;
		}
		
		return value;
	}

	/* (non-Javadoc)
	 * @see interfaces.DataReader#readDataFromInputScanner(java.util.Scanner)
	 */
	@Override
	public Object readDataFromInputScanner(Scanner input) {
		// TODO Auto-generated method stub
		String s = input.nextLine();
		
		try{
			Long l = Long.parseLong(s);
			return new Long(l);
		}catch(Exception e){
			return null; //if the next line does not contain a LONG
		}
	}

}
