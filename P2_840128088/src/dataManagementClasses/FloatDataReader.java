package dataManagementClasses;

import java.util.Scanner;

import interfaces.DataReader;



/**
 * Defines a DataReader for Float data types.
 * @author J.A. Sanchez Perez
 *
 */
public class FloatDataReader implements DataReader {

	private static final int FLOATSIZE = Float.BYTES; 
	public static final FloatDataReader INSTANCE = new FloatDataReader(); //singleton object
	
	//The only way to use this class is to use INSTANCE which is static field.
	//No other objects of this class can be created
	private FloatDataReader() {}; 

	public Float readDataFromArrayOfBytes(byte[] b, int index) {
		int value = 0; 
		int lSB; 
		for (int i=0; i < FLOATSIZE; i++) { 
			value = value << 8; 
			lSB = 0x000000ff & b[index + i];
			value = value | lSB; 
		}
		return Float.intBitsToFloat(value); //convert from int to Float 
	}
	
	@Override
	public Object readDataFromInputScanner(Scanner input) {
		String s = input.nextLine(); 
		try {
			Float v = Float.parseFloat(s); 
			return new Float(v); 
		} catch (Exception e) { 
			return null;  //if the line does not contain a float 
		}
	}

}

