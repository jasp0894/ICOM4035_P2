package dataManagementClasses;

import generalUtilities.DataUtils;

/**
 * Date defines a data type to hold the date in format month, day, year.
 * @author J.A. Sanchez Perez
 *
 */
public class Date implements Comparable<Date> {
	private static int NDAYSPERMONTH[] = {31, 28, 31, 30, 31, 30, 
		                                  31, 31, 30, 31, 30, 31}; 
	private static String MONTHNAME[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", 
		                                 "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}; 
	private byte month, day; 
	private short year; 
	
	
	/**
	 * Creates an instance of this with given month, day and year.
	 * @param month the month
	 * @param day the day
	 * @param year the year 
	 * @throws InvalidDateException if given date is not valid as per the specs of this project.
	 */
	public Date(byte month, byte day, short year) throws InvalidDateException { 
		if (!DataUtils.isValidDate(month, day, year))
			throw new InvalidDateException("Given date is not valid."); 
		this.month = month; 
		this.day = day; 
		this.year = year; 
		
	}
	/**
	 * Gets the month of this.
	 * @return month
	 */
	public byte getMonth() {
		return month;
	}
	
	/**
	 * Gets the day of this.
	 * @return the day
	 */
	public byte getDay() {
		return day;
	}
	
	/**
	 * Gets the year of this. 
	 * @return the year
	 */
	public short getYear() {
		return year;
	}

	public String toString() { 
		return String.format(DataUtils.DATEFORMAT, MONTHNAME[month-1], day, year); 
	}
	
	/**
	 * Gets the numbers of days for a given month.
	 * @param month the month
	 * @return number of days in the month.
	 */
	public static int nDays(int month) { 
		return NDAYSPERMONTH[month]; 
	}
	
	public boolean equals(Object other) { 
		if (other == null) return false; 
		if (!(other instanceof Date)) return false; //check if the given child of Object is not of type Date
		Date otherDate = (Date) other; 
		return this.month == otherDate.month &&
				this.day == otherDate.day &&
				this.year == otherDate.year; 
	}
	
	public int compareTo(Date other) { 
		if (this.year < other.year) return -1; 
		if (this.year > other.year) return 1; 
		if (this.month < other.month) return -1; 
		if (this.month > other.month) return 1; 
		if (this.day < other.day) return -1;
		if (this.day > other.day) return 1; 
		// if this point is reached, then they are equal
		return 0; 
	}
}
