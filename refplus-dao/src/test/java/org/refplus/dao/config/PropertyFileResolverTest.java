package org.refplus.dao.config;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
// @AdditionalClasses(PropertyFileResolver.class)
public class PropertyFileResolverTest {

	@Inject
	PropertyFileResolver p;

	@BeforeClass
	public static void before() {
		System.setProperty(PropertyFileResolver.LOCATION_NAME, "src/test/resources");
	}

	@Test
	public void test() {
		assertEquals("geluk", p.getProperties().get("dbname"));
		assertEquals("geheim", p.getProperties().get("dbpassword"));

	}

}