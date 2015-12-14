package com.tsystems.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.tsystems.model.Person;

@Repository("authenticationRepository")
public class AuthenticationRepositoryImpl implements AuthenticationRepository {
	@PersistenceContext
	private EntityManager em;
	
	public Person findUserByEmail(String email) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Person> cq = cb.createQuery(Person.class);
		Root<Person> c = cq.from(Person.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.equal(c.get("email"), email));

		cq.select(c).where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Person> q = em.createQuery(cq);
		List<Person> founded = q.getResultList();
		if (founded.isEmpty())
			return null;
		else
			return founded.get(0);
	}
}
