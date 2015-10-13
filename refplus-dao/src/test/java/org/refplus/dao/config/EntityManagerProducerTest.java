package org.refplus.dao.config;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
@AdditionalClasses(EntityManagerProducer.class)
public class EntityManagerProducerTest {

	@Inject
	EntityManager em;

	@Test
	public void test() {
		assertNotNull(em);
	}

}