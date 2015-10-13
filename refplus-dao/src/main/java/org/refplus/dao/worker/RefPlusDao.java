package org.refplus.dao.worker;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.refplus.domain.core.Code;

public class RefPlusDao {

	@Inject
	private EntityManager em;

	public void store(Code code) {
		em.persist(code);
	};

}
