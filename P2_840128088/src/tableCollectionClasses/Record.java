package tableCollectionClasses;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import dataManagementClasses.AttributeInSchema;
import dataManagementClasses.TableSchema;

/**
 * Defines an object to represent Record as specified in the project description.
 * @author J.A. Sanchez Perez
 *
 */
public class Record {
	private byte[] data; 
	private TableSchema ts; 
	
	/**
	 * Creates a new instance of this base on a table schema.
	 * @param ts the table schema
	 */
	public Record(TableSchema ts) { 
		data = new byte[ts.getDataRecordLength()]; 
		this.ts = ts; 
	}
	
	/**
	 * Read the data value corresponding to the selected attribute
	 * @param attrNumber index of the selected attribute in schema.. 0, 1, ...
	 * @return the data value that is extracted from the internal array of bytes
	 */
	public Object readData(int attrNumber) { 
		AttributeInSchema ais = ts.getAttr(attrNumber); 
		return ais.readDataValueFromArrayOfBytes(data, ais.getDataOffset()); 
	}

	/**
	 * Writes a data value as a sequence of bytes in the array data[]. 
	 * @param attrNumber index of the selected attribute the value corresponds to
	 * @param value the value to be written
	 */
	public void writeData(int attrNumber, Object value) { 
		AttributeInSchema ais = ts.getAttr(attrNumber); 
		ais.writeDataValueToArrayOfBytes(data, ais.getDataOffset(), value);
	}
	
	/**
	 * Reads record from the given file -- a sequence of bytes beginning at current file pointer
	 * @param file
	 * @throws IOException
	 */
	public void readFromFile(RandomAccessFile file) throws IOException { 
		// 
		file.read(data);
	}
	
	/**
	 * Reads data from user from an input scanner. 
	 * @param input the input scanner
	 */
	public void readDataRecordFromUser(Scanner input) {
		for (int i=0; i<ts.getNumberOfAttrs(); i++) { 
			
			AttributeInSchema ais = ts.getAttr(i); 

			Object value = null; 
			do { 
			  System.out.print("  " + ais + ": ");
			  value = ais.readDataValueFromInputScanner(input); 
			} while (value == null); 
			
			writeData(i, value);
		}
	}

	/**
	 * Write this record to a RandomAccessFile (RAF).
	 * @param file the RAF
	 * @throws IOException if an I/O error occurs.
	 */
	public void writeToFile(RandomAccessFile file) throws IOException { 
		file.write(data);
	}

	
	public String toString() { 
		String s = "|"; 
		int nAttrs = ts.getNumberOfAttrs();
		for (int a=0; a<nAttrs; a++) { 
			AttributeInSchema ais = ts.getAttr(a);
			s += ais.toString(readData(a)) 
					+ (a == nAttrs-1? " |" : "");

		}
		return s; 
	}

	
}
