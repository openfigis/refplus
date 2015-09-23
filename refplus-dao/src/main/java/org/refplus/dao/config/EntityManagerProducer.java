package org.refplus.dao.config;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityManagerProducer {

	protected static Logger LOG = LoggerFactory.getLogger(EntityManagerProducer.class);

	@Produces
	private EntityManager getEntityManager() {

		Map<String, String> dbProps = new HashMap<String, String>();

		dbProps.put("eclipselink.logging.level", "");

		EntityManagerFactory fact = Persistence.createEntityManagerFactory("myTestPU", dbProps);
		return fact.createEntityManager();
	}

	@Produces
	@ApplicationScoped
	public EntityManager produceEntityManager() {
		return Persistence.createEntityManagerFactory("refplus-persistence").createEntityManager();
	}

}
