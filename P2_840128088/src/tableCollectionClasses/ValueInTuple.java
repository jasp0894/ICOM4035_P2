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
		if(!(other instanceof ValueInTuple)) return false;
		
		//compare values. All data types managed in this project implements the equals method.
		//Since we are going to compare values of the same attribute we don't need to check the attributes.
		
		return this.value.equals(((ValueInTuple)other).getValue());
		
	}
	
	public String toString(){
		return attr.toString(value);
	}
	

}
