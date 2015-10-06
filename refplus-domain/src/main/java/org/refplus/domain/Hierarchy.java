package org.refplus.domain;

import java.util.HashMap;
import java.util.Map;

import org.refplus.domain.core.Group;
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

	// @Id
	private Long id;

	private Concept source;
	private Concept target;

	private Map<Ro, Group> map;

	public Hierarchy(Concept source, Concept target) {
		super();
		this.source = source;
		this.target = target;
		this.map = new HashMap<Ro, Group>();
	}

}
