package org.refplus.domain.core;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * MultiLingualString is the description of a Ro in the different languages
 * 
 * 
 * @author Erik van Ingen
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MultiLingualString extends Attribute implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private Map<String, String> stringMap;

	private static final long serialVersionUID = -6780225288480597193L;

}