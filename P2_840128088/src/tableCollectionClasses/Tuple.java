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
		if( !(other instanceof Tuple)) return false;
		Tuple tuple = (Tuple) other;
		//check sizes
		if(this.size != tuple.size()) return false;
		
		int i=0;
		for(ValueInTuple val: this.values){
			//check every value inside the tuples
			if(!(val.equals(tuple.getValue(i))))
				return false;
			i++;
		}
		
		return true; //if reaches here, the tuples are the same
		
	}
	
	public String toString(){
		return null;
	}

}
