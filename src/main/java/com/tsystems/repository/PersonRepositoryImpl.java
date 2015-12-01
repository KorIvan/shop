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

import com.tsystems.model.Category;
import com.tsystems.model.Person;
import com.tsystems.model.Product;
import com.tsystems.model.User;

@Repository("clientRepository")
public class PersonRepositoryImpl implements PersonRepository {
	@PersistenceContext
	private EntityManager em;

	public Person readPerson(Long id) {
		return em.find(Person.class, id);
	}

	@Transactional
	public boolean createClient(Person person) {
		em.persist(person);
		System.out.println("person persisted!");
		em.flush();
		System.out.println("person flushed!");

		return em.contains(person);
	}
	@Transactional
	public boolean updatePerson(Person client) {
	em.merge(client);
		em.flush();
		return true;
	}

	public boolean validateClient(Person client) {
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

	public List<Product> getProductsByCategory(Long categoryId) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		Root<Product> c = cq.from(Product.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.equal(c.get("category"), em.find(Category.class, categoryId)));

		cq.select(c).where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Product> q = em.createQuery(cq);
		List<Product> founded = q.getResultList();
		return founded;
	}

	public List<Category> findAllCategories() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		Root<Category> c = cq.from(Category.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		cq.select(c).where(predicates.toArray(new Predicate[] {}));
		TypedQuery<Category> q = em.createQuery(cq);
		return q.getResultList();
	}

	public boolean validateClient(User user) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Person> cq = cb.createQuery(Person.class);
		Root<Person> c = cq.from(Person.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.equal(c.get("email"), user.getEmail()));
		predicates.add(cb.equal(c.get("password"), user.getPassword()));
		predicates.add(cb.equal(c.get("type"), user.getType()));
		cq.select(c).where(predicates.toArray(new Predicate[] {}));
		TypedQuery<Person> q = em.createQuery(cq);
		List<Person> founded = q.getResultList();
		if (founded.isEmpty())
			return false;
		else {
			user.setId(founded.get(0).getId());
			System.out.println(founded.get(0).getId()+" in repo");
			System.out.println(user.getId()+" in repo current user id");
			return true;
		}
	}


}
