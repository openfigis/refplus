package org.refplus.domain.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;

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
public class Codelist {

	public Codelist(String attributeName) {
		setAttribute("name", attributeName);
	}
	
	private Codelist hierarchySource = null;
	
	private Vector<Ro> codelist = new Vector<Ro>();

	/**
	 * 
	 * The AttributeConcepts of RefPlus can be dynamically created in the system and should only be edited by a metadata
	 * administrator. They could be loaded through a metadata file in CSV, JSON or XML.
	 * 
	 * This approach solves also the problem of accessing easily a certain attribute. Imagine you have names and long
	 * names. Now they are listed as 2 attributes in the multiLingualStringList. You would then have to find it, while
	 * pulling it from attributeMap1 is a one command action.
	 * 
	 *  AttributeConcept = {name, longName, code, value}
	 *  mandatory attributes: name<MultiLingualAttribute>, ID<Attribute>
	 */
	private Map<String, String> attributeMap = new HashMap<String, String>();
	private Map<String,  Map<String, String>> multilangAttributeMap = new HashMap<String,  Map<String, String>>();

	public void setAttribute (String attributeName, String value) {

		if (attributeName.isEmpty())
			return;
		
		attributeMap.put(attributeName, value);
	}
	
	/**
	 * @param attributeName
	 * @return the attribute or the multilangAttribute in english language
	 */
	public String getAttribute (String attributeName) {
		if (attributeMap.containsKey(attributeName))
			return (attributeMap.get(attributeName));
		
		if (!multilangAttributeMap.containsKey(attributeName))
			return (null);
		return (multilangAttributeMap.get(attributeName).get("en"));
	}
	
	public Ro locateCodeByName (String codeName) {

		for (Ro someRo : codelist) {
			if (someRo.getAttribute("name").equals(codeName))
					return (someRo);
	    }
		return (null);
	}
	
	public void add2Codelist(Ro theCode) {

		String codeString = theCode.getAttribute("name");
		if (codeString == null || StringUtils.isBlank(codeString))
			return;
		
		if (locateCodeByName(codeString) == null) {
			// the code is not part of the codelist; so add it
			codelist.add(theCode);
		}
	}
}
