package org.refplus.dao.worker;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.refplus.dao.config.EntityManagerProducer;
import org.refplus.dao.config.PropertyFileResolver;
import org.refplus.domain.core.Code;

@RunWith(CdiRunner.class)
@AdditionalClasses(EntityManagerProducer.class)
public class RefPlusDaoTest {

	@Inject
	private RefPlusDao dao;

	@Test
	public void testStore() {
		Code code = new Code(0l, "TUNA");
		dao.store(code);
	};

	@BeforeClass
	public static void before() {
		System.setProperty(PropertyFileResolver.LOCATION_NAME, "src/test/resources");
	}

}
