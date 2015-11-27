package com.tsystems.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tsystems.model.Category;
import com.tsystems.model.Product;

@Repository("productRepository")
public class ProductRepositoryImpl implements ProductRepository {
	@PersistenceContext
	private EntityManager em;

	public Product readProduct(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public boolean createProduct(Product prod) {
		// Map<Property, PropertyBody> map = prod.getProperties();
		// Iterable<Property> i = map.keySet();
		// for (Property prop : i) {
		// if(!em.contains(prop))
		// em.persist(prop);
		// }
		// em.flush();
		em.persist(prod);

		em.flush();
		return em.contains(prod);// TODO Auto-generated method stub
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
		Query query = em.createQuery("SELECT e FROM CATEGORY e");
	    return query.getResultList();
	}

}
