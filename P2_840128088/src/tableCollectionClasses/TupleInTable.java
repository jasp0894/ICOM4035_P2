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
	/**
	 * Creates a new instance of this.
	 */
	public TupleInTable() {
		// TODO Auto-generated constructor stub
		super();
		this.ocurrence=0;
	}
	/**
	 * Get the occurrence of this.
	 * @return the occurrence
	 */
	public int getOcurrence() {
		return ocurrence;
	}
	/**
	 * Increase the occurrence of this by one.
	 */
	public void increasOcurrenceByOne() {
		this.ocurrence++;
	}
	
	

}
