package interfaces;


/**
 *
 * Specifies a new data type to write data in a particular array of bytes. 
 * @author Pedro I. Rivera Vega
 *
 */
public interface DataWriter {
	
	/**
	 * Writes data value of a particular data type into an array of bytes. 
	 * the bytes corresponding to the value being written are going to 
	 * be written at bytes a[starting], a[starting+1], ...
	 * @param a the array
	 * @param starting index of initial byte to be occupied in the array
	 * @param value the value to be written into the array. 
	 */
	void writeDataToArrayOfBytes(byte[] a, int starting, Object value);
	
	/**
	 * Specializes in converting a particular value of certain data type 
	 * into a string. 
	 * @param value the value to convert
	 * @return the resulting string
	 */
	String toString(Object value); 
}
