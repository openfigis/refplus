package org.refplus.domain.core;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import org.refplus.RefPlusException;
import org.refplus.domain.groups.Cardinality;
import org.refplus.domain.groups.Group;
import org.refplus.domain.groups.Link;
import org.refplus.domain.util.Lang;
import org.refplus.domain.util.MultiLingualStringUtil;

import au.com.bytecode.opencsv.CSVReader;

public class ConceptTest {
	public static String csvFileName = "src/test/resources/CL_SPECIES_1_3.csv";

	MultiLingualStringUtil u = new MultiLingualStringUtil();

	/**
	 * 0 ISSCAAP
	 * 
	 * 1 TAXOCODE
	 * 
	 * 2 3A_CODE
	 * 
	 * 3 Scientific_name
	 * 
	 * 4 English_name
	 * 
	 * 5 French_name
	 * 
	 * 6 Spanish_name
	 * 
	 * 7 Author
	 * 
	 * 8 Family
	 * 
	 * 9 Order
	 * 
	 * 10 Stats_data
	 */

	@Test
	public void testConcept() {
		Concept speciesConcept = new Concept();
		Concept familyConcept = new Concept();
		speciesConcept.setRoList(new ArrayList<Ro>());

		Group familySpecies = new Group();
		// Group orderFamily = new Group();
		familySpecies.setSource(familyConcept);
		familySpecies.setTarget(speciesConcept);
		familySpecies.setCardinality(Cardinality.PLURAL);

		try {
			CSVReader reader = new CSVReader(new FileReader(csvFileName));

			reader.readNext();
			String[] nextLine;
			// Set<String> orderSet = new HashSet<String>();
			HashMap<String, Ro> familyMap = new HashMap<String, Ro>();

			while ((nextLine = reader.readNext()) != null) {

				Code alpha3 = new Code(nextLine[2]);

				MultiLingualString mls = u.english(nextLine[4]);
				u.addLanguage(Lang.FR, mls, nextLine[5]);
				u.addLanguage(Lang.ES, mls, nextLine[6]);
				u.addLanguage(Lang.LA, mls, nextLine[3]);
				Ro ro = new Ro(alpha3, mls);
				speciesConcept.getRoList().add(ro);

				Ro family = null;
				if (familyMap.containsKey(nextLine[8])) {
					family = familyMap.get(nextLine[8]);
				} else {
					Code familyCode = new Code(nextLine[8]);
					family = new Ro(familyCode, null);
				}
				familySpecies.getLinkList().add(new Link(family, ro));

				// buildUpOrderFamily(orderSet, orderFamily, nextLine[9], nextLine[8], ro);

			}
			reader.close();
		} catch (IOException e) {
			throw new RefPlusException(e);
		}

	}

	// private void buildUpOrderFamily(Set<String> orderSet, Group orderFamily, String orderCode,
	//
	// String speciesCode, Ro ro) {
	// if (orderSet.contains(orderCode)) {
	//
	// } else {
	// Ro family = new Ro();
	// Link link = new Link(family, ro);
	// orderFamily.getLinkList().add(link);
	// orderSet.add(orderFamily + orderCode);
	// }
	//
	// }
}
