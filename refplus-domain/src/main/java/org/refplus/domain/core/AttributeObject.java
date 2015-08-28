package org.refplus.domain.core;

/**
 * @author Thomas Berger
 * @module AttributeObject.java
 *
 * @text   	Implements the properties of a codelist attribute
 *
 * 1.00  19-May-2015  first release
 * 1.01  28-Aug-2015  updated for RefPlus
 *
 */
public class AttributeObject {

	private int attribute_ID = 0;			// unique accross all codelists (451,452,453,...)
	
	private int attribute_number = 0;		// unique within a codelist (1,2,3,..) used for sorting
	
	private int codelist_ID = 0;

	private String attributeName = null;
	
	private boolean isHidden = false;
	
	private String restName = null;
	
	private boolean is_A_Code = false;
	
	/**
	 * valid attribute types are "CHAR", "INT" and "MULTILANG"
	 */
	private String attr_Type = null;
	
	private int attr_Length = 0;
	
	private int attr_Precision = 0;		// only valid for INT
	
	private boolean is_Unique = false;
	
	private boolean is_CanBeEmpty = true;
	
	private String validationRegEx = null;

	public String toString () { 
		return Integer.toString(attribute_number) + " - " + attributeName;
	}
	
	public int getAttribute_ID() {
		return attribute_ID;
	}

	public void setAttribute_ID(int attribute_ID) {
		this.attribute_ID = attribute_ID;
	}

	public int getCodelist_ID() {
		return codelist_ID;
	}

	public void setCodelist_ID(int codelist_ID) {
		this.codelist_ID = codelist_ID;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public String getRestName() {
		return restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	public boolean isIs_A_Code() {
		return is_A_Code;
	}

	public void setIs_A_Code(boolean is_A_Code) {
		this.is_A_Code = is_A_Code;
	}

	public String getAttr_Type() {
		return attr_Type;
	}

	public void setAttr_Type(String attr_Type) {
		this.attr_Type = attr_Type;
	}

	public int getAttr_Length() {
		return attr_Length;
	}

	public void setAttr_Length(int attr_Length) {
		this.attr_Length = attr_Length;
	}

	public int getAttr_Precision() {
		return attr_Precision;
	}

	public void setAttr_Precision(int attr_Precision) {
		this.attr_Precision = attr_Precision;
	}

	public boolean isIs_Unique() {
		return is_Unique;
	}

	public void setIs_Unique(boolean is_Unique) {
		this.is_Unique = is_Unique;
	}

	public int getAttribute_number() {
		return attribute_number;
	}

	public void setAttribute_number(int attribute_number) {
		this.attribute_number = attribute_number;
	}

	public String getValidationRegEx() {
		return validationRegEx;
	}

	public void setValidationRegEx(String validationRegEx) {
		this.validationRegEx = validationRegEx;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public boolean isIs_CanBeEmpty() {
		return is_CanBeEmpty;
	}

	public void setIs_CanBeEmpty(boolean is_CanBeEmpty) {
		this.is_CanBeEmpty = is_CanBeEmpty;
	}
}
