package org.refplus.domain.core;

import java.util.Map;
import java.util.Set;

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

	private Ro hierarchySource;
	
	private Map<Ro, Set<Ro>> groups;

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
	private Map<String, Attribute> attributeMap;
	private Map<String, MultiLingualAttribute> multilangAttributeMap;

}
