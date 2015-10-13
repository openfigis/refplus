package org.refplus.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@Entity
public class Bucket extends Concept {

	@Id
	@GeneratedValue
	private Long id;

}
