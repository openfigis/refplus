package org.refplus.domain.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RoTest {

	@Test
	public void testEquals() {

		AttributeDefinition codeConcept = new AttributeDefinition();

		Ro ro1 = new Ro(codeConcept, "AAA");
		Ro ro2 = new Ro(codeConcept, "AAA");
		Ro ro3 = new Ro(codeConcept, "AAB");
		assertTrue(ro1.equals(ro2));
		assertFalse(ro1.equals(ro3));

	}

}
