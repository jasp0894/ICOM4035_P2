package dataManagementClasses;

import interfaces.DataWriter;

/**
 * Defines a data writer for Data data types.
 * @author J.A. Sanchez Perez
 *
 */
public class DateDataWriter implements DataWriter {

	public static final DateDataWriter INSTANCE = new DateDataWriter(); 
	
	//singleton
	private DateDataWriter() {}; 

	/**
	 * Write Date data type to array of bytes.
	 */
	public void writeDataToArrayOfBytes(byte[] b, int index, Object  rd) {
		Date d = (Date) rd; 
		ByteDataWriter.INSTANCE.writeDataToArrayOfBytes(b, index, d.getMonth());
		ByteDataWriter.INSTANCE.writeDataToArrayOfBytes(b, index+1, d.getDay());
	    ShortDataWriter.INSTANCE.writeDataToArrayOfBytes(b, index+2, d.getYear()); 
	}

	@Override
	public String toString(Object value) {
		Date dValue = (Date) value; 
		return " " + dValue.toString(); 
	}
}
