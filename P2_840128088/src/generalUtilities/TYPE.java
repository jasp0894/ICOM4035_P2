package generalUtilities;

import interfaces.DataReader;
import interfaces.DataWriter;

/**
 * An object of this class contains the attributes that describe the different data types
 * used in this project. A Data Reader and Data Writer is associated with each object. 
 * @author PIR
 *
 */
public class TYPE { 
	private String name; 
	private int size;    // number of bytes
	private DataReader dataReader;  // data reader for this data type
	private DataWriter dataWriter;  // data writer for this data type
	
	/**
	 * 
	 * Creates an instance of TYPE that contains name, size (number of bytes that it occupies in memory,
	 * a DataReader and a DataWriter
	 * @param name name of this type
	 * @param size memory size
	 * @param dr associated DataReader
	 * @param dw associated DataWriter
	 */
	public TYPE(String name, int size, DataReader dr, DataWriter dw) { 
		this.name = name; 
		this.size = size; 
		this.dataReader = dr; 
		this.dataWriter = dw; 
	}
	
	/**
	 *  Gets the name of this.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the size of this.
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Gets DataReader associated to this.
	 * @return the DataReader
	 */
	public DataReader getDataReader() {
		return dataReader;
	}
	
	/**
	 * Sets the DataReader.
	 * @param dr DataReader to set
	 */
	public void setDataReader(DataReader dr) {
		this.dataReader = dr;
	}
	/**
	 * Gets the associated DataWriter.
	 * @return the data datawriter
	 */
	public DataWriter getDataWriter() {
		return dataWriter;
	}
	
	/**
	 * Sets the DataWriter.
	 * @param dw DataWriter to set
	 */
	public void setDataWriter(DataWriter dw) {
		this.dataWriter = dw;
	}
	
}