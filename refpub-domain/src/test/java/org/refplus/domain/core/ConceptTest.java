package org.refplus.domain.core;

import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.refplus.RefPlusException;
import org.refplus.domain.groups.Group;
import org.refplus.domain.groups.Link;
import org.refplus.domain.util.Lang;
import org.refplus.domain.util.LinkUtil;
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
		familyConcept.setRoList(new ArrayList<Ro>());

		Group familySpecies = new Group();
		// Group orderFamily = new Group();
		familySpecies.setSource(familyConcept);
		familySpecies.setTarget(speciesConcept);
		familySpecies.setMap(new HashMap<Ro, Link>());

		try {
			CSVReader reader = new CSVReader(new FileReader(csvFileName), '\t');

			reader.readNext();
			String[] nextLine;
			LinkUtil lu = new LinkUtil();

			while ((nextLine = reader.readNext()) != null) {

				if (!StringUtils.isBlank(nextLine[2])) {

					Code alpha3 = new Code(nextLine[2]);

					MultiLingualString mls = u.english(nextLine[4]);
					u.addLanguage(Lang.FR, mls, nextLine[5]);
					u.addLanguage(Lang.ES, mls, nextLine[6]);
					u.addLanguage(Lang.LA, mls, nextLine[3]);
					Ro species = new Ro(alpha3, mls);
					speciesConcept.getRoList().add(species);

					lu.buildGroup(familyConcept, familySpecies, nextLine[2], species);
				}
			}

			reader.close();
		} catch (IOException e) {
			throw new RefPlusException(e);
		}

		// 12560

		assertEquals(12560, speciesConcept.getRoList().size());
		assertEquals(980, familyConcept.getRoList().size());

	}
}
