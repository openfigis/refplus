package org.refplus.dao.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityManagerProducer {

	protected static Logger LOG = LoggerFactory.getLogger(EntityManagerProducer.class);

	@Inject
	private PropertyFileResolver propertyFileResolver;

	@Produces
	@ApplicationScoped
	private EntityManager getEntityManager() {
		EntityManagerFactory fact = Persistence.createEntityManagerFactory("refplus-persistence",
				propertyFileResolver.getProperties());
		return fact.createEntityManager();
	}

}
