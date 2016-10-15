package tableCollectionClasses;

import dataManagementClasses.AttributeInSchema;

/**
 * 
 * This class defines a data type to represent a value in a tuple. A ValueInTuple has a value and the attribute 
 * associated to it. 
 * @author J.A. Sanchez Perez
 *
 */
public class ValueInTuple {

	private AttributeInSchema attr;
	private Object value;
	
	/**
	 * Creates an instance of this with given attribute and value.
	 * @param attr the attribute.
	 * @param value the value
	 */
	public ValueInTuple(AttributeInSchema attr, Object value) {
		// TODO Auto-generated constructor stub
		
		this.attr = attr;
		this.value = value;
	}
	
	
	
	/**
	 * Gets the attribute of this.
	 * @return the attr the attribute.
	 */
	public AttributeInSchema getAttr() {
		return attr;
	}


	/**
	 * Gets the value of this.
	 * @return the value the value.
	 */
	public Object getValue() {
		return value;
	}


	public boolean equals(Object other){
		return false;
	}
	

}
