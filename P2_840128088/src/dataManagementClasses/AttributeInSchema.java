package dataManagementClasses;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import generalUtilities.DataUtils;
import interfaces.DataReader;
import interfaces.DataWriter;

/**
 * Specifies a new Attribute as per the specification of project's schema concept.
 * This type of attribute has DataReader and DataWriter associated to it. These readers and writers
 * will vary depending on the type of the attribute. Also, it includes a dataOffset as it belongs  to 
 * a schema.  
 * @author J.A. Sanchez Perez
 *
 */
public class AttributeInSchema extends Attribute {

	private int dataOffset;     // offset of its value in the records of table it is part of
	private DataReader dataReader; 
	private DataWriter dataWriter; 
	
	/**
	 * Creates an instance of this with given name, ID, and dataOffset in schema.
	 * @param name the name of this 
	 * @param tIndex Id of this attribute
	 * @param dataOffset the offset in schema
	 */
	public AttributeInSchema(String name, int tIndex, int dataOffset) 
	{ 
		super(name, tIndex);  
		this.dataOffset = dataOffset; 
		this.dataReader = DataUtils.getTypeDataReader(tIndex); 
		this.dataWriter = DataUtils.getTypeDataWriter(tIndex); 
	}

	/**
	 * 
	 * Creates an instance of this given a file and the data offset in schema
	 * @param file RandomAcessFile that contains the attribute
	 * @param dataOffset the data offset in schema
	 * @throws IOException if an I/O error occurs
	 */
	public AttributeInSchema(RandomAccessFile file, int dataOffset) 
			throws IOException 
	{ 
		super(file);  
		this.dataOffset = dataOffset; 
		this.dataReader = DataUtils.getTypeDataReader(super.gettIndex()); 
		this.dataWriter = DataUtils.getTypeDataWriter(super.gettIndex()); 		
	}
	
	/**
	 * Gets the data offset of this
	 * @return the data offset
	 */
	public int getDataOffset() { 
		return dataOffset; 
	}

	/** .. just for testing purposes...
	public String toString() { 
		return super.toString()+":"+dataOffset; 
	}
	**/
	
	/**
	 * Reads data value from an array of byte starting at position "starting".
	 * @param a the array of bytes
	 * @param starting initial position
	 * @return the read value
	 */
	public Object readDataValueFromArrayOfBytes(byte[] a, int starting) { 
		return dataReader.readDataFromArrayOfBytes(a, starting); 
	}
	
	/**
	 * Reads data value from an input scanner. 
	 * @param input the input scanner reference
	 * @return the read value
	 */
	public Object readDataValueFromInputScanner(Scanner input) {  
		return dataReader.readDataFromInputScanner(input); 
	}
	
	/**
	 * Write data to an array of bytes. The data starts at position "starting".
	 * @param a the array
	 * @param starting the initial position
	 * @param value the value to write
	 */
	public void writeDataValueToArrayOfBytes(byte[] a, int starting, Object value) { 
		dataWriter.writeDataToArrayOfBytes(a, starting, value);
	}
	
	public String toString(Object value) { 
		return dataWriter.toString(value); 
	}
}
