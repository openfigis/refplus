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
	public static String csvFileSpecies = "src/test/resources/CL_SPECIES_1_3.csv";
	public static String csvFileArea = "src/test/resources/CL_PRODUCTION_AREA_0.csv";



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
	
	private static String HIERARCHY_WATER_AREA = "Water Area";

	private Hierarchy subUnitGroup      = new Hierarchy("subUnitGroup");
	private Hierarchy subDivisionGroup  = new Hierarchy("subDivisionGroup");
	private Hierarchy divisionGroup     = new Hierarchy("divisionGroup");
	private Hierarchy subAreaGroup      = new Hierarchy("subAreaGroup");
	private Hierarchy majorAreaGroup    = new Hierarchy("majorAreaGroup");
	private Hierarchy subOceanGroup     = new Hierarchy("subOceanGroup");
	private Hierarchy oceanGroup        = new Hierarchy("oceanGroup");
	private Concept  codelistWaterArea  = new Concept("codelistWaterArea");
	

	
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
		refplus.topConcepts.add(codelistWaterArea);
		
		try {
			CSVReader reader = new CSVReader(new FileReader(csvFileArea));

			reader.readNext();
			String[] nextLine;

			while ((nextLine = reader.readNext()) != null) {
				// check for valid liens in CSV
				if (!StringUtils.isBlank(nextLine[AREA_SUBOCEAN])) {

					// add the ocean
					Ro aOcean = findCreateRo(oceanGroup, nextLine[AREA_OCEAN]);
					oceanGroup.add2Hierarchy(aOcean);
					codelistWaterArea.add2Concept(aOcean);

					// add the sub-ocean
					Ro subOcean = findCreateRo(subOceanGroup, nextLine[AREA_OCEAN]);
					findAddGrouping(HIERARCHY_WATER_AREA, aOcean, subOcean);
					subOceanGroup.add2Hierarchy(subOcean);
					codelistWaterArea.add2Concept(subOcean);

					// add the major area
					Ro majorArea = findCreateRo(majorAreaGroup, nextLine[AREA_AREA]);
					findAddGrouping(HIERARCHY_WATER_AREA, subOcean, majorArea);
					majorAreaGroup.add2Hierarchy(majorArea);
					codelistWaterArea.add2Concept(majorArea);

					// add the sub-area
					if (!StringUtils.isBlank(nextLine[AREA_SUBAREA])) {
						Ro subArea = findCreateRo(subAreaGroup, nextLine[AREA_SUBAREA]);
						findAddGrouping(HIERARCHY_WATER_AREA, majorArea, subArea);
						subAreaGroup.add2Hierarchy(subArea);
						codelistWaterArea.add2Concept(subArea);
						
						if (!StringUtils.isBlank(nextLine[AREA_DIVISION])) {
							Ro areaDivision = findCreateRo(divisionGroup, nextLine[AREA_DIVISION]);
							findAddGrouping(HIERARCHY_WATER_AREA, subArea, areaDivision);
							divisionGroup.add2Hierarchy(areaDivision);
							codelistWaterArea.add2Concept(areaDivision);

							if (!StringUtils.isBlank(nextLine[AREA_SUBDIVISION])) {
								Ro areaSubDivision = findCreateRo(subDivisionGroup, nextLine[AREA_SUBDIVISION]);
								findAddGrouping(HIERARCHY_WATER_AREA, areaDivision, areaSubDivision);
								subDivisionGroup.add2Hierarchy(areaSubDivision);
								codelistWaterArea.add2Concept(areaSubDivision);

								if (!StringUtils.isBlank(nextLine[AREA_SUBUNIT])) {
									Ro areaSubUnit = findCreateRo(subUnitGroup, nextLine[AREA_SUBUNIT]);
									findAddGrouping(HIERARCHY_WATER_AREA, areaSubDivision, areaSubUnit);
									subUnitGroup.add2Hierarchy(areaSubUnit);
									codelistWaterArea.add2Concept(areaSubUnit);
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

		assertEquals(2, subUnitGroup.getHierarchy().size());
		assertEquals(61, subDivisionGroup.getHierarchy().size());
		assertEquals(92, subAreaGroup.getHierarchy().size());
		assertEquals(19, majorAreaGroup.getHierarchy().size());
		assertEquals(336, codelistWaterArea.getConceptlist().size());

		assertEquals(2, subDivisionGroup.locateHierarchyByName("21.5.Z.e").getGroups().get(HIERARCHY_WATER_AREA).size());
		assertEquals(9, divisionGroup.locateHierarchyByName("27.3.d").getGroups().get(HIERARCHY_WATER_AREA).size());
		// NOTE: 27.3 has only 4 divisions, not 11
		assertEquals(4, subAreaGroup.locateHierarchyByName("27.3").getGroups().get(HIERARCHY_WATER_AREA).size());
		assertEquals(7, majorAreaGroup.locateHierarchyByName("21").getGroups().get(HIERARCHY_WATER_AREA).size());

	}

	private Ro findCreateRo(Hierarchy someGroup, String codeString) {

		if (StringUtils.isBlank(codeString))
			return(null);
		
		Ro foundIT = someGroup.locateHierarchyByName(codeString);
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
	 * 1 TAXOCODE
	 * 2 3A_CODE
	 * 3 Scientific_name
	 * 4 English_name
	 * 5 French_name
	 * 6 Spanish_name
	 * 7 Author
	 * 8 Family 
	 * 9 Order
	 * 10 Stats_data
	 */
	
	private static int SPECIES_ISSCAAP = 0;
	@SuppressWarnings("unused")
	private static int SPECIES_TAXOCODE = 1;
	private static int SPECIES_3ACODE = 2;
	private static int SPECIES_SCIENTIFIC = 3;
	private static int SPECIES_ENGLISH = 4;
	private static int SPECIES_FRENCH = 5;
	private static int SPECIES_SPANISH = 6;
	private static int SPECIES_AUTHOR = 7;
	private static int SPECIES_FAMILY = 8;
	private static int SPECIES_ORDER = 9;

	private static String ATTRIB_3ALFA = "3AlfaCODE";
	private static String ATTRIB_SCIENTIFIC_NAME = "Scientific Name";
	private static String ATTRIB_ML_NAME = "Multilingual Name";
	private static String ATTRIB_AUTHOR = "Author";

	private static String HIERARCHY_FAMILY = "Family";
	private static String HIERARCHY_ORDER = "Order";
	private static String HIERARCHY_ISSCAAP = "ISSCAAP";
	
	private Hierarchy familyHierarchy     = new Hierarchy("familyCodeAttributeConcept");
	private Hierarchy orderHierarchy      = new Hierarchy("orderCodeAttributeConcept");
	private Hierarchy iscaapHierarchy     = new Hierarchy("iscaapGroupCodeAttributeConcept");
	private Concept  speciesCodelist     = new Concept("Species");

	@Test
	public void testConceptSpecies() {
	
		Top refplus = new Top();
		refplus.topHierarchies.add(familyHierarchy);
		refplus.topHierarchies.add(orderHierarchy);
		refplus.topHierarchies.add(iscaapHierarchy);
		refplus.topConcepts.add(speciesCodelist);
			
		try {
			CSVReader reader = new CSVReader(new FileReader(csvFileSpecies), '\t');

			reader.readNext();
			String[] nextLine;

			while ((nextLine = reader.readNext()) != null) {

				if (!StringUtils.isBlank(nextLine[SPECIES_3ACODE])) {

					Ro species = new Ro(nextLine[SPECIES_3ACODE]);
					species.setAttributeML(ATTRIB_ML_NAME, Lang.EN, nextLine[SPECIES_ENGLISH]);
					species.setAttributeML(ATTRIB_ML_NAME, Lang.FR, nextLine[SPECIES_FRENCH]);
					species.setAttributeML(ATTRIB_ML_NAME, Lang.SP, nextLine[SPECIES_SPANISH]);
					species.getCodeMap().put(ATTRIB_3ALFA, nextLine[SPECIES_3ACODE]);
					species.getAttributeMap().put(ATTRIB_SCIENTIFIC_NAME, nextLine[SPECIES_SCIENTIFIC]);
					species.getAttributeMap().put(ATTRIB_AUTHOR, nextLine[SPECIES_AUTHOR]);
					
					speciesCodelist.add2Concept(species);

					if (!StringUtils.isBlank(nextLine[SPECIES_FAMILY])) {
						Ro family = findCreateRo(familyHierarchy, nextLine[SPECIES_FAMILY]);
						findAddGrouping(HIERARCHY_FAMILY, family, species);
						familyHierarchy.add2Hierarchy(family);
					}
					if (!StringUtils.isBlank(nextLine[SPECIES_ORDER])) {
						Ro order = findCreateRo(orderHierarchy, nextLine[SPECIES_ORDER]);
						findAddGrouping(HIERARCHY_ORDER, order, species);
						orderHierarchy.add2Hierarchy(order);
					}
					if (!StringUtils.isBlank(nextLine[SPECIES_ISSCAAP])) {
						Ro isscaap = findCreateRo(iscaapHierarchy, nextLine[SPECIES_ISSCAAP]);
						findAddGrouping(HIERARCHY_ISSCAAP, isscaap, species);
						iscaapHierarchy.add2Hierarchy(isscaap);
					}
				}
			}

			reader.close();
		} catch (Exception e) {
			throw new RefPlusException(e);
		}

		assertEquals(12560, speciesCodelist.getConceptlist().size());
		assertEquals(979, familyHierarchy.getHierarchy().size());
		assertEquals(140, orderHierarchy.getHierarchy().size());
		assertEquals(50, iscaapHierarchy.getHierarchy().size());	// 51
		
		assertEquals(37, familyHierarchy.locateHierarchyByName("Petromyzontidae").getGroups().get(HIERARCHY_FAMILY).size());
		assertEquals(114, orderHierarchy.locateHierarchyByName("SQUALIFORMES").getGroups().get(HIERARCHY_ORDER).size());	// 6
		assertEquals(886, iscaapHierarchy.locateHierarchyByName("38").getGroups().get(HIERARCHY_ISSCAAP).size());
		
	}
}
