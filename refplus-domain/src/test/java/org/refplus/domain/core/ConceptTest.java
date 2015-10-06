package org.refplus.domain.core;

import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.refplus.RefPlusException;
import org.refplus.domain.Concept;
import org.refplus.domain.Hierarchy;
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

		AttributeDefinition subUnitAttributeConcept = new AttributeDefinition();
		Vector<AttributeDefinition> attibuteDefinitionList = new Vector<AttributeDefinition>();
		attibuteDefinitionList.add(subUnitAttributeConcept);
		Concept subunitConcept = new Concept(new Vector<Ro>(), attibuteDefinitionList);

		AttributeDefinition subDivisionAttributeConcept = new AttributeDefinition();
		Vector<AttributeDefinition> subDivisionAttributeDefinitionList = new Vector<AttributeDefinition>();
		subDivisionAttributeDefinitionList.add(subDivisionAttributeConcept);
		Concept subDivisionConcept = new Concept(new Vector<Ro>(), subDivisionAttributeDefinitionList);

		AttributeDefinition subAreaAttributeConcept = new AttributeDefinition();
		Vector<AttributeDefinition> subAreaAttributeDefinitionList = new Vector<AttributeDefinition>();
		subDivisionAttributeDefinitionList.add(subAreaAttributeConcept);
		Concept subAreaConcept = new Concept(new Vector<Ro>(), subAreaAttributeDefinitionList);

		AttributeDefinition majorAreaAttributeConcept = new AttributeDefinition();
		Vector<AttributeDefinition> majorAreaAttributeDefinitionList = new Vector<AttributeDefinition>();
		majorAreaAttributeDefinitionList.add(majorAreaAttributeConcept);
		Concept majorAreaConcept = new Concept(new Vector<Ro>(), majorAreaAttributeDefinitionList);

		Hierarchy subDivisionSubUnit = new Hierarchy(subDivisionConcept, subunitConcept);
		Hierarchy subAreaSubDivision = new Hierarchy(subDivisionConcept, subAreaConcept);
		Hierarchy divisionSubDivision = new Hierarchy(subDivisionConcept, subAreaConcept);
		Hierarchy majorAreaSubArea = new Hierarchy(subAreaConcept, majorAreaConcept);

		try {
			CSVReader reader = new CSVReader(new FileReader(csvFileNameArea));

			reader.readNext();
			String[] nextLine;
			LinkUtil lu = new LinkUtil();

			while ((nextLine = reader.readNext()) != null) {

				if (!StringUtils.isBlank(nextLine[3])) {

					// MultiLingualString mls = u.english(nextLine[1]);

					Ro subunit = lu.addCodeAsRo(subunitConcept, subUnitAttributeConcept, nextLine[5]);
					Ro subDivision = lu.addCodeAsRo(subDivisionConcept, subDivisionAttributeConcept, nextLine[7]);

					if (subDivision != null && subunit != null) {
						lu.buildHierarchy(subDivisionConcept, subDivisionSubUnit, subDivision, subunit);
					}

					Ro subArea = lu.addCodeAsRo(subAreaConcept, subAreaAttributeConcept, nextLine[4]);
					if (subArea != null && subDivision != null) {
						lu.buildHierarchy(subAreaConcept, subAreaSubDivision, subArea, subDivision);
					}

					Ro majorArea = lu.addCodeAsRo(majorAreaConcept, majorAreaAttributeConcept, nextLine[3]);
					if (majorArea != null && subArea != null) {
						lu.buildHierarchy(majorAreaConcept, majorAreaSubArea, majorArea, subArea);
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

		assertEquals(2,
				subDivisionSubUnit.getMap().get(new Ro(subDivisionAttributeConcept, "21.5.Z.e")).getMemberSet().size());
		assertEquals(11,
				subAreaSubDivision.getMap().get(new Ro(subAreaAttributeConcept, "27.3")).getMemberSet().size());
		assertEquals(7, majorAreaSubArea.getMap().get(new Ro(majorAreaAttributeConcept, "21")).getMemberSet().size());

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
		AttributeDefinition alpha3CodeConcept = new AttributeDefinition();
		Vector<AttributeDefinition> alpha3List = new Vector<AttributeDefinition>();
		alpha3List.add(alpha3CodeConcept);
		Concept speciesConcept = new Concept(new Vector<Ro>(), alpha3List);

		AttributeDefinition asfisDescriptionConcept = new AttributeDefinition();

		AttributeDefinition familyCodeAttributeConcept = new AttributeDefinition();
		Vector<AttributeDefinition> familyList = new Vector<AttributeDefinition>();
		familyList.add(familyCodeAttributeConcept);
		Concept familyConcept = new Concept(new Vector<Ro>(), familyList);

		AttributeDefinition orderCodeAttributeConcept = new AttributeDefinition();
		Vector<AttributeDefinition> orderList = new Vector<AttributeDefinition>();
		orderList.add(orderCodeAttributeConcept);
		Concept orderConcept = new Concept(new Vector<Ro>(), orderList);

		AttributeDefinition iscaapGroupCodeAttributeConcept = new AttributeDefinition();
		Vector<AttributeDefinition> iscaapGroupList = new Vector<AttributeDefinition>();
		iscaapGroupList.add(iscaapGroupCodeAttributeConcept);
		Concept iscaapGroupConcept = new Concept(new Vector<Ro>(), iscaapGroupList);

		Hierarchy familySpecies = new Hierarchy(familyConcept, speciesConcept);
		Hierarchy orderFamily = new Hierarchy(orderConcept, familyConcept);
		Hierarchy iscaapGroupSpecies = new Hierarchy(orderConcept, familyConcept);

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
					Ro species = new Ro(alpha3CodeConcept, alpha3, asfisDescriptionConcept, mls);
					speciesConcept.getRoList().add(species);
					Ro order = null;
					if (!StringUtils.isBlank(nextLine[9])) {
						order = new Ro(orderCodeAttributeConcept, nextLine[9]);
						if (!orderConcept.getRoList().contains(order)) {
							orderConcept.getRoList().add(order);
						}
					}
					if (!StringUtils.isBlank(nextLine[8])) {
						Ro family = new Ro(familyCodeAttributeConcept, nextLine[8]);
						lu.buildHierarchy(familyConcept, familySpecies, family, species);
						if (order != null) {
							lu.buildHierarchy(orderConcept, orderFamily, order, family);
						}
					}

					if (!StringUtils.isBlank(nextLine[0])) {
						Ro iscaapGroup = new Ro(iscaapGroupCodeAttributeConcept, nextLine[0]);
						lu.buildHierarchy(iscaapGroupConcept, iscaapGroupSpecies, iscaapGroup, species);
					}

				}
			}

			reader.close();
		} catch (IOException e) {
			throw new RefPlusException(e);
		}

		assertEquals(12560, speciesConcept.getRoList().size());

		// there is an empty group with an order and relates to a species.("37
		// ""1520300101"" ""LEK"" ""Eumecichthys fiski"" ""Unicorn crestfish""
		// ""(Günther 1890)"" ""Lophotidae"" ""LAMPRIFORMES"" 0" )
		assertEquals(979, familyConcept.getRoList().size());

		assertEquals(140, orderConcept.getRoList().size());
		assertEquals(50, iscaapGroupConcept.getRoList().size());
		assertEquals(37, familySpecies.getMap().get(new Ro(familyCodeAttributeConcept, "Petromyzontidae"))
				.getMemberSet().size());
		assertEquals(5,
				orderFamily.getMap().get(new Ro(orderCodeAttributeConcept, "SQUALIFORMES")).getMemberSet().size());
		assertEquals(886,
				iscaapGroupSpecies.getMap().get(new Ro(iscaapGroupCodeAttributeConcept, "38")).getMemberSet().size());

	}
}
