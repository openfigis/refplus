package org.refplus.domain.core;

import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.refplus.RefPlusException;
import org.refplus.domain.groups.Group;
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
	public void testConceptSpecies() {
		Concept speciesConcept = new Concept();
		Concept familyConcept = new Concept();
		Concept orderConcept = new Concept();
		Concept iscaapGroupConcept = new Concept();
		speciesConcept.setRoList(new Vector<Ro>());
		familyConcept.setRoList(new Vector<Ro>());
		orderConcept.setRoList(new Vector<Ro>());
		iscaapGroupConcept.setRoList(new Vector<Ro>());

		Group familySpecies = new Group(familyConcept, speciesConcept);
		Group orderFamily = new Group(orderConcept, familyConcept);
		Group iscaapGroupSpecies = new Group(orderConcept, familyConcept);

		try {
			CSVReader reader = new CSVReader(new FileReader(csvFileName), '\t');

			reader.readNext();
			String[] nextLine;
			LinkUtil luFamilySpecies = new LinkUtil();
			LinkUtil luOrderFamily = new LinkUtil();
			LinkUtil luIscaapGroupSpecies = new LinkUtil();

			while ((nextLine = reader.readNext()) != null) {

				if (!StringUtils.isBlank(nextLine[2])) {

					Code alpha3 = new Code(nextLine[2]);

					MultiLingualString mls = u.english(nextLine[4]);
					u.addLanguage(Lang.FR, mls, nextLine[5]);
					u.addLanguage(Lang.ES, mls, nextLine[6]);
					u.addLanguage(Lang.LA, mls, nextLine[3]);
					Ro species = new Ro(alpha3, mls);
					speciesConcept.getRoList().add(species);

					Ro family = new Ro(nextLine[8]);
					Ro order = new Ro(nextLine[9]);
					Ro iscaapGroup = new Ro(nextLine[0]);

					luFamilySpecies.buildGroup(familyConcept, familySpecies, family, species);
					luOrderFamily.buildGroup(orderConcept, orderFamily, order, family);
					luIscaapGroupSpecies.buildGroup(iscaapGroupConcept, iscaapGroupSpecies, iscaapGroup, species);

				}
			}

			reader.close();
		} catch (IOException e) {
			throw new RefPlusException(e);
		}

		assertEquals(12560, speciesConcept.getRoList().size());
		assertEquals(980, familyConcept.getRoList().size());
		assertEquals(140, orderConcept.getRoList().size());
		assertEquals(51, iscaapGroupConcept.getRoList().size());
		assertEquals(37, familySpecies.getMap().get(new Ro("Petromyzontidae")).getMemberSet().size());
		assertEquals(6, orderFamily.getMap().get(new Ro("SQUALIFORMES")).getMemberSet().size());
		assertEquals(886, iscaapGroupSpecies.getMap().get(new Ro("38")).getMemberSet().size());

	}
}
