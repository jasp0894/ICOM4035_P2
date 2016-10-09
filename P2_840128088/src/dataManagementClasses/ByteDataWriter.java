package dataManagementClasses;

import generalUtilities.DataUtils;
import interfaces.DataWriter;

/**
 * Defines a data writer for Boolean data types.
 * @author J.A. Sanchez Perez
 *
 */
public class ByteDataWriter implements DataWriter {

	public static final ByteDataWriter INSTANCE = new ByteDataWriter();
	
	
	//singleton
	private ByteDataWriter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * write byte data to array of bytes.
	 */
	@Override
	public void writeDataToArrayOfBytes(byte[] a, int starting, Object value) {
		// TODO Auto-generated method stub
		Byte v = (Byte) value;
		
		a[starting] = v; //add the value directly to the array since it is only a byte
		
		
	}

	@Override
	public String toString(Object value) {
		// TODO Auto-generated method stub
		return String.format(DataUtils.INTEGERFORMAT, (Byte) value); 
	}

}
