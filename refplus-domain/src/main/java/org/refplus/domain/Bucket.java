package org.refplus.domain;

import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Erik van Ingen, Thomas Berger
 *
 *         A bucket is an arbitrary group of reference objects (Ro's) at the top-level (i.e. visibility like a Concept)
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class Bucket extends Concept {

	@Id
	private Long id;

}
