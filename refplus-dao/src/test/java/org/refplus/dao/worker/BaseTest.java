package org.refplus.dao.worker;

import org.junit.BeforeClass;
import org.refplus.dao.config.PropertyFileResolver;

public abstract class BaseTest {

	@BeforeClass
	public static void before() {
		System.setProperty(PropertyFileResolver.LOCATION_NAME, "src/test/resources");
	}

}
