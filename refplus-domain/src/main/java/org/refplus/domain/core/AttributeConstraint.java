package org.refplus.domain.core;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Thomas Berger
 * @module AttributeConstraint.java
 *
 * @text   	Implements the properties of a codelist attribute
 *
 * 1.00  19-May-2015  first release
 *
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class AttributeConstraint {

	private int codelist_ID = 0;
	
	private int attribute_sortorder = 0;		// unique within a codelist (1,2,3,..)
	
	private String columnName = null;
	
	private boolean isHidden = false;
	
	private String restName = null;
	
	private boolean is_A_Code = false;			// OR HAVE 
	
	/**
	 * valid attribute types are "CHAR", "INT" and "MULTILANG"
	 */
	private String attr_Type = null;
	
	private int attr_Length = 0;
	
	private int attr_Precision = 0;
	
	private boolean is_Unique = false;
	
}
