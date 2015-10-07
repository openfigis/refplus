package org.refplus.domain;

import java.util.Vector;

import org.refplus.domain.core.AttributeDefinition;
import org.refplus.domain.core.Ro;
import org.refplus.domain.core.SingleCoded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * A Concept. Can be a country, species, city, whatever
 * 
 * 
 * @author Erik van Ingen
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Concept extends SingleCoded {

	// @Id
	private Long id;

	/**
	 * The instances of a concept, or in RefPlus terms, the reference objects of a concept. If the concept is Country,
	 * the Ro instances are Germany, France, Mexico, etc.
	 */
	private Vector<Ro> roList;

	private Vector<AttributeDefinition> attibuteDefinitionList;

}
