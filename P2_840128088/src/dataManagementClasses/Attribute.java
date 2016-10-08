package dataManagementClasses;

import generalUtilities.DataUtils;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 
 * An instance of this represents an attribute as per the specifications of this project. 
 * @author J.A. Sanchez Perez
 *
 */
public class Attribute {
	private String name; 
	private int tIndex;         // id of the attribute's data type
	
	/**
	 *  Creates an instance of this with the given name and ID. 
	 * @param name the name of this attribute
	 * @param tIndex ID (associated to a data type)
	 */
	public Attribute(String name, int tIndex) 
	{ 
		this.name = name; 
		this.tIndex = tIndex; 
	}

	/**
	 * Creates an instance of Attribute from the specified file. It is assumed that the file pointer location 
	 * is located where this attribute data starts. The first byte specifies the data type (ID), the second
	 * specifies the length of this attribute's name and the next bytes contains the actual name of this attribute
	 * char by char.
	 * @param file
	 * @throws IOException
	 */
	public Attribute(RandomAccessFile file) throws IOException { 
		// reads the data to form this attribute from the RAF file; 
		// beginning reading from the current file pointer location
		// assumed to be located at the byte corresponding to the
		// data type for the attribute. 
		tIndex = file.readByte(); 
		name = ""; 
		int nLength = file.readByte(); 
		
		// read the name, one character at the time
		for (int i=0; i<nLength; i++) 
			name = name + file.readChar(); 	
	}
	

	/**
	 * Gets the name of this Attribute.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the ID (associated to a data type) of this attribute.
	 * @return the ID.
	 */
	public int gettIndex() {
		return tIndex;
	}

	/**
	 * Writes this attribute's properties to file.
	 * @param file Random Acces file
	 * @throws IOException if an I/O error occurs
	 */
	public void writeToFile(RandomAccessFile file) throws IOException { 
		file.writeByte((byte) tIndex);
		file.writeByte((byte) name.length()); 
		file.writeChars(name);
	}
		
	/**
	 * Gets the size of the data this attribute holds. 
	 * @return size
	 */
	public int getDataSize() { 
		return DataUtils.getTypeSize(tIndex); 
	}
	
	
	public String toString() { 
		String s="(" + this.name + ", ";
		
		s = s + DataUtils.getTypeName(this.tIndex) + ")"; 
		return s; 
	}
}
