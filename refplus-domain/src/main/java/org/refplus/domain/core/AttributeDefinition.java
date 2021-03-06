package org.refplus.domain.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class AttributeDefinition extends SingleCoded {

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	private Class<? extends Attribute> attributeType;

	@NonNull
	private String inputSchema;
	/*
	 * // private boolean is_A_Code = false; // private String attr_Type = null;
	 * 
	 * private int attribute_ID = 0; // unique accross all codelists (451,452,453,...)
	 * 
	 * private int attribute_number = 0; // unique within a codelist (1,2,3,..) used for sorting
	 * 
	 * private int codelist_ID = 0;
	 * 
	 * private String attributeName = null;
	 * 
	 * private boolean isHidden = false;
	 * 
	 * 
	 * // valid attribute types are "CHAR", "INT" and "MULTILANG" private String attr_Type = null;
	 * 
	 * private int attr_Length = 0;
	 * 
	 * private int attr_Precision = 0; // only valid for INT
	 * 
	 * private boolean is_Unique = false;
	 * 
	 * private boolean is_CanBeEmpty = true;
	 * 
	 * private String validationRegEx = null;
	 */

}
