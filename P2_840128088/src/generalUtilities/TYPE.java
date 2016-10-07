package generalUtilities;

import interfaces.DataReader;
import interfaces.DataWriter;

/**
 * 
 * @author PIR
 *
 */
public class TYPE { 
	private String name; 
	private int size;    // number of bytes
	private DataReader dataReader;  // data reader for this data type
	private DataWriter dataWriter;  // data writer for this data type
	public TYPE(String name, int size, DataReader dr, DataWriter dw) { 
		this.name = name; 
		this.size = size; 
		this.dataReader = dr; 
		this.dataWriter = dw; 
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * 
	 * @return
	 */
	public DataReader getDataReader() {
		return dataReader;
	}
	
	/**
	 * 
	 * @param dr
	 */
	public void setDataReader(DataReader dr) {
		this.dataReader = dr;
	}
	/**
	 * 
	 * @return
	 */
	public DataWriter getDataWriter() {
		return dataWriter;
	}
	
	/**
	 * 
	 * @param dw
	 */
	public void setDataWriter(DataWriter dw) {
		this.dataWriter = dw;
	}
	
}