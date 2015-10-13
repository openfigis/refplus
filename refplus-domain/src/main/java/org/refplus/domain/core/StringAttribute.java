package org.refplus.domain.core;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Example of use are the latin name of species. The latin names are not always unique and can therefore not be
 * considered as codes.
 * 
 * 
 * @author Erik van Ingen
 *
 */

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "value")
@Entity
public class StringAttribute extends Attribute {

	@NonNull
	private String value;

}
