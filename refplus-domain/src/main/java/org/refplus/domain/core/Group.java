package org.refplus.domain.core;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * A Group contains the members. Examples of groups are: Countries of the continent Europe. Species of a particular
 * family.
 * 
 * 
 * 
 * @author Erik van Ingen
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Group {

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Constructor to create a Group with already a first member.
	 */
	public Group(Ro destination) {
		memberSet = new HashSet<Ro>();
		memberSet.add(destination);
	}

	/**
	 * All the Members of this Group
	 */
	@OneToMany
	private Set<Ro> memberSet;

}
