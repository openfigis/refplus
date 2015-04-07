package org.refplus.domain.core;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.refplus.domain.groups.Group;

/**
 * A Concept.
 * 
 * 
 * @author Erik van Ingen
 *
 */
@Data
@EqualsAndHashCode
public class Concept {

	protected Code conceptCode;
	protected List<MultiLingualString> nameMls;

	/**
	 * The instances of a concept, or in RefPlus terms, the reference objects of a concept.
	 */
	private List<Ro> roList;

	/**
	 * In order to understand the hierarchical context of a Concept, all the groups where it belongs to.
	 * 
	 * This one breaks the rule of the unidirectional dependency between the package core and groups. TODO can this be
	 * improved?
	 * 
	 */
	private List<Group> groupList;

}
