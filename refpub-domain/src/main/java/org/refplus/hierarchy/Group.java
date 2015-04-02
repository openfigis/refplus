package org.refplus.hierarchy;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.refplus.core.Ro;

/**
 * A group is a holder links.
 * 
 * TODO The group could also describe the links as singular or plural. The group could also describe the links by name.
 * 
 * 
 * @author Erik van Ingen
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Group extends Ro {

	private List<Link> linkList;

}
