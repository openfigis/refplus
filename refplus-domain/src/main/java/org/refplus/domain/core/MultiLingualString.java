package org.refplus.domain.core;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;

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
@Entity
public class MultiLingualString extends Attribute implements Serializable {

	@ElementCollection(fetch = FetchType.EAGER)
	@Lob
	@CollectionTable(name = "STRINGMAP")
	private Map<String, String> stringMap;

	private static final long serialVersionUID = -6780225288480597193L;

}