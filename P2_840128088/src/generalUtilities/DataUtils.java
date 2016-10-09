package generalUtilities;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import dataManagementClasses.Attribute;
import dataManagementClasses.AttributeInSchema;
import dataManagementClasses.BooleanDataReader;
import dataManagementClasses.BooleanDataWriter;
import dataManagementClasses.ByteDataReader;
import dataManagementClasses.ByteDataWriter;
import dataManagementClasses.CharDataReader;
import dataManagementClasses.CharDataWriter;
import dataManagementClasses.Date;
import dataManagementClasses.DateDataReader;
import dataManagementClasses.DateDataWriter;
import dataManagementClasses.DoubleDataReader;
import dataManagementClasses.DoubleDataWriter;
import dataManagementClasses.FloatDataReader;
import dataManagementClasses.FloatDataWriter;
import dataManagementClasses.IntDataReader;
import dataManagementClasses.IntDataWriter;
import dataManagementClasses.LongDataReader;
import dataManagementClasses.LongDataWriter;
import dataManagementClasses.ShortDataReader;
import dataManagementClasses.ShortDataWriter;
import dataManagementClasses.TableSchema;
import interfaces.DataReader;
import interfaces.DataWriter;
import tableCollectionClasses.Record;
import tableCollectionClasses.Table;

/**
 * This class provides a set of utilities or tools to aid in the execution of
 * certain program' operation execution.
 * 
 * @author J.A. Sanchez Perez
 */
public class DataUtils {

	public static int VALUEWIDE = 12;
	public static String STRINGFORMAT = "%" + VALUEWIDE + "s";
	public static String CHARFORMAT = "%" + VALUEWIDE + "c";
	public static String INTEGERFORMAT = "%" + VALUEWIDE + "d";
	public static String BOOLEANFORMAT = "%" + VALUEWIDE + "s";
	public static String FLOATFORMAT = "%" + VALUEWIDE + ".2f";
	public static String DATEFORMAT = "%3s/%02d/%4d";

	public static final TYPE[] TYPEList = {
			new TYPE("byte", Byte.BYTES, ByteDataReader.INSTANCE, ByteDataWriter.INSTANCE),
			new TYPE("char", Character.BYTES, CharDataReader.INSTANCE, CharDataWriter.INSTANCE),
			new TYPE("short", Short.BYTES, ShortDataReader.INSTANCE, ShortDataWriter.INSTANCE),
			new TYPE("int", Integer.BYTES, IntDataReader.INSTANCE, IntDataWriter.INSTANCE),
			new TYPE("long", Long.BYTES, LongDataReader.INSTANCE, LongDataWriter.INSTANCE),
			new TYPE("float", Float.BYTES, FloatDataReader.INSTANCE, FloatDataWriter.INSTANCE),
			new TYPE("double", Double.BYTES, DoubleDataReader.INSTANCE, DoubleDataWriter.INSTANCE),
			new TYPE("boolean", 1, BooleanDataReader.INSTANCE, BooleanDataWriter.INSTANCE),
			new TYPE("date", 4, DateDataReader.INSTANCE, DateDataWriter.INSTANCE) };

	/**
	 * Gets the ID associated to the data type with the given tName.
	 * 
	 * @param tName
	 *            The name of the data type
	 * @return the associated ID if exists, if not returns -1.
	 */
	public static int getTypeID(String tName) {
		for (int i = 0; i < TYPEList.length; i++)
			if (TYPEList[i].getName().equalsIgnoreCase(tName))
				return i;
		return -1;
	}

	/**
	 * Gets the name of the data type with ID specified by tIndex.
	 * 
	 * @param tIndex
	 *            data type ID
	 * @return name of the data type
	 * @throws IllegalArgumentException
	 *             if index cannot be associated to a valid data type for this
	 *             project.
	 */
	public static String getTypeName(int tIndex) throws IllegalArgumentException {
		if (tIndex < 0 || tIndex > TYPEList.length)
			throw new IllegalArgumentException("Invalid index for a valid data type.");
		return TYPEList[tIndex].getName();
	}

	/**
	 * Gets the size of the data type whose type name is given by tName.
	 * 
	 * @param tName
	 *            name of the data type
	 * @return size of the data type if tName represents a valid data type, if
	 *         not returns -1
	 */
	public static int getTypeSize(String tName) {
		for (int i = 0; i < TYPEList.length; i++)
			if (TYPEList[i].getName().equals(tName))
				return TYPEList[i].getSize();
		return -1;
	}

	/**
	 * Gets the size of the data type whose associated ID is given by index.
	 * 
	 * @param index
	 *            the associated ID of the data type
	 * @return size of the data type.
	 */
	public static int getTypeSize(int index) {
		if (index < 0 || index > TYPEList.length)
			throw new IllegalArgumentException("Invalid index for a valid data type.");
		return TYPEList[index].getSize();
	}

	/**
	 * Gets the data type's associated DataReader whose ID is given by index.
	 * 
	 * @param index
	 *            data type's associated ID
	 * @return the DataReader
	 */
	public static DataReader getTypeDataReader(int index) {
		if (index < 0 || index > TYPEList.length)
			throw new IllegalArgumentException("Invalid index for a valid data type.");
		return TYPEList[index].getDataReader();
	}

	/**
	 * Gets the data type's associated DataWriter whose ID is given by index.
	 * 
	 * @param index
	 *            data type's associated ID
	 * @return the DataWriter
	 */
	public static DataWriter getTypeDataWriter(int index) {
		if (index < 0 || index > TYPEList.length)
			throw new IllegalArgumentException("Invalid index for a valid data type.");
		return TYPEList[index].getDataWriter();

	}

	/**
	 * Determines if the given type name represents an actual data type of this
	 * project.
	 * 
	 * @param tName
	 *            the name of the data type
	 * @return true if valid, false otherwise.
	 */
	public static boolean isValidDataType(String tName) {
		return getTypeID(tName) != -1;
	}

	/**
	 * Checks if the given name is valid according to the project rules.
	 * 
	 * @param s
	 *            the name
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidName(String s) {
		s = s.trim();
		if (s.length() == 0 || s.length() > 256)
			return false; // the length of s is restricted to 2^8 = 256
							// since a byte is used to store it in memory
		if (!Character.isLetter(s.charAt(0)))
			return false;
		for (int i = 1; i < s.length(); i++)
			if (!Character.isLetter(s.charAt(i)) && !Character.isDigit(s.charAt(i)) && s.charAt(i) != '_')
				return false;

		// if it reaches here, then it is a valid name
		return true;
	}

	/**
	 * Read an attribute name from an input scanner.
	 * 
	 * @param input
	 *            the input scanner
	 * @return reference to attribute name.
	 */
	public static String readAttributeNameFromInput(Scanner input) {
		String answer;

		System.out.print("\tEnter the name of the attribute: ");

		// read answer
		answer = input.nextLine();

		while (!DataUtils.isValidName(answer)) {
			System.out.print("\n****Invalid name. Enter a valid name of the attribute: ");
			answer = input.nextLine();
		}
		return answer;
	}

	/**
	 * Read an attribute name from an input scanner.
	 * 
	 * @param input
	 *            the input scanner
	 * @return reference to attribute name.
	 */
	public static String readAttributeTypeFromInput(Scanner input) {
		String answer;

		System.out
				.print("\tEnter type for the attribute {byte, boolean, char, short, int, float,double, long, date}: ");
		// read answer
		answer = input.nextLine();

		// keep asking if invalid data type
		while (!DataUtils.isValidDataType(answer)) {
			System.out.print(
					"\n****Invalid type. Enter a valid type for the attribute {byte, boolean, char, short, int, float,double, long, date}: \n");
			answer = input.nextLine();
		}

		return answer;

	}

	/**
	 * Get and validate answer to Y/N question from input scanner.
	 * 
	 * @param input
	 *            the scanner
	 * @return the answer.
	 */
	public static String getAndValidateAnswerToQuestionFrom(Scanner input) {
		String answer = input.nextLine();

		while (!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n"))) {
			System.out.print("\n****Incorrect answer. Do you want to add a new attribute? Y_/N_\n");

			// read answer again
			answer = input.nextLine();
		}

		return answer;
	}

	/**
	 * Request or read from input the data from a table schema to create a
	 * Record.
	 * 
	 * @param ts
	 *            the table schema
	 * @param input
	 *            the input scanner
	 * @return a reference to a record constructed based upon ts.
	 */
	public static Record requestDataForRecord(TableSchema ts, Scanner input) {
		Record r = new Record(ts);
		for (int i = 0; i < ts.getNumberOfAttrs(); i++) {
			AttributeInSchema ais = ts.getAttr(i);

			System.out.print("\tEnter value for the following attribute " + ((Attribute) ais).toString() + ": ");

			// read answer
			Object value = ais.readDataValueFromInputScanner(input);
			while (value == null) {
				System.out.print("\n****Invalid value. Enter a valid value for the following attribute "
						+ ((Attribute) ais).toString() + ": \n");
				value = ais.readDataValueFromInputScanner(input);
			}

			// At this point, value should be valid and we can write it
			// in the record.
			r.writeData(i, value);

		}

		return r;
	}

	/**
	 * Determines if the given String represents a valid Int.
	 * 
	 * @param s
	 *            the string
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Determines if the given String represents a valid Boolean.
	 * 
	 * @param s
	 *            the string
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidBoolean(String s) {
		try {
			Boolean.parseBoolean(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Determines if the given String represents a valid Long.
	 * 
	 * @param s
	 *            the string
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidLong(String s) {
		try {
			Long.parseLong(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Determines if the given String represents a valid Short.
	 * 
	 * @param s
	 *            the string
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidShort(String s) {
		try {
			Short.parseShort(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Determines if the given String represents a valid Char.
	 * 
	 * @param s
	 *            the string
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidChar(String s) {
		return s.length() == 1;
	}

	/**
	 * Determines if the given String represents a valid Byte.
	 * 
	 * @param s
	 *            the string
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidByte(String s) {
		try {
			Byte.parseByte(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Determines if the given String represents a valid Float.
	 * 
	 * @param s
	 *            the string
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidFloat(String s) {
		try {
			Float.parseFloat(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Determines if the given String represents a valid Double.
	 * 
	 * @param s
	 *            the string
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Determines if the given String represents a valid Date.
	 * 
	 * @param month
	 *            a byte representing the month
	 * @param day
	 *            a byte representing the day
	 * @param year
	 *            a short representing the year
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidDate(byte month, byte day, short year) {
		if (year < 0)
			return false;
		if (month < 1 || month > 12)
			return false;

		int maxDays;
		if (month == 2 && year % 4 == 0) // month February on a leap year....
			maxDays = 29;
		else
			maxDays = Date.nDays(month - 1);
		if (day < 1 || day > maxDays)
			return false;

		// if it reaches here, then the date is valid as per the specs given
		return true;
	}

	/**
	 * Verify if the given file complies with this project specification for a
	 * file.
	 * 
	 * @param raf
	 *            the random access file.
	 * @return a table with the content of the raf if valid, null otherwise.
	 */
	public static Table isValidProjectFile(RandomAccessFile raf) {
		int dOffset = 0;
		Table t;

		try {
			if (raf.length() < 2)
				return null;

			Short nOfAttrs = raf.readShort(); // number of attributes as per
												// specification

			// create a table schema with the given
			TableSchema ts = TableSchema.getInstance(nOfAttrs);

			// try to create the number of attributes given by nOfAttrs
			for (int i = 0; i < nOfAttrs; i++) {

				AttributeInSchema ais = new AttributeInSchema(raf, dOffset);
				try {
					dOffset += ais.getDataSize();
				} catch (IllegalArgumentException e) {
					return null; // the read attribute does not contain a valid
									// ID.
				}

				if (!DataUtils.isValidName(ais.getName()))
					return null; // was not a valid name

				// at this point, the read attribute should be valid since it
				// contains a valid ID and name
				// Therefore it can be added to the schema.
				ts.addAttribute(ais);
			}

			t = new Table(ts); // initialize the table with the table schema
			// check if all file content has been read and therefore no records
			// are given.
			// If so, the table file would be valid.
			if ((raf.length() - raf.getFilePointer()) == 1)
				return t;

			// check if rest of the file contain data records, and if it does,
			// then check if
			// they are of the expected length determined by the data types of
			// all attributes.
			if (((raf.length() - raf.getFilePointer()) % ts.getDataRecordLength()) != 0)
				return null;

			// at this point we know that the file has data records of the
			// expected length.
			// we need to check if their content is valid
			// get the number of data records in file
			long numberOfDataRecords = (raf.length() - raf.getFilePointer()) / ts.getDataRecordLength();
			long dataRecordsStartIndex = raf.getFilePointer();

			// read the rest of the file in the form of records.
			t.readTableDataFromFile(raf);

			// check if their content is valid record by record
			raf.seek(dataRecordsStartIndex);// set the file pointer at the
											// starting position of all data
											// records
			for (int i = 0; i < numberOfDataRecords; i++) {
				// create a copy of an existing record
				Record r = t.getRecord(i);
				// read record content from the file. Even though the record
				// already has its data, this way we can test its content.
				r.readFromFile(raf);
				// try to read the values contained in the record. One value for
				// each attribute
				for (int i1 = 0; i1 < ts.getNumberOfAttrs(); i1++) {
					Object value = r.readData(i1); // the value is read from the
													// array of bytes of this
													// record. be careful with
													// long

					//check if the data could not be converted to any of the project's data types
					if (value == null)
						return null;

					//double check for some
					value = ts.getAttr(i1).readDataValueFromInputScanner(new Scanner(value.toString())); // try
																											// to
																											// read
																											// the
																											// same
					// value converted to string from a scanner. If null, then
					// could not parse the value and therefore is not valid.

					if (value == null)
						return null; 

				}
			}

		}
		// given file does not comply with specification
		catch (EOFException e) {
			return null;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}

		return t;// if it reached this point, it must be valid

	}

}
