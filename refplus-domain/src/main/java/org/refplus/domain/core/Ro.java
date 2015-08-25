package org.refplus.domain.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.deltaspike.core.util.StringUtils;

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

	public Ro(String attributeName) {
		setAttribute("name", attributeName);
	}
	
	private Ro hierarchySource = null;
	
	private Map<String, Vector<Ro>> groups = new HashMap<String, Vector<Ro>>();

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
	private Map<String, String> codeMap = new HashMap<String, String>();
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
	
	/**
	 * @param groupName name of the group
	 * @return the grouping, or null
	 */
	public Vector<Ro> getGroupByName (String groupName) {
		return (groups.get(groupName));
	}
	
	/**
	 * @param groupName name of group
	 * @param itemName name of Ro
	 * @return the Ro which has an attribute "name" equals itemName
	 */
	public Ro getGroupItemByName (String groupName, String itemName) {
		Vector<Ro> aGroup = groups.get(groupName);
		
		if (aGroup == null || itemName == null || StringUtils.isEmpty(itemName))
			return (null);

	    for (Ro groupMember : aGroup) {
	    	if (groupMember.getAttribute("name").equals(itemName))
	    		return (groupMember);
	    }
		return (null);
	}
	
	public void setAttributeML (String attributeName, String languge, String value) throws Exception {
		if (!languge.equals(Lang.EN) && !languge.equals(Lang.FR) && !languge.equals(Lang.SP))
			throw new Exception("sorry language ["+languge+"] is not supported");
		
		Map <String,String> mlAttribute = multilangAttributeMap.get(attributeName);
		if (mlAttribute == null)
			mlAttribute = new HashMap<String, String>();
		
		mlAttribute.put(languge, value);
	}
}
