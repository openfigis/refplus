package org.refplus.domain.util;

import org.apache.commons.lang.StringUtils;
import org.refplus.domain.core.Ro;


public class LinkUtil {

	/**
	 * Build up the Group. Do create the group if necessary.
	 * 
	 * A higherLevelRo has a one-to-may relation to the ro.
	 * 
	 * 
	 * @param higherLevelConcept
	 * @param group
	 * @param higherLevelRo
	 * @param ro
	 */
	public void buildGroup(Concept higherLevelConcept, Group group, Ro higherLevelRo, Ro ro) {

		// check whether this one is already in the list
		if (!higherLevelConcept.getRoList().contains(higherLevelRo)) {
			// if not, add it
			higherLevelConcept.getRoList().add(higherLevelRo);
		}

		/*
		// check whether a group exists for the higherLevelRo
		if (!group.getMap().containsKey(higherLevelRo)) {
			// if not, create it
			Grouping link = new Grouping(ro);
			group.getMap().put(higherLevelRo, link);
		}
		// add the relation ro-higherLevelRoCode
		group.getMap().get(higherLevelRo).getMemberSet().add(ro);
		 */
	}

	/**
	 * Build up a ro from the codeString. Create a new ro, add the codeString as an attributeConcept attribute to the
	 * new created ro.
	 * 
	 * All this, providing a non blank codeString.
	 * 
	 * 
	 * @param subunitConcept
	 * @param attributeConcept
	 * @param codeString
	 * @return
	 */
	public Ro addCodeAsRo(Concept subunitConcept, AttributeDefinition attributeConcept, String codeString) {

		Ro ro = new Ro(attributeConcept, codeString);
		if (StringUtils.isBlank(codeString)) {
			ro = null;
		} else {
			if (!subunitConcept.getRoList().contains(ro)) {
				subunitConcept.getRoList().add(ro);
			}
		}
		return ro;

	}

}
