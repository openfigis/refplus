package org.refplus.domain.core;

import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.refplus.RefPlusException;
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
	
	private static int AREA_OBJECT_ID = 0;
	private static int AREA_OCEAN = 1;  		// Ocean_Name
	private static int AREA_SUBOCEAN = 2;		// ID
	private static int AREA_AREA = 3;			// ID
	private static int AREA_SUBAREA = 4;		// ID(Area).ID(SubArea)
	private static int AREA_SUBUNIT = 5;		// ID(Area).ID(SubArea).ID(Div).ID(SubDiv).ID(SubUnit)
	private static int AREA_DIVISION = 6;		// ID(Area).ID(SubArea).ID(Div)
	private static int AREA_SUBDIVISION = 7;	// ID(Area).ID(SubArea).ID(Div).ID(SubDiv)
	private static int AREA_shape_len = 8;
	private static int AREA_shape_area = 9;

	private Ro subUnitGroup      = new Ro("subUnitGroup");
	private Ro subDivisionGroup  = new Ro("subDivisionGroup");
	private Ro divisionGroup     = new Ro("divisionGroup");
	private Ro subAreaGroup      = new Ro("subAreaGroup");
	private Ro majorAreaGroup    = new Ro("majorAreaGroup");
	private Ro subOceanGroup     = new Ro("subOceanGroup");
	private Ro oceanGroup        = new Ro("oceanGroup");
	private Ro codelistWaterArea = new Ro("codelistWaterArea");
	
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
		
		try {
			CSVReader reader = new CSVReader(new FileReader(csvFileNameArea));

			reader.readNext();
			String[] nextLine;
			LinkUtil lu = new LinkUtil();

			while ((nextLine = reader.readNext()) != null) {
				// check for valid liens in CSV
				if (!StringUtils.isBlank(nextLine[AREA_SUBOCEAN])) {

					// add the ocean
					addHierarchy(oceanGroup, nextLine[AREA_OCEAN]);
					
					// add the major area
					addHierarchy(subOceanGroup, nextLine[AREA_SUBOCEAN]);
					
					// add the sub-ocean
					addHierarchy(majorAreaGroup, nextLine[AREA_AREA]);
					
					if (!StringUtils.isBlank(nextLine[AREA_DIVISION])) {
						addHierarchy(divisionGroup, nextLine[AREA_DIVISION]);
					}

					if (!StringUtils.isBlank(nextLine[AREA_SUBDIVISION])) {
						addHierarchy(subDivisionGroup, nextLine[AREA_SUBDIVISION]);
					}
					
					if (!StringUtils.isBlank(nextLine[AREA_SUBUNIT])) {
						addHierarchy(subUnitGroup, nextLine[AREA_SUBUNIT]);
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			throw new RefPlusException(e);
		}

		assertEquals(2, subUnitGroup.getGroups().get("subUnitGroup").size());
		assertEquals(61, subDivisionGroup.getGroups().get("subDivisionGroup").size());
		assertEquals(92, subAreaGroup.getGroups().get("subAreaGroup").size());
		assertEquals(19, majorAreaGroup.getGroups().get("majorAreaGroup").size());

		assertEquals(2, subDivisionGroup.getGroupItemByName("subDivisionGroup", "21.5.Z.e").getGroups().get("21.5.Z.e").size());
		assertEquals(11, subDivisionGroup.getGroupItemByName("subDivisionGroup", "27.3").getGroups().get("27.3").size());
		assertEquals(7, majorAreaGroup.getGroupItemByName("majorAreaGroup", "21").getGroups().get("21").size());

	}

	private void addHierarchy(Ro someGroup, String codeString) {

		if (StringUtils.isBlank(codeString))
			return;
		
		// get the codelist Ro and create if needed
		Ro codeElement = codelistWaterArea.getGroupItemByName("codelist", codeString);
		if (codeElement == null) {
			codeElement = new Ro(codeString);
			codelistWaterArea.getGroups().get("codelist").add(codeElement);
			
		}
		
		// check if the Ro already exists in the group
		String hirarchyName = someGroup.getAttribute("name");
		if (!someGroup.getGroups().containsKey(hirarchyName)) {
			Vector<Ro> groups = someGroup.getGroups().get(hirarchyName);
			groups.add(codeElement);
		}
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

	}
}
