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
public class ByteDataReader implements DataReader {
	
	public static final ByteDataReader INSTANCE = new ByteDataReader();
	/**
	 * 
	 */
	private ByteDataReader() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public Byte readDataFromArrayOfBytes(byte[] b, int index) {
		byte lSB = b[index];
		return lSB;
	}
	@Override
	public Object readDataFromInputScanner(Scanner input) {
		// TODO Auto-generated method stub

		String s = input.nextLine();
		
		try{
			
			Byte b = Byte.parseByte(s);
			return new Byte(b);
			
		}catch(Exception e){
			
			return null; //return null if the line does not contain a byte
			
		}
	}
	
	

}
