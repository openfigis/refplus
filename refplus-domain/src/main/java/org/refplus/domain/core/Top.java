package org.refplus.domain.core;

import java.util.Vector;

public class Top {

	
	Vector<Hierarchy> 		topHierarchies = new Vector<Hierarchy>(); 	// groups
	
	Vector<Concept> 		topConcepts = new Vector<Concept> ();		// concepts
	// TODO: add constraints for codelists

	public Concept getConceptByName (String codelistName) {

	    for (Concept aConcept: topConcepts) {
	    	if (aConcept.getAttribute("name").equals(aConcept))
	    		return (aConcept);
	    }
		return (null);
	}
}
