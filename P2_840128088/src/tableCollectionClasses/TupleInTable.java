/**
 * 
 */
package tableCollectionClasses;

/**
 * An object of this is a specialized version of Tuple which now holds the occurrence of this tuple in Table.
 * @author J.A. Sanchez Perez
 *
 */
public class TupleInTable extends Tuple {

	private int ocurrence;
	private double percentageInTable;
	/**
	 * Creates a new instance of this.
	 */
	public TupleInTable() {
		// TODO Auto-generated constructor stub
		super();
		this.ocurrence=1;
	}
	
	/**
	 * Get the occurrence of this.
	 * @return the occurrence
	 */
	public int getOcurrence() {
		return ocurrence;
	}
	
	/**
	 * Sets the percentage in table of this.
	 * @param perc the percentage
	 */
	public void setPercentage(double perc){
		this.percentageInTable = perc;
	}
	
	/**
	 * Gets the percentage in table of this.
	 * @return percentage.
	 */
	public double getPercentageInTable(){
		return this.percentageInTable;
	}
	/**
	 * Increase the occurrence of this by one.
	 */
	public void increasOcurrenceByOne() {
		this.ocurrence++;
	}
	
	

}
