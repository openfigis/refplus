package org.refplus.domain.core;

import java.io.Serializable;
import java.util.Map;

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
public class MultiLingualAttribute implements Serializable {

	private static final long serialVersionUID = -6780225288480597193L;

	/**
	 * <language, value>
	 * example: <"en", "Italy">
	 */
	private Map<String, String> attrMultiLanguage;

}