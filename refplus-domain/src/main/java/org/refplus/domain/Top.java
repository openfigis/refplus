package org.refplus.domain;

import java.util.Vector;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Top level class from where to navigate in the RefPlus domain model.
 * 
 * @author Thomas Berger, Erik van Ingen
 *
 */

@Data
@EqualsAndHashCode
public class Top {

	private Vector<Hierarchy> hierarchyList = new Vector<Hierarchy>();

	private Vector<Bucket> bucketList = new Vector<Bucket>();

	private Vector<Concept> conceptList = new Vector<Concept>();

	// TODO: add constraints for codelists

}
