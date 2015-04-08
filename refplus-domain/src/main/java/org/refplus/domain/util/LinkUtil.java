package org.refplus.domain.util;

import org.apache.commons.lang.StringUtils;
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

	public void addCodeAsRo(Concept subunitConcept, String codeString) {
		Ro ro = new Ro(codeString);
		if (!subunitConcept.getRoList().contains(ro) && !StringUtils.isBlank(codeString)) {
			subunitConcept.getRoList().add(ro);
		}

	}

}
