package org.refplus.domain;

import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
@Entity
public class Top {

	@Id
	@GeneratedValue
	private Long id;

	private Vector<Hierarchy> hierarchyList = new Vector<Hierarchy>();

	private Vector<Bucket> bucketList = new Vector<Bucket>();

	private Vector<Concept> conceptList = new Vector<Concept>();

	// TODO: add constraints for codelists

}
