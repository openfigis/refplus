package org.refplus.domain.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;

import org.refplus.domain.Bucket;
import org.refplus.domain.Concept;
import org.refplus.domain.Hierarchy;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Ro is a Reference Object
 * 
 * 
 * @author Erik van Ingen, Thomas Berger
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Ro {

	@Id
	private Long id;

	public Ro(AttributeDefinition codeAttributeConcept, Code code, AttributeDefinition mlsAttributeConcept,
			MultiLingualString mls) {
		attributeMap = new HashMap<AttributeDefinition, Attribute>();
		attributeMap.put(codeAttributeConcept, code);
		attributeMap.put(mlsAttributeConcept, mls);
	}

	public Ro(AttributeDefinition codeAttributeConcept, String code) {
		Code codeObject = new Code(0l, code);
		attributeMap = new HashMap<AttributeDefinition, Attribute>();
		attributeMap.put(codeAttributeConcept, codeObject);
	}

	/**
	 * 
	 * The AttributeConcepts of RefPlus can be dynamically created in the system and should only be edited by a metadata
	 * administrator. They could be loaded through a metadata file in CSV, JSON or XML.
	 * 
	 * This approach solves also the problem of accessing easily a certain attribute. Imagine you have names and long
	 * names. Now they are listed as 2 attributes in the multiLingualStringList. You would then have to find it, while
	 * pulling it from attributeMap1 is a one command action.
	 * 
	 * * AttributeConcept = {name, longName, code, value}
	 */

	/**
	 * the attributes for this Ro
	 */
	private Map<AttributeDefinition, Attribute> attributeMap;

	/**
	 * tree structure is defined one-way, top-down each Ro can point to Group's of other Ro's; and each group is part of
	 * a hierarchy example: Ro(country:<Italy>) has a Hierarchy (GAUL) with member <Lazio>, and a Hierarchy (city) with
	 * member <Rome>
	 */
	private Map<Hierarchy, Group> groups;

	/**
	 * the buckets where this Ro is part of this is to model the OneToMany relation
	 */
	private Set<Bucket> buckets;

	/**
	 * the concepts where this Ro is part of this is to model the OneToMany relation
	 */
	private Set<Concept> concepts;

}
