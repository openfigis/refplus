package org.refplus.domain.groups;

import java.util.HashSet;
import java.util.Set;

import org.refplus.domain.core.Ro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * A Group contains the Ros being part of the same hierarchical element. Examples of links are: Countries of the
 * continent Europe. Species of a particular family.
 * 
 * 
 * 
 * @author Erik van Ingen
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Group {

	/**
	 * Constructor to create a link with already a first element.
	 */
	public Group(Ro destination) {
		memberSet = new HashSet<Ro>();
		memberSet.add(destination);
	}

	/**
	 * All the Members of this link
	 */
	private Set<Ro> memberSet;

}
