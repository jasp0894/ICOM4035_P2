/**
 * 
 */
package tableCollectionClasses;

import java.util.ArrayList;

/**
 * An object of this represents a tuple of n values. These values are of type ValueInTuple.
 * @author J.A. Sanchez Perez
 *
 */
public class Tuple {
	private ArrayList<ValueInTuple> values;
	private int size;

	/**
	 * Creates an instance of this.
	 */
	public Tuple() {
		// TODO Auto-generated constructor stub
		values = new ArrayList<>();
		size=0;
	}
	
	/**
	 * Add new value to this Tuple.
	 * @param value the value to add.
	 */
	public void addValue(ValueInTuple value){
		values.add(value);
	}
	
	/**
	 * Gets the value at index.
	 * @param index the index.
	 * @return reference of the value at index.
	 */
	public ValueInTuple getValue(int index){
		return 	values.get(index);	
	}
	
	
	/**
	 * Return the size.
	 * @return the size
	 */
	public int size(){
		return this.size;
	}
	
	
	public boolean equals(Object other){
		return false;
	}
	
	public String toString(){
		return null;
	}
}
