package dataManagementClasses;
import java.util.Scanner;

import interfaces.DataReader;

/**
 * 
 */

/**
 * @author J.A. Sanchez Perez
 *
 */
public class DoubleDataReader implements DataReader {

	private static final int DOUBLESIZE = Double.BYTES;
	public static final DoubleDataReader INSTANCE = new DoubleDataReader();
	/**
	 * 
	 */
	private DoubleDataReader() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see interfaces.DataReader#readDataFromArrayOfBytes(byte[], int)
	 */
	@Override
	public Double readDataFromArrayOfBytes(byte[] a, int starting) {
		// TODO Auto-generated method stub
		long value = 0;
		long lSB =0;
		for (int i = 0; i < DOUBLESIZE; i++) {
			value = value <<8;
			lSB = 0x0000ff & a[starting +i];
			value = value | lSB;
		}
		
		return Double.longBitsToDouble(value);
	}

	/* (non-Javadoc)
	 * @see interfaces.DataReader#readDataFromInputScanner(java.util.Scanner)
	 */
	@Override
	public Object readDataFromInputScanner(Scanner input) {
		// TODO Auto-generated method stub
		String s = input.nextLine();
		
		try{
			return Double.parseDouble(s);
		}catch(Exception e){
			return null; //if the next line does not contain a LONG
		}
	}


}
