package org.refplus.domain.util;

import org.apache.commons.lang.StringUtils;
import org.refplus.domain.core.AttributeConcept;
import org.refplus.domain.core.Concept;
import org.refplus.domain.core.Ro;
import org.refplus.domain.groups.Group;
import org.refplus.domain.groups.Link;

public class LinkUtil {

	public void buildGroup(Concept higherLevelConcept, Group group, Ro higherLevelRo, Ro ro) {

		// check whether this one is already in the list
		if (!higherLevelConcept.getRoList().contains(higherLevelRo)) {
			// if not, add it
			higherLevelConcept.getRoList().add(higherLevelRo);
		}

		// check whether a group exists for the higherLevelRo
		if (!group.getMap().containsKey(higherLevelRo)) {
			// if not, create it
			Link link = new Link(ro);
			group.getMap().put(higherLevelRo, link);
		}
		// add the relation ro-higherLevelRoCode
		group.getMap().get(higherLevelRo).getMemberSet().add(ro);

	}

	public Ro addCodeAsRo(Concept subunitConcept, AttributeConcept attributeConcept, String codeString) {

		if (codeString.equals("21.5.Z")) {
			System.out.println();
		}

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
