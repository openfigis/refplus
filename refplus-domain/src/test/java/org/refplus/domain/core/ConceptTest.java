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
	public static String csvFileNameArea = "src/test/resources/CL_PRODUCTION_AREA_0.csv";

	MultiLingualStringUtil u = new MultiLingualStringUtil();

	/**
	 * 
	 * http://figisapps.fao.org/FIGISwiki/index.php/FMA_SDMX_Codelist
	 * 
	 * 0 OBJECTID,N,9,0
	 *
	 * 1 OCEAN,C,16
	 *
	 * 2 SUBOCEAN,C,16
	 *
	 * 3 F_AREA,C,16
	 *
	 * 4 F_SUBAREA,C,16
	 *
	 * 5 F_SUBUNIT,C,16
	 *
	 * 6 F_DIVISION,C,16
	 *
	 * 7 F_SUBDIVIS,C,16
	 *
	 * 8 Shape_Leng,N,19,11
	 *
	 * 9 Shape_Area,N,19,11
	 *
	 */

	@Test
	public void testConceptAreas() {
		Concept subunitConcept = new Concept(new Vector<Ro>());
		Concept subDivisionConcept = new Concept(new Vector<Ro>());
		Concept subAreaConcept = new Concept(new Vector<Ro>());
		Concept majorAreaConcept = new Concept(new Vector<Ro>());

		Group subDivisionSubUnit = new Group(subDivisionConcept, subunitConcept);
		Group subAreaSubDivision = new Group(subDivisionConcept, subAreaConcept);
		Group majorAreaSubArea = new Group(subAreaConcept, majorAreaConcept);

		try {
			CSVReader reader = new CSVReader(new FileReader(csvFileNameArea));

			reader.readNext();
			String[] nextLine;
			LinkUtil lu = new LinkUtil();

			while ((nextLine = reader.readNext()) != null) {

				if (!StringUtils.isBlank(nextLine[3])) {

					// MultiLingualString mls = u.english(nextLine[1]);

					Ro subunit = lu.addCodeAsRo(subunitConcept, nextLine[5]);
					Ro subDivision = lu.addCodeAsRo(subDivisionConcept, nextLine[7]);

					if (subDivision != null && subunit != null) {
						lu.buildGroup(subDivisionConcept, subDivisionSubUnit, subDivision, subunit);
					}

					Ro subArea = lu.addCodeAsRo(subAreaConcept, nextLine[4]);
					if (subArea != null && subDivision != null) {
						lu.buildGroup(subAreaConcept, subAreaSubDivision, subArea, subDivision);
					}

					Ro majorArea = lu.addCodeAsRo(majorAreaConcept, nextLine[3]);
					if (majorArea != null && subArea != null) {
						lu.buildGroup(majorAreaConcept, majorAreaSubArea, majorArea, subArea);
					}

				}
			}

			reader.close();
		} catch (IOException e) {
			throw new RefPlusException(e);
		}

		assertEquals(2, subunitConcept.getRoList().size());
		assertEquals(61, subDivisionConcept.getRoList().size());
		assertEquals(92, subAreaConcept.getRoList().size());
		assertEquals(19, majorAreaConcept.getRoList().size());

		assertEquals(2, subDivisionSubUnit.getMap().get(new Ro("21.5.Z.e")).getMemberSet().size());
		assertEquals(11, subAreaSubDivision.getMap().get(new Ro("27.3")).getMemberSet().size());
		assertEquals(7, majorAreaSubArea.getMap().get(new Ro("21")).getMemberSet().size());

	}

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
		Concept speciesConcept = new Concept(new Vector<Ro>());
		Concept familyConcept = new Concept(new Vector<Ro>());
		Concept orderConcept = new Concept(new Vector<Ro>());
		Concept iscaapGroupConcept = new Concept(new Vector<Ro>());

		Group familySpecies = new Group(familyConcept, speciesConcept);
		Group orderFamily = new Group(orderConcept, familyConcept);
		Group iscaapGroupSpecies = new Group(orderConcept, familyConcept);

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

					Ro family = new Ro(nextLine[8]);
					Ro order = new Ro(nextLine[9]);
					Ro iscaapGroup = new Ro(nextLine[0]);

					lu.buildGroup(familyConcept, familySpecies, family, species);
					lu.buildGroup(orderConcept, orderFamily, order, family);
					lu.buildGroup(iscaapGroupConcept, iscaapGroupSpecies, iscaapGroup, species);

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
