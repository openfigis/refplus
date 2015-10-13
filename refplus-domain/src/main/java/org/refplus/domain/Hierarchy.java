package org.refplus.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.refplus.domain.core.Group;
import org.refplus.domain.core.Ro;
import org.refplus.domain.core.SingleCoded;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * A hierarchy is a holder of groups.
 * 
 * 
 * 
 * @author Erik van Ingen
 *
 */
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Hierarchy extends SingleCoded {

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	private Concept source;
	@NonNull
	private Concept target;

	private Map<Ro, Group> map = new HashMap<Ro, Group>();

}
