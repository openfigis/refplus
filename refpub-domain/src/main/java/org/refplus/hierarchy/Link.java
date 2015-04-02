package org.refplus.hierarchy;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.refplus.core.Ro;

/**
 * 
 * A link links 2 Ros.
 * 
 * 
 * @author Erik van Ingen
 *
 */
@Data
@EqualsAndHashCode
public class Link {

	private Ro source;
	private Ro target;

}
