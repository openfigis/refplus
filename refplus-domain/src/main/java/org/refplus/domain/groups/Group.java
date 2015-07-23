package org.refplus.domain.groups;

import java.util.Map;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.refplus.domain.core.Concept;
import org.refplus.domain.core.Ro;

/**
 * A group is a holder of links.
 * 
 * 
 * 
 * @author Erik van Ingen
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Group extends Ro /* EU 28 */{

	private Hierarchy hierarchy;

	// Map <Ro> : is the hierarchy this group is part of
	// Set <Ro> : are the children of the group
	private Map<Ro, Set<Ro>> groups;

	// example <Europe<Italy, Germany, etc>>
	// <developing,<a,b,c>
	// <ceonmoic,<a,b,c>

	public Group(Concept source, Concept target) {
		super();

		// this.groups = new HashMap<Ro, new HashSet<Ro>>();
	}
}
