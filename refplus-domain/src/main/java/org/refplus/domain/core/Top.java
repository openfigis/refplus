package org.refplus.domain.core;

import java.util.Vector;

public class Top {

	
	Vector<Ro> 			topHierarchies; 	// groups
	
	Vector<Ro> 			topCodelists;		// codelists
	// TODO: add contraints for codelists


	public Ro getCodelistByName (String codelistName) {

	    for (Ro codeList: topCodelists) {
	    	if (codeList.getAttribute("name").equals(codeList))
	    		return (codeList);
	    }
		return (null);
	}
}
