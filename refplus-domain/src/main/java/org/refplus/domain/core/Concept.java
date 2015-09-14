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
public class Concept {

	public Concept(String attributeName) {
		setAttribute("name", attributeName);
	}
	
	private Vector<Ro> conceptlist = new Vector<Ro>();

	/**
	 * 
	 * Concept is a list of reference objects (Ro's)
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
	 * @return the attribute or the multilangAttribute in English language
	 */
	public String getAttribute (String attributeName) {
		if (attributeMap.containsKey(attributeName))
			return (attributeMap.get(attributeName));
		
		if (!multilangAttributeMap.containsKey(attributeName))
			return (null);
		return (multilangAttributeMap.get(attributeName).get("en"));
	}
	
	public Ro locateConceptByName (String codeName) {

		for (Ro someRo : conceptlist) {
			if (someRo.getAttribute("name").equals(codeName))
					return (someRo);
	    }
		return (null);
	}
	
	public void add2Concept(Ro theCode) {

		String codeString = theCode.getAttribute("name");
		if (codeString == null || StringUtils.isBlank(codeString))
			return;
		
		if (locateConceptByName(codeString) == null) {
			// the code is not part of the Concept; so add it
			conceptlist.add(theCode);
		}
	}
}
