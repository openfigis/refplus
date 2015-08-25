package org.refplus.domain.core;

import java.util.Vector;

public class Top {

	
	Vector<Hierarchy> 		topHierarchies = new Vector<Hierarchy>(); 	// groups
	
	Vector<Codelist> 		topCodelists = new Vector<Codelist> ();		// codelists
	// TODO: add contraints for codelists

	public Codelist getCodelistByName (String codelistName) {

	    for (Codelist codeList: topCodelists) {
	    	if (codeList.getAttribute("name").equals(codeList))
	    		return (codeList);
	    }
		return (null);
	}
}
