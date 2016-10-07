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
public class DoubleDataWriter implements DataWriter {

	private static final int DOUBLESIZE = Double.BYTES;
	public static final DoubleDataWriter INSTACE = new DoubleDataWriter();
	/**
	 * 
	 */
	private DoubleDataWriter() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see interfaces.DataWriter#writeDataToArrayOfBytes(byte[], int, java.lang.Object)
	 */
	@Override
	public void writeDataToArrayOfBytes(byte[] a, int index, Object rv) {
		double v = (double) rv; 
		long value = Double.doubleToLongBits(v); 
		long lSB; 
		for (int i=0; i < DOUBLESIZE; i++) { 
			lSB = 0x00000000000000ff & value;
			value = value >> 8; 
		//Now we have the least significant byte of value (lSB), so we can add it to the array of bytes
		//BUT, the array of bytes follows BIG ENDIAN notation, so we will have to add lSB to highest index of b
		//allocated for this value. The highest index for this value is index+DOUBLESIZE-1. The next bytes will 
		//be added kind of backwards, that explains the - i.
		    a[index + DOUBLESIZE - i - 1] = (byte) (lSB & 0x00000000000000ff); 
		}
 
	}

	@Override
	public String toString(Object value) {
		return String.format(DataUtils.FLOATFORMAT, (Long) value); 
	}
}
