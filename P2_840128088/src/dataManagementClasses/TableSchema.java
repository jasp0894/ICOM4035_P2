package dataManagementClasses;

import generalUtilities.DataUtils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Defines an object for the schema of the table, which holds a list of attributes.
 * @author J.A. Sanchez Perez
 *
 */
public class TableSchema {
	private AttributeInSchema[] attrs;   // the attributes
	private int size;     // number of attributes added
	
	/**
	 * Create a new instance with n attributes.
	 * @param n number of attributes.
	 */
	private TableSchema(int n) { 
		attrs = new AttributeInSchema[n]; 
	}
	
	/**
	 * Add a new attribute to the list. 
	 * @param attr new attribute 
	 * @throws IllegalStateException if the table cannot hold more attributes than what it was created for.
	 */
	public void addAttribute(AttributeInSchema attr) throws IllegalStateException { 
		if (size == attrs.length)
			throw new IllegalStateException("Table of attributes is full."); 
		attrs[size++] = attr; 
	}
	
	/**
	 * Gets the number (size of the attr lists)  of attributes 
	 * @return the size
	 */
	public int getNumberOfAttrs() { 
		return size; 
	}

	/**
	 * Get the attribute at index.
	 * @param index the index of attribute
	 * @return attribute
	 * @throws IndexOutOfBoundsException if index does not represent an attribute
	 */
	public AttributeInSchema getAttr(int index) throws IndexOutOfBoundsException { 
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("getAttr: Index out of bounds: " + index); 
		return attrs[index]; 
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isValid() { 
		return false; 
	}
	
	/**
	 * Creates an instance of this class.
	 * @param n number of attributes to initiate an instance of this.
	 * @return the instance of this.
	 */
	public static TableSchema getInstance(int n) { 
		// PRE: n is a valid positive integer value
		return new TableSchema(n); 
	}
	
	/**
	 * Creates an instance of this from a RandomAccessFile (RAF).
	 * @param file the RAF
	 * @return the instance of this
	 * @throws IOException if an I/O error occurs
	 */
	public static TableSchema getInstance(RandomAccessFile file) throws IOException { 
		file.seek(0);
		short nAttrs = file.readShort(); 
		TableSchema st = new TableSchema(nAttrs); 
		int offset = 0; 
		// read attributes 
		for (int i=0; i<nAttrs; i++) { 
			AttributeInSchema ais = new AttributeInSchema(file, offset); 
			st.addAttribute(ais); 
			offset += DataUtils.getTypeSize(ais.gettIndex()); 
		}
		return st; 
	}
	
	/**
	 * Saves a tableSchema into a RAF.
	 * @param file the RAF
	 * @throws IllegalStateException if the file pointer is not at 0 at the beginning.
	 * @throws IOException if an I/O error occurs.
	 */
	public void saveSchema(RandomAccessFile file) 
			throws IllegalStateException, IOException { 
		// Saves this schema into the given RAF, beginning at its current file pointer
		// location. The file is assumed to be open and with file pointer at 0. 
		
		if (file.getFilePointer() != 0)
			throw new IllegalStateException("File pointer is not at 0."); 
		
		// first write value for the number of attributes
		file.writeShort(size);
		
		// ready to save the schema; one attribute at the time...
		for (AttributeInSchema a : attrs) 
			a.writeToFile(file);
	}
	
	/**
	 * Gets length of each data record, which holds tuples of values. 
	 * @return the length.
	 */
	public int getDataRecordLength() { 
		int len = 0; 
		
		for (AttributeInSchema ais : this.attrs) 
			len += ais.getDataSize(); 
		return len; 
	}
	
	/**
	 * Creates a table schema from a list of attributes.
	 * @param selectedAttrs determines the size and content of the new schema.
	 * @return reference to the new table schema 
	 * 
	 */
	public static TableSchema getSubschema(ArrayList<AttributeInSchema> selectedAttrs) { 
		TableSchema newSchema = new TableSchema(selectedAttrs.size()); 
		for(int i=0; i<selectedAttrs.size(); i++)
			newSchema.addAttribute(selectedAttrs.get(i));
		
		return newSchema; 
	}
	
	public String toString() { 
		String s = "|"; 
		for (int i=0; i<attrs.length-1; i++) 
			s += String.format(DataUtils.STRINGFORMAT, attrs[i].getName()); 
		s += String.format(DataUtils.STRINGFORMAT, attrs[attrs.length-1].getName())+" |";
		s += "\n"; 
		for (int i=0; i<=this.size * DataUtils.VALUEWIDE+2; i++) s+='='; 
		return s; 
	}
	
}
