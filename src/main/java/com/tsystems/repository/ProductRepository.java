package com.tsystems.repository;

import java.util.List;

import com.tsystems.model.Category;
import com.tsystems.model.Product;
/**
 * ProductRepository provides CRUD-operations with objects of Product class. 
 * @author Ivan
 *
 */
public interface ProductRepository {
	Product readProduct(Integer id);
	boolean createProduct(Product prod);
	boolean updateProduct(Product prod);
	boolean deleteProduct(Product prod);
	/**
	 * Import products from file
	 * @return
	 */
	boolean importProducts();
	/**
	 * Return list of existing categories.
	 * @return
	 */
	List<Category> findAllCategories();
//	
//	<T> List<T> findAll(T toSearch);
	boolean createCategory(Category category);
	boolean validateCategory(Category category);
	boolean validateProduct(Product product);
}
