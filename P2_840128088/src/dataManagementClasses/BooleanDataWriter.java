package dataManagementClasses;

import generalUtilities.DataUtils;
import interfaces.DataWriter;

/**
 * Defines a data writer for Boolean data types.
 * @author J.A. Sanchez Perez
 *
 */
public class BooleanDataWriter implements DataWriter {

	public static final BooleanDataWriter INSTANCE = new BooleanDataWriter(); 
	
	//singleton 
	private BooleanDataWriter() {}; 
	
	/**
	 * Writes boolean data to array of bytes.
	 */
	public void writeDataToArrayOfBytes(byte[] b, int index, Object rvalue) {
		Boolean value = (Boolean) rvalue; 
		b[index] = (byte) (value ? 1 : 0);  
	}

	@Override
	public String toString(Object value) {
		return String.format(DataUtils.BOOLEANFORMAT, (Boolean) value); 
	}
}
