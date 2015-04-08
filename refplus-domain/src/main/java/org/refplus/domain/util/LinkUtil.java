package org.refplus.domain.util;

import org.refplus.domain.core.Concept;
import org.refplus.domain.core.Ro;
import org.refplus.domain.groups.Group;
import org.refplus.domain.groups.Link;

public class LinkUtil {

	public void buildGroup(Concept higherLevelConcept, Group group, String higherLevelRoCode, Ro ro) {

		// first create a Ro for the higher level ro instance
		Ro higherLevelRo = new Ro(higherLevelRoCode);

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

}
