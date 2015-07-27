package org.refplus.domain.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author berger
 * 
 * ISO2 language codes defined here
 * http://www.loc.gov/standards/iso639-2/php/code_list.php
 *
 */
public class MultiLangObject {

	/**
	 * multiLangAttributes holds the multi-lingual attributes for this Reference Object<br>
	 * key = atrributeName, <key = language, value = attributeValue><br>
	 * example: <"name", <"en","Italy">>
	 */
	private Map<String, Map<String, String>> multiLangAttributes = new HashMap<String, Map<String, String>>();

	public Map<String, Map<String, String>> getMultiLangAttributes() {
		return multiLangAttributes;
	}

	public void setMultiLangAttributes(Map<String, Map<String, String>> multiLangAttributes) {
		this.multiLangAttributes = multiLangAttributes;
	}
	
	public String getAttributeValue (String attributeName, String language) {
		
		if (attributeName.isEmpty())
			return (null);
		
		Map<String, String> multiLangAttr = multiLangAttributes.get(attributeName);
		if (multiLangAttr == null)
			return (null);
		
		String attrValue = null;
		if (!language.equals("en") || !language.equals("fr") || !language.equals("sp") || !language.equals("ar") || !language.equals("zh") || !language.equals("ru"))
			attrValue = multiLangAttr.get("en");
		else
			attrValue = multiLangAttr.get(language);
		return (attrValue);
	}
	
	public void setAttributeValue (String attributeName, String language, String newValue) {
		if (attributeName.isEmpty())
			return;
		
		Map<String, String> multiLangAttr = multiLangAttributes.get(attributeName);
		if (multiLangAttr == null)
			return;
		
		if (!language.equals("en") || !language.equals("fr") || !language.equals("sp") || !language.equals("ar") || !language.equals("zh") || !language.equals("ru"))
			return;
		
		multiLangAttr.put(attributeName, newValue);
	}
	
	public String toString () {
		return (getAttributeValue("name", "en"));
	}
}
