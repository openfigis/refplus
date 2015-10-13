package org.refplus.dao.worker;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.refplus.dao.config.EntityManagerProducer;
import org.refplus.domain.Bucket;
import org.refplus.domain.Concept;
import org.refplus.domain.Hierarchy;
import org.refplus.domain.Top;
import org.refplus.domain.core.AttributeDefinition;
import org.refplus.domain.core.Code;
import org.refplus.domain.core.Group;
import org.refplus.domain.core.MultiLingualString;
import org.refplus.domain.core.Ro;
import org.refplus.domain.core.StringAttribute;

@RunWith(CdiRunner.class)
@AdditionalClasses(EntityManagerProducer.class)
public class RefPlusDaoTest extends BaseTest {

	Object[] testObjectsBase = { new AttributeDefinition(MultiLingualString.class, "schema"), new Code("TUNA"),
			new Group(), new MultiLingualString(), new Ro(), new StringAttribute() };

	Object[] testObjectsCore = { new Bucket(), new Concept(), new Hierarchy(), new Top() };

	@Inject
	private EntityManager em;

	@Test
	public void testPeristBase() {
		for (Object o : testObjectsBase) {
			em.persist(o);
		}
	}

	@Test
	public void testPeristCore() {
		for (Object o : testObjectsCore) {
			em.persist(o);
		}
	}

}
