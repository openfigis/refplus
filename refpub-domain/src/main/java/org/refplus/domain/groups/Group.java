package org.refplus.domain.groups;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.refplus.domain.core.Concept;
import org.refplus.domain.core.Ro;

/**
 * A group is a holder links.
 * 
 * 
 * 
 * @author Erik van Ingen
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Group extends Ro {

	private Concept source;
	private Concept target;

	/**
	 * Indicator whether the relation is singular or plural.
	 * 
	 * TODO, do we need link type, like parent, child, etc?
	 */
	private Cardinality cardinality;

	/**
	 * The list is links.
	 * 
	 */
	private List<Link> linkList;

}
