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
	 * Get the ocurrence of this.
	 * @return the ocurrence
	 */
	public int getOcurrence() {
		return ocurrence;
	}
	/**
	 * Sets the ocurrence of this.
	 * @param ocurrence the ocurrence to set
	 */
	public void setOcurrence(int ocurrence) {
		this.ocurrence = ocurrence;
	}
	
	

}