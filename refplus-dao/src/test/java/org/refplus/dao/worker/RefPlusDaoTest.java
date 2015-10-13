package org.refplus.dao.worker;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.refplus.dao.config.EntityManagerProducer;
import org.refplus.domain.core.Code;

@RunWith(CdiRunner.class)
@AdditionalClasses(EntityManagerProducer.class)
public class RefPlusDaoTest extends BaseTest {

	@Inject
	private RefPlusDao dao;

	@Test
	public void testCode() {
		Code code = new Code("TUNA");
		dao.store(code);
	};

}
