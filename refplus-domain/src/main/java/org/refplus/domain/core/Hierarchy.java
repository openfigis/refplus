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
public class Hierarchy {

	public Hierarchy(String attributeName) {
		setAttribute("name", attributeName);
	}
	
	private Hierarchy hierarchySource = null;
	
	private Vector<Ro> hierarchy = new Vector<Ro>();

	/**
	 * 
	 * Hierarchy is a grouping of reference objects (Ro's)
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
	
	public Ro locateHierarchyByName (String codeName) {

		for (Ro someRo : hierarchy) {
			if (someRo.getAttribute("name").equals(codeName))
					return (someRo);
	    }
		return (null);
	}
	
	public void add2Hierarchy(Ro theCode) {

		String codeString = theCode.getAttribute("name");
		if (codeString == null || StringUtils.isBlank(codeString))
			return;
		
		if (locateHierarchyByName(codeString) == null) {
			// the code is not part of the codelist; so add it
			hierarchy.add(theCode);
		}
	}

}
