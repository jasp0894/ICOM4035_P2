package tableCollectionClasses;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import dataManagementClasses.AttributeInSchema;
import dataManagementClasses.TableSchema;

/**
 * This class is the central data type in this project. It contains a table schema that specifies the
 * number of attributes an their characteristics as well as records that specify the values for the attributes.
 * @author J.A. Sanchez Perez
 *
 */
public class Table {
	private TableSchema ts; 
	private ArrayList<Record> rList; 
	private int recordLength; 
	
	/**
	 * Create a Table with a table schema.
	 * @param ts the table schema
	 */
	public Table(TableSchema ts) { 
		this.ts = ts; 
		recordLength = ts.getDataRecordLength(); 
		rList = new ArrayList<>(); 
	}
	
	/**
	 * Gets the attribute at index.
	 * @param index the index 
	 * @return reference to attribute at index
	 */
	public AttributeInSchema getAttribute(int index) { 
		return ts.getAttr(index); 
	}
	
	/**
	 * Gets the number of attributes.
	 * @return the attributes.
	 */
	public int getNumberOfAttrs() { 
		return ts.getNumberOfAttrs(); 
	}
	
	/**
	 * Creates a new instance of Record. The instance is initialized with this table schema.
	 * @return the newly create object of Record.
	 */
	public Record getNewRecordInstance() { 
		return new Record(ts); 
	}
	
	/**
	 * Add a new record to this.
	 * @param r the record
	 */
	public void addRecord(Record r) { 
		rList.add(r); 
	}
	
	/**
	 * Get the record at index.
	 * @param index position of record
	 * @return the record
	 */
	public Record getRecord(int index) { 
		return rList.get(index); 
	}
	
	/**
	 * Get the record length for this table.
	 * @return the length
	 */
	public int getRecordLength() { 
		return recordLength; 
	}
	
	/**
	 * Displays the table schema of this table.
	 */
	public void displaySchema() { 
		System.out.println(ts); 
	}
	
	/**
	 * Display this table entirely.
	 */
	public void displayTable() { 
		this.displaySchema();
		for(Record r : rList) 
			System.out.println(r); 
	}
	
	/**
	 * Read data for the table from a RAF. 
	 * @param file the RAF
	 * @throws IOException if an IO error occurs.
	 */
	public void readTableDataFromFile(RandomAccessFile file) throws IOException {

		long numberOfDataRecords = 
				(file.length() - file.getFilePointer())/ts.getDataRecordLength(); //filepointer
		//is assumed to be just after the schema.
				
		for (int dr = 1; dr <= numberOfDataRecords; dr++) {
			Record record = getNewRecordInstance(); 
			record.readFromFile(file);
			addRecord(record); 
		}
	}
	
	/**
	 * Write this table date to a RAF.
	 * @param file the random access file
	 * @throws IOException if an IO error occurs.
	 */
	public void writeTableDataToFile(RandomAccessFile file) throws IOException {
		//file pointer is assumed to be just after the schema.
		
				for (int dr = 0; dr < rList.size(); dr++) {
					Record record = this.getRecord(dr); 
					record.writeToFile(file); 
				}
	}
	
	/**
	 * Gets the table schema of this.
	 * @return table schema
	 */
	public TableSchema getTableSchema(){
		return this.ts;
	}
/*
 	public static Table readTableDataFromFile(RandomAccessFile file) throws IOException {
 
		TableSchema ts = TableSchema.getInstance(file); 
		Table table = new Table(ts); 
		long numberOfDataRecords = 
				(file.length() - file.getFilePointer())/ts.getDataRecordLength(); 
				
		for (int dr = 1; dr <= numberOfDataRecords; dr++) {
			Record record = table.getNewRecordInstance(); 
			record.readFromFile(file);
			table.addRecord(record); 
		}
		
		return table; 
	}
*/	
}
