package org.refplus.core;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Ro is a Reference Object
 * 
 * 
 * @author Erik van Ingen
 *
 */
@Data
@EqualsAndHashCode
public class Ro {

	private List<Code> codeList;
	private List<Attribute> AttributeList;

}
