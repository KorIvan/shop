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
import org.springframework.transaction.annotation.Transactional;

import com.tsystems.model.Person;
import com.tsystems.model.PersonType;

@Repository("clientRepository")
public class PersonRepositoryImpl implements PersonRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Person readPerson(Long id) {
		return em.find(Person.class, id);
	}

	@Override
	@Transactional
	public void createPerson(Person person) {
		em.persist(person);
	}

	@Override
	@Transactional
	public void updatePerson(Person client) {
		em.merge(client);
	}

	@Override
	public boolean validatePerson(Person client) {
		if (em.contains(client))
			return false;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Person> cq = cb.createQuery(Person.class);
		Root<Person> c = cq.from(Person.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.equal(c.get("email"), client.getEmail()));

		cq.select(c).where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Person> q = em.createQuery(cq);
		List<Person> founded = q.getResultList();
		if (founded.isEmpty())
			return true;
		else
			return false;
	}


	@Override
	public Person findClientByEmail(String email) {
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
	@Deprecated
	public boolean validateManager(Person manager) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Person> cq = cb.createQuery(Person.class);
		Root<Person> c = cq.from(Person.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.equal(c.get("email"), manager.getEmail()));
		predicates.add(cb.equal(c.get("password"), manager.getPassword()));
		predicates.add(cb.equal(c.get("type"), PersonType.ROLE_SALES_MANAGER));

		cq.select(c).where(predicates.toArray(new Predicate[] {}));
		TypedQuery<Person> q = em.createQuery(cq);
		List<Person> founded = q.getResultList();
		if (founded.isEmpty())
			return false;
		else {
			manager.setId(founded.get(0).getId());
			return true;
		}
	}
	
	@Override
	public List<Person> getPersons(PersonType type){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Person> cq = cb.createQuery(Person.class);
		Root<Person> c = cq.from(Person.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(c.get("type"), type));
		cq.select(c).where(predicates.toArray(new Predicate[] {}));
		TypedQuery<Person> q = em.createQuery(cq);
		return q.getResultList();
	}
}