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

@Repository("clientRepository")
public class PersonRepositoryImpl implements PersonRepository {
	@PersistenceContext
	private EntityManager em;

	public Person readPerson(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public boolean createClient(Person person) {
		em.persist(person);
		System.out.println("person persisted!");
		em.flush();
		System.out.println("person flushed!");

		return em.contains(person);
	}

	public boolean updatePerson(Person client) {
		// TODO Auto-generated method stub
		return false;
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

}
