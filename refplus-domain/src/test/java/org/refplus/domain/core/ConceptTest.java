package org.refplus.domain.core;

import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.refplus.RefPlusException;

import au.com.bytecode.opencsv.CSVReader;

public class ConceptTest {
	public static String csvFileName = "src/test/resources/CL_SPECIES_1_3.csv";
	public static String csvFileNameArea = "src/test/resources/CL_PRODUCTION_AREA_0.csv";



	/**
	 * 
	 * http://figisapps.fao.org/FIGISwiki/index.php/FMA_SDMX_Codelist
	 * 
	 * 0 OBJECTID,N,9,0
	 * 1 OCEAN,C,16
	 * 2 SUBOCEAN,C,16
	 * 3 F_AREA,C,16
	 * 4 F_SUBAREA,C,16
	 * 5 F_SUBUNIT,C,16
	 * 6 F_DIVISION,C,16
	 * 7 F_SUBDIVIS,C,16
	 * 8 Shape_Leng,N,19,11
	 * 9 Shape_Area,N,19,11
	 */
	
	@SuppressWarnings("unused")
	private static int AREA_OBJECT_ID = 0;
	private static int AREA_OCEAN = 1;  		// Ocean_Name
	private static int AREA_SUBOCEAN = 2;		// ID
	private static int AREA_AREA = 3;			// ID
	private static int AREA_SUBAREA = 4;		// ID(Area).ID(SubArea)
	private static int AREA_SUBUNIT = 5;		// ID(Area).ID(SubArea).ID(Div).ID(SubDiv).ID(SubUnit)
	private static int AREA_DIVISION = 6;		// ID(Area).ID(SubArea).ID(Div)
	private static int AREA_SUBDIVISION = 7;	// ID(Area).ID(SubArea).ID(Div).ID(SubDiv)
	@SuppressWarnings("unused")
	private static int AREA_shape_len = 8;
	@SuppressWarnings("unused")
	private static int AREA_shape_area = 9;
	
	private static String WATER_AREA = "Water Area";

	private Codelist subUnitGroup      = new Codelist("subUnitGroup");
	private Codelist subDivisionGroup  = new Codelist("subDivisionGroup");
	private Codelist divisionGroup     = new Codelist("divisionGroup");
	private Codelist subAreaGroup      = new Codelist("subAreaGroup");
	private Codelist majorAreaGroup    = new Codelist("majorAreaGroup");
	private Codelist subOceanGroup     = new Codelist("subOceanGroup");
	private Codelist oceanGroup        = new Codelist("oceanGroup");
	private Codelist codelistWaterArea = new Codelist("codelistWaterArea");
	
	@Test
	public void testConceptAreas() {


		
		Top refplus = new Top();
		refplus.topHierarchies.add(subUnitGroup);
		refplus.topHierarchies.add(subDivisionGroup);
		refplus.topHierarchies.add(divisionGroup);
		refplus.topHierarchies.add(subAreaGroup);
		refplus.topHierarchies.add(majorAreaGroup);
		refplus.topHierarchies.add(subOceanGroup);
		refplus.topHierarchies.add(oceanGroup);
		refplus.topCodelists.add(codelistWaterArea);
		refplus.hierarchyNames.add(WATER_AREA);
		
		try {
			CSVReader reader = new CSVReader(new FileReader(csvFileNameArea));

			reader.readNext();
			String[] nextLine;

			while ((nextLine = reader.readNext()) != null) {
				// check for valid liens in CSV
				if (!StringUtils.isBlank(nextLine[AREA_SUBOCEAN])) {

					// add the ocean
					Ro aOcean = findCreateRo(oceanGroup, nextLine[AREA_OCEAN]);
					oceanGroup.add2Codelist( nextLine[AREA_OCEAN], aOcean);

					// add the sub-ocean
					Ro subOcean = findCreateRo(subOceanGroup, nextLine[AREA_OCEAN]);
					findAddGrouping(WATER_AREA, aOcean, subOcean);
					subOceanGroup.add2Codelist(nextLine[AREA_SUBOCEAN], subOcean);

					// add the major area
					Ro majorArea = findCreateRo(majorAreaGroup, nextLine[AREA_AREA]);
					findAddGrouping(WATER_AREA, subOcean, majorArea);
					majorAreaGroup.add2Codelist(nextLine[AREA_AREA], majorArea);

					// add the sub-area
					if (!StringUtils.isBlank(nextLine[AREA_SUBAREA])) {
						Ro subArea = findCreateRo(subAreaGroup, nextLine[AREA_SUBAREA]);
						findAddGrouping(WATER_AREA, majorArea, subArea);
						subAreaGroup.add2Codelist(nextLine[AREA_SUBAREA], subArea);
						
						if (!StringUtils.isBlank(nextLine[AREA_DIVISION])) {
							Ro areaDivision = findCreateRo(divisionGroup, nextLine[AREA_DIVISION]);
							findAddGrouping(WATER_AREA, subArea, areaDivision);
							divisionGroup.add2Codelist(nextLine[AREA_DIVISION], areaDivision);

							if (!StringUtils.isBlank(nextLine[AREA_SUBDIVISION])) {
								Ro areaSubDivision = findCreateRo(subDivisionGroup, nextLine[AREA_SUBDIVISION]);
								findAddGrouping(WATER_AREA, areaDivision, areaSubDivision);
								subDivisionGroup.add2Codelist(nextLine[AREA_SUBDIVISION], areaSubDivision);

								if (!StringUtils.isBlank(nextLine[AREA_SUBUNIT])) {
									Ro areaSubUnit = findCreateRo(subUnitGroup, nextLine[AREA_SUBUNIT]);
									findAddGrouping(WATER_AREA, areaSubDivision, areaSubUnit);
									subUnitGroup.add2Codelist(nextLine[AREA_SUBUNIT], areaSubUnit);
								}
							}
						}
					}
					else {
						// parent is a major-area, not a sub-area
						if (!StringUtils.isBlank(nextLine[AREA_DIVISION])) {
							Ro areaDivision = findCreateRo(divisionGroup, nextLine[AREA_DIVISION]);
							findAddGrouping(WATER_AREA, majorArea, areaDivision);
							divisionGroup.add2Codelist(nextLine[AREA_DIVISION], areaDivision);

							if (!StringUtils.isBlank(nextLine[AREA_SUBDIVISION])) {
								Ro areaSubDivision = findCreateRo(subDivisionGroup, nextLine[AREA_SUBDIVISION]);
								findAddGrouping(WATER_AREA, areaDivision, areaSubDivision);
								subDivisionGroup.add2Codelist(nextLine[AREA_SUBDIVISION], areaSubDivision);

								if (!StringUtils.isBlank(nextLine[AREA_SUBUNIT])) {
									Ro areaSubUnit = findCreateRo(subUnitGroup, nextLine[AREA_SUBUNIT]);
									findAddGrouping(WATER_AREA, areaSubDivision, areaSubUnit);
									subUnitGroup.add2Codelist(nextLine[AREA_SUBUNIT], areaSubUnit);
								}
							}
						}
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			throw new RefPlusException(e);
		}

		assertEquals(2, subUnitGroup.getCodelist().size());
		assertEquals(61, subDivisionGroup.getCodelist().size());
		assertEquals(92, subAreaGroup.getCodelist().size());
		assertEquals(19, majorAreaGroup.getCodelist().size());

		assertEquals(2, subDivisionGroup.locateCodeByName("21.5.Z.e").getGroups().get(WATER_AREA).size());
		assertEquals(11, subAreaGroup.locateCodeByName("27.3").getGroups().get(WATER_AREA).size());
		assertEquals(7, majorAreaGroup.locateCodeByName("21").getGroups().get(WATER_AREA).size());

	}

	private Ro findCreateRo(Codelist someGroup, String codeString) {

		if (StringUtils.isBlank(codeString))
			return(null);
		
		Ro foundIT = someGroup.locateCodeByName(codeString);
		if (foundIT != null)
			return (foundIT);
		
		return (new Ro(codeString));
	}
	
	private void findAddGrouping (String hierarchyName, Ro aGroup, Ro aMember) {
		String name = aMember.getAttribute("name");
		if (aGroup.getGroupItemByName(hierarchyName, name) != null)
			return;
		
		if (aGroup.getGroupByName(hierarchyName) == null)
			aGroup.getGroups().put(hierarchyName, new Vector<Ro>());
		
		aGroup.getGroupByName(hierarchyName).add(aMember);
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
	 *

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
					Ro species = new Ro(alpha3CodeConcept, alpha3, asfisDescriptionConcept, mls);
					speciesConcept.getRoList().add(species);

					Ro family = new Ro(familyCodeAttributeConcept, nextLine[8]);
					Ro order = new Ro(orderCodeAttributeConcept, nextLine[9]);
					Ro iscaapGroup = new Ro(iscaapGroupCodeAttributeConcept, nextLine[0]);

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
		assertEquals(37, familySpecies.getMap().get(new Ro(familyCodeAttributeConcept, "Petromyzontidae"))
				.getMemberSet().size());
		assertEquals(6, orderFamily.getMap().get(new Ro(orderCodeAttributeConcept, "SQUALIFORMES")).getMemberSet()
				.size());
		assertEquals(886, iscaapGroupSpecies.getMap().get(new Ro(iscaapGroupCodeAttributeConcept, "38")).getMemberSet()
				.size());

	}*/
}
