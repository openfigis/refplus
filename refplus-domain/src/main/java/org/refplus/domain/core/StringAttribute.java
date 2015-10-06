package org.refplus.domain.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Example of use are the latin name of species. The latin names are not always unique and can therefore not be
 * considered as codes.
 * 
 * 
 * @author Erik van Ingen
 *
 */

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "value")
public class StringAttribute extends Attribute {

	private String value;

}
