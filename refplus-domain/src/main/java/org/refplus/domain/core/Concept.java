package org.refplus.domain.core;

import java.util.Vector;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A Concept. Can be a country, species, city, whatever
 * 
 * 
 * @author Erik van Ingen
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Concept extends SingleCoded {

	/**
	 * The instances of a concept, or in RefPlus terms, the reference objects of a concept. If the concept is Country,
	 * the Ro instances are Germany, France, Mexico, etc.
	 */
	private Vector<Ro> roList;

}
