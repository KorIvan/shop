package com.tsystems.repository;

import java.util.List;

import com.tsystems.model.Category;
import com.tsystems.model.Product;
import com.tsystems.model.User;
/**
 * ProductRepository provides CRUD-operations with objects of Product class. 
 * @author Ivan
 *
 */
public interface ManagerRepository <T>{
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
	T getById(Long id, Class<T> T);
	Category findCategoryById(Long id);
	boolean validateManager(User user);
}
