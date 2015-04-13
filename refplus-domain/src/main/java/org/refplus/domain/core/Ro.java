package org.refplus.domain.core;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Ro is a Reference Object
 * 
 * 
 * @author Erik van Ingen
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Ro {

	/**
	 * This is the 1st alternative to the combination codeList and multiLingualStringList.
	 * 
	 * The AttributeConcepts of RefPlus can be dynamically created in the system and should only be edited by a metadata
	 * administrator. They could be loaded through a metadata file in CSV, JSON or XML.
	 * 
	 * This approach solves also the problem of accessing easily a certain attribute. Imagine you have names and long
	 * names. Now they are listed as 2 attributes in the multiLingualStringList. You would then have to find it, while
	 * pulling it from attributeMap1 is a one command action.
	 * 
	 * 
	 */
	// private Map<AttributeConcept, Attribute> attributeMap1;

	/**
	 * This is the 2nd alternative to the combination codeList and multiLingualStringList.
	 * 
	 * AttributeConcept = {name, longName, code, value}
	 * 
	 * The disadvantage of this approach is that you cannot give names dynamically, so you cannot add a new attribute
	 * like iso3 or something, because every attribute is a class in Java. Therefore this approach is not enough
	 * flexible and the attributeMap1 is therefore better.
	 * 
	 * 
	 */
	// private Map<Class<? extends AttributeConcept>, Attribute> attributeMap2;

	/**
	 * This codeList is not to be confused with CodeList
	 */
	private List<Code> codeList;
	private List<MultiLingualString> multiLingualStringList;
	private List<String> valueList;

	public Ro(Code code, MultiLingualString mls) {
		codeList = new ArrayList<Code>();
		codeList.add(code);
		multiLingualStringList = new ArrayList<MultiLingualString>();
		multiLingualStringList.add(mls);
	}

	public Ro(String code) {
		Code codeObject = new Code(code);
		codeList = new ArrayList<Code>();
		codeList.add(codeObject);
	}

}
