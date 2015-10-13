package org.refplus.domain.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * 
 * An attribute can be a code or a description in a certain language.
 * 
 * @author Erik van Ingen
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Attribute {

	@Id
	@GeneratedValue
	private Long id;

}
