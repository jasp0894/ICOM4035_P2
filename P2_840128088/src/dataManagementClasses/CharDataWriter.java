/**
 * 
 */
package dataManagementClasses;

import generalUtilities.DataUtils;
import interfaces.DataWriter;
/**
 * Defines a data writer for Character data types.
 * @author J.A. Sanchez Perez
 *
 */
public class CharDataWriter implements DataWriter {

	private static final int CHARSIZE = Character.BYTES;
	public static final CharDataWriter INSTANCE = new CharDataWriter();
	
	//singleton
	private CharDataWriter() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see interfaces.DataWriter#writeDataToArrayOfBytes(byte[], int, java.lang.Object)
	 */
	@Override
	public void writeDataToArrayOfBytes(byte[] b, int index, Object rv) {
		Character v = (Character) rv; 
		
		char value = v;//Integer.parseInt(Character.toString(v), 16);  //casting from Character to int
		int lSB; 
		for (int i=0; i < CHARSIZE; i++) { 
			lSB = 0x000000ff & value;
			value = (char) (value >> 8); 
		//Now we have the least significant byte of value (lSB), so we can add it to the array of bytes
		//BUT, the array of bytes follows BIG ENDIAN notation, so we will have to add lSB to highest index of b
		//allocated for this value. The highest index for this value is index+CHARSIZE-1. The next bytes will 
		//be added kind of backwards, that explains the - i.
		    b[index + CHARSIZE - i - 1] = (byte) (lSB & 0x000000ff); 
		}
	}

	@Override
	public String toString(Object value) {
		return String.format(DataUtils.CHARFORMAT, (Character) value);
		
	}

}
