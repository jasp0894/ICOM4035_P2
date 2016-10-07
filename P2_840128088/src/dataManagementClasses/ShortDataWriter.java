/**
 * 
 */
package dataManagementClasses;

import generalUtilities.DataUtils;
import interfaces.DataWriter;

/**
 * @author J.A. Sanchez Perez
 *
 */
public class ShortDataWriter implements DataWriter {

	private static final int SHORTSIZE = Short.BYTES;
	public static final ShortDataWriter INSTANCE = new ShortDataWriter();//singleton object
	
	/**
	 * 
	 */
	private ShortDataWriter() {
		// TODO Auto-generated constructor stub
	}
	public void writeDataToArrayOfBytes(byte[] b, int index, Object rv) {
		Short v = (Short) rv; 
		int value = (int) v;  //casting from short(2 bytes) to int (4bytes)
		int lSB; 
		for (int i=0; i < SHORTSIZE; i++) { 
			lSB = 0x000000ff & value;
			value = value >> 8; 
		//Now we have the least significant byte of value (lSB), so we can add it to the array of bytes
		//BUT, the array of bytes follows BIG ENDIAN notation, so we will have to add lSB to highest index of b
		//allocated for this value. The highest index for this value is index+FLOATSIZE-1. The next bytes will 
		//be added kind of backwards, that explains the - i.
		    b[index + SHORTSIZE - i - 1] = (byte) (lSB & 0x000000ff); 
		}
 
	}

	@Override
	public String toString(Object value) {
		return String.format(DataUtils.INTEGERFORMAT, (Short) value);
		
	}
	

}
