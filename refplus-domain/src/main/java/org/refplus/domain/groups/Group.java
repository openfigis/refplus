package org.refplus.domain.groups;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.refplus.domain.core.Concept;
import org.refplus.domain.core.Ro;
import org.refplus.domain.core.SingleCoded;

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
public class Group extends SingleCoded {

	private Concept source;
	private Concept target;

	private Map<Ro, Link> map;

	public Group(Concept source, Concept target) {
		super();
		this.source = source;
		this.target = target;
		this.map = new HashMap<Ro, Link>();
	}

}
