package tableCollectionClasses;

import org.w3c.dom.Attr;

import dataManagementClasses.AttributeInSchema;

public class ValueInTuple {

	private AttributeInSchema attr;
	private Object value;
	
	public ValueInTuple(AttributeInSchema attr, Object value) {
		// TODO Auto-generated constructor stub
		
		this.attr = attr;
		this.value = value;
	}
	
	
	
	/**
	 * @return the attr
	 */
	public AttributeInSchema getAttr() {
		return attr;
	}


	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}


	public boolean equals(Object other){
		return false;
	}
	

}
