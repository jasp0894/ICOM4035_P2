package dataManagementClasses;

import generalUtilities.DataUtils;
import interfaces.DataWriter;

/**
 * Defines a Data Writer for Float data types.
 * @author J.A. Sanchez Perez
 *
 */
public class FloatDataWriter implements DataWriter{

	private static final int FLOATSIZE = Float.BYTES; 
	public static final FloatDataWriter INSTANCE = new FloatDataWriter(); 
	
	//singleton
	private FloatDataWriter() {}; 
	
	/**
	 * Write Float data to array of bytes.
	 */
	public void writeDataToArrayOfBytes(byte[] b, int index, Object rv) {
		Float v = (Float) rv; 
		int value = Float.floatToIntBits(v); 
		int lSB; 
		for (int i=0; i < FLOATSIZE; i++) { 
			lSB = 0x000000ff & value;
			value = value >> 8; 
		//Now we have the least significant byte of value (lSB), so we can add it to the array of bytes
		//BUT, the array of bytes follows BIG ENDIAN notation, so we will have to add lSB to highest index of b
		//allocated for this value. The highest index for this value is index+FLOATSIZE-1. The next bytes will 
		//be added kind of backwards, that explains the - i.
		    b[index + FLOATSIZE - i - 1] = (byte) (lSB & 0x000000ff); 
		}
 
	}

	@Override
	public String toString(Object value) {
		return String.format(DataUtils.FLOATFORMAT, (Float) value); 
	}
	
}