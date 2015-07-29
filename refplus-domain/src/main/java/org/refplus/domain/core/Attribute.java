package org.refplus.domain.core;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * An attribute can be a code or a description in a certain language.
 * 
 * @author Erik van Ingen
 *
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Attribute {

	private String attributeValue = null;
	private boolean isCode = false;
	
}
