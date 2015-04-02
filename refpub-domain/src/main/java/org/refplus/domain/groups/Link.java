package org.refplus.domain.groups;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@AllArgsConstructor
@EqualsAndHashCode
public class Link {

	private Ro source;
	private Ro target;

}
