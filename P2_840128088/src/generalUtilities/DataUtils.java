package generalUtilities;

import java.io.File;

import dataManagementClasses.*;
import interfaces.DataReader;
import interfaces.DataWriter;

/**
 * This class provides a set of utilities or tools to aid in the execution of certain
 * program' operation execution.
 * 
 * @author J.A. Sanchez Perez
 */
public class DataUtils {

	public static int VALUEWIDE = 12;
	public static String STRINGFORMAT = "%"+VALUEWIDE + "s"; 
	public static String CHARFORMAT = "%"+VALUEWIDE + "c"; 
	public static String INTEGERFORMAT = "%"+VALUEWIDE + "d";
	public static String BOOLEANFORMAT = "%"+VALUEWIDE + "s"; 
	public static String FLOATFORMAT = "%"+VALUEWIDE + ".2f"; 
	public static String DATEFORMAT ="%3s/%02d/%4d"; 
	
	public static final TYPE[] TYPEList = {new TYPE("byte", Byte.BYTES, ByteDataReader.INSTANCE, ByteDataWriter.INSTANCE), 
										 new TYPE("char", Character.BYTES, CharDataReader.INSTANCE, CharDataWriter.INSTANCE), 
		                                 new TYPE("short", Short.BYTES, ShortDataReader.INSTANCE, ShortDataWriter.INSTANCE), 
		                                 new TYPE("int", Integer.BYTES, IntDataReader.INSTANCE, IntDataWriter.INSTANCE),
			                             new TYPE("long", Long.BYTES, LongDataReader.INSTANCE, LongDataWriter.INSTANCE), 
			                             new TYPE("float", Float.BYTES, FloatDataReader.INSTANCE, FloatDataWriter.INSTANCE), 
			                             new TYPE("double", Double.BYTES, DoubleDataReader.INSTANCE, DoubleDataWriter.INSTANCE), 
			                             new TYPE("boolean", 1, BooleanDataReader.INSTANCE, BooleanDataWriter.INSTANCE), 
			                             new TYPE("date", 4, DateDataReader.INSTANCE, DateDataWriter.INSTANCE)}; 
	/**
	 * Gets the ID associated to the data type with the given tName. 
	 * @param tName The name of the data type
	 * @return the associated ID if exists, if not returns -1.
	 */
	public static int getTypeID(String tName) { 
		for (int i=0; i<TYPEList.length; i++) 
			if (TYPEList[i].getName().equalsIgnoreCase(tName)) 
				return i; 
		return -1; 
	}
	
	
	/**
	 * Gets the name of the data type with ID specified by tIndex.
	 * @param tIndex data type ID
	 * @return name of the data type
	 * @throws IllegalArgumentException if index cannot be associated to a valid data type for this project.
	 */
	public static String getTypeName(int tIndex) throws IllegalArgumentException { 
		if (tIndex<0 || tIndex > TYPEList.length)
			throw new IllegalArgumentException("Invalid index for a valid data type."); 
		return TYPEList[tIndex].getName(); 
	}
	
	/**
	 * Gets the size of the data type whose type name is given by tName.
	 * @param tName name of the data type
	 * @return size of the data type if tName represents a valid data type, if not returns -1
	 */
	public static int getTypeSize(String tName) { 
		for (int i=0; i<TYPEList.length; i++) 
			if (TYPEList[i].getName().equals(tName)) 
				return TYPEList[i].getSize(); 
		return -1; 
	}

	
	/**
	 * Gets the size of the data type whose associated ID is given by index.
	 * @param index the associated ID of the data type
	 * @return size of the data type. 
	 */
	public static int getTypeSize(int index) { 
		if (index<0 || index > TYPEList.length)
			throw new IllegalArgumentException("Invalid index for a valid data type."); 
		return TYPEList[index].getSize(); 
	}

	/**
	 * Gets the data type's associated DataReader whose ID is given by index.
	 * @param index data type's associated ID
	 * @return the DataReader
	 */
	public static DataReader getTypeDataReader(int index) { 
		if (index<0 || index > TYPEList.length)
			throw new IllegalArgumentException("Invalid index for a valid data type."); 
		return TYPEList[index].getDataReader(); 		
	}
	
	/**
	 * Gets the data type's associated DataWriter whose ID is given by index.
	 * @param index data type's associated ID
	 * @return the DataWriter
	 */
	public static DataWriter getTypeDataWriter(int index) { 
		if (index<0 || index > TYPEList.length)
			throw new IllegalArgumentException("Invalid index for a valid data type."); 
		return TYPEList[index].getDataWriter(); 
		
	}
	
	/**
	 * Determines if the given type name represents an actual data type of this project.
	 * @param tName the name of the data type
	 * @return true if valid, false otherwise.
	 */
	public static boolean isValidDataType(String tName) { 
		return getTypeID(tName) != -1; 
	}
	
	/**
	 * Checks if the given name is valid according to the project rules. 
	 * @param s the name
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidName(String s) { 
		s = s.trim(); 
		if (s.length()==0 || s.length()>256) return false; //the length of s is restricted to 2^8 = 256
														//since a byte is used to store it in memory
		if (!Character.isLetter(s.charAt(0))) return false; 
		for (int i=1; i<s.length(); i++) 
			if (!Character.isLetter(s.charAt(i)) &&
				!Character.isDigit(s.charAt(i)) &&
				s.charAt(i) != '_') 
			   return false; 
		
		// if it reaches here, then it is a valid name
		return true; 
	}
	
	/**
	 * Determines if the given String represents a valid Int.
	 * @param s the string
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidInt(String s) { 
		try { 
			Integer.parseInt(s); 
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}
	/**
	 * Determines if the given String represents a valid Boolean.
	 * @param s the string
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidBoolean(String s) {
		try {
			Boolean.parseBoolean(s); 
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}
	
	/**
	 * Determines if the given String represents a valid Long.
	 * @param s the string
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidLong(String s) {
		try {
			Long.parseLong(s); 
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}
	
	/**
	 * Determines if the given String represents a valid Short.
	 * @param s the string
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidShort(String s) {
		try {
			Short.parseShort(s);
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}

	/**
	 * Determines if the given String represents a valid Char.
	 * @param s the string
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidChar(String s) {
		return s.length() == 1; 
	}

	/**
	 * Determines if the given String represents a valid Byte.
	 * @param s the string
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidByte(String s) {
		try {
			Byte.parseByte(s); 
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}

	/**
	 * Determines if the given String represents a valid Float.
	 * @param s the string
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidFloat(String s) {
		try {
			Float.parseFloat(s); 
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}
	
	/**
	 * Determines if the given String represents a valid Double.
	 * @param s the string
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidDouble(String s) {
		try {
			Double.parseDouble(s); 
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}


	/**
	 * Determines if the given String represents a valid Date. 
	 * @param month a byte representing the month
	 * @param day a byte representing the day
	 * @param year a short representing the year
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidDate(byte month, byte day, short year) { 
		if (year < 0)
			return false;
		if (month < 1 || month > 12) 
			return false; 
		
		int maxDays; 
		if (month == 2 && year % 4 == 0)   // month February on a leap year....
			maxDays = 29; 
		else 
			maxDays = Date.nDays(month-1); 
		if (day < 1 || day > maxDays)
			return false; 		

		// if it reaches here, then the date is valid as per the specs given
		return true;
	}
//	
//	/**
//	 * Determines if the given file name exists in directory.
//	 * @param fname the name of the file
//	 * @return reference to the file if exists, 
//	 */
//	public static File exists(String fname){
//		
//		
//	}
}
