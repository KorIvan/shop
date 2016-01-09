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
import com.tsystems.model.Product;

@Repository("productRepository")
public class ProductRepositoryImpl implements ProductRepository {
	@PersistenceContext
	protected EntityManager em;

	@Override
	@Transactional
	public void createProduct(Product product) {
		em.persist(product);
	}

	@Override
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

	@Override
	@Transactional
	public void updateProduct(Product prod) {
		em.merge(prod);
	}

	@Override
	public List<Product> findProductsByCategory(Long categoryId) {

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

	@Override
	public Product findProductById(Long prodId) {
		return em.find(Product.class, prodId);
	}

}
