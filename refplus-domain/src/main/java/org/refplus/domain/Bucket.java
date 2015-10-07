package org.refplus.domain;

import java.util.Vector;

import org.refplus.domain.core.AttributeDefinition;
import org.refplus.domain.core.Ro;

/**
 * 
 * @author Erik van Ingen, Thomas Berger
 *
 * A bucket is an arbitrary group of reference objects (Ro's) at the top-level (i.e. visibility like a Concept)
 */
public class Bucket extends Concept {

	public Bucket(Long id, Vector<Ro> roList,
			Vector<AttributeDefinition> attibuteDefinitionList) {
		super(id, roList, attibuteDefinitionList);
		// TODO Auto-generated constructor stub
	}

}
