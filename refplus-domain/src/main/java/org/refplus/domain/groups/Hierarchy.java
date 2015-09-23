package org.refplus.domain.groups;

import java.util.HashMap;
import java.util.Map;

import org.refplus.domain.core.Concept;
import org.refplus.domain.core.Ro;
import org.refplus.domain.core.SingleCoded;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A hierarchy is a holder of groups.
 * 
 * 
 * 
 * @author Erik van Ingen
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Hierarchy extends SingleCoded {

	private Concept source;
	private Concept target;

	private Map<Ro, Link> map;

	public Hierarchy(Concept source, Concept target) {
		super();
		this.source = source;
		this.target = target;
		this.map = new HashMap<Ro, Link>();
	}

}
