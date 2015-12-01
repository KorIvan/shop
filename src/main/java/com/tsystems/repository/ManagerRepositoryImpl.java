package com.tsystems.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

@Repository("managerRepository")
public class ManagerRepositoryImpl<T> implements ManagerRepository {
	@PersistenceContext
	private EntityManager em;

	public Product readProduct(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public boolean createProduct(Product product) {
		em.persist(product);
		em.flush();
		return em.contains(product);
	}
	public boolean validateProduct(Product product) {
		if (em.contains(product))
			return false;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		Root<Product> c = cq.from(Product.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.equal(c.get("name"), product.getName()));
		predicates.add(cb.equal(c.get("category"), product.getCategory()));

		cq.select(c).where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Product> q = em.createQuery(cq);
		List<Product> founded = q.getResultList();
		if (founded.isEmpty())
			return true;
		else
			return false;
	}
	@Transactional
	public boolean updateProduct(Product prod) {
		// TODO Auto-generated method stub
		return false;
	}

	@Transactional
	public boolean deleteProduct(Product prod) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean importProducts() {
		// TODO Auto-generated method stub
		return false;
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
@Transactional
	public boolean createCategory(Category category) {
		em.persist(category);
		em.flush();
		return em.contains(category);
	}

	public boolean validateCategory(Category category) {
		if (em.contains(category))
			return false;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		Root<Category> c = cq.from(Category.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.equal(c.get("name"), category.getName()));

		cq.select(c).where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Category> q = em.createQuery(cq);
		List<Category> founded = q.getResultList();
		if (founded.isEmpty())
			return true;
		else
			return false;
	}

//	public T getById(Long id, Class<T> T) {
//		
//	}

	public Object getById(Long id, Class T) {
		return em.find(T, id);
	}

	public Category findCategoryById(Long id) {
		return em.find(Category.class, id);
	}

	public boolean validateManager(User user) {
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
		else{
			user.setId(founded.get(0).getId());
			return true;}
	}

	
	
	

}
