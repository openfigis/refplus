package org.refplus.domain.groups;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.refplus.domain.core.Ro;

/**
 * 
 * A link links 2 Ros.
 * 
 * 
 * @author Erik van Ingen
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Link {

	public Link(Ro destination) {
		memberSet = new HashSet<Ro>();
		memberSet.add(destination);
	}

	private Set<Ro> memberSet;

}
