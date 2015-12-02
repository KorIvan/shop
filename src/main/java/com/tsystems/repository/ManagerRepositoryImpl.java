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
import com.tsystems.model.Order;
import com.tsystems.model.OrderItem;
import com.tsystems.model.Person;
import com.tsystems.model.PersonType;
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
	public void createProduct(Product product) {
		em.persist(product);
		// em.flush();
		// return em.contains(product);
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
	public void updateProduct(Product prod) {
		em.merge(prod);
	}

	// @Transactional
	// public void deleteProduct(Product prod) {
	// // TODO Auto-generated method stub
	// return false;
	// }

	public void importProducts() {
		// TODO Auto-generated method stub
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

	public List<Order> findAllOrders() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> c = cq.from(Order.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		cq.select(c).where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Order> q = em.createQuery(cq);
		return q.getResultList();
	}

	@Transactional
	public void createCategory(Category category) {
		em.persist(category);
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
		predicates.add(cb.equal(c.get("type"), PersonType.SALES_MANAGER));

		cq.select(c).where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Person> q = em.createQuery(cq);
		List<Person> founded = q.getResultList();
		if (founded.isEmpty())
			return false;
		else {
			user.setId(founded.get(0).getId());
			return true;
		}
	}

	public void updateCategory(Category category) {
		em.merge(category);

	}

	public Product findProductById(Long prodId) {
		return em.find(Product.class, prodId);
	}

	public OrderItem findOrderItemById(Long id) {
		return em.find(OrderItem.class, id);
	}

}
