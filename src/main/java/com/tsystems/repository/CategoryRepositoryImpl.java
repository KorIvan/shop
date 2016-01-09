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
@Repository("categoryRepository")
public class CategoryRepositoryImpl implements CategoryRepository {
	@PersistenceContext
	EntityManager em;

	public List<Category> findAllCategories() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		Root<Category> c = cq.from(Category.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		cq.select(c).where(predicates.toArray(new Predicate[] {}));
		TypedQuery<Category> q = em.createQuery(cq);
		return q.getResultList();
	}
	public Category findCategoryById(Long id) {
		return em.find(Category.class, id);
	}
	@Override
	@Transactional
	public void updateCategory(Category category) {
		em.merge(category);

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
}
