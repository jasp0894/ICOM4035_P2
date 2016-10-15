/**
 * 
 */
package tableCollectionClasses;

import java.util.ArrayList;

/**
 * @author J.A. Sanchez Perez
 *
 */
public class Tuple {
	private ArrayList<ValueInTuple> values;
	private int size;

	/**
	 * 
	 */
	public Tuple() {
		// TODO Auto-generated constructor stub
		values = new ArrayList<>();
		size  =0;
	}
	
	public void addValue(ValueInTuple value){
		values.add(value);
	}
	public ValueInTuple getValue(int index){
		return 	values.get(index);	
	}
	public boolean equals(Object other){
		return false;
	}
	
	public String toString(){
		return null;
	}

}
