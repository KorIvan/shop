package com.tsystems.repository;

import java.util.List;

import com.tsystems.model.Category;
import com.tsystems.model.Order;
import com.tsystems.model.OrderItem;
import com.tsystems.model.Product;
import com.tsystems.model.User;
/**
 * ProductRepository provides CRUD-operations with objects of Product class. 
 * @author Ivan
 *
 */
public interface ManagerRepository <T>{
	Product readProduct(Integer id);
	
	void createProduct(Product prod);
	
	void updateProduct(Product prod);
	
//	void deleteProduct(Product prod);
	/**
	 * Import products from file
	 * @return
	 */
	void importProducts();
	
	/**
	 * Return list of existing categories.
	 * @return
	 */
	List<Category> findAllCategories();
//	
//	<T> List<T> findAll(T toSearch);
	void createCategory(Category category);
	/**
	 * Check category for uniqueness
	 * @param category
	 * @return true, if there is not other category with the same name
	 */
	boolean validateCategory(Category category);
	
	/**
	 * Check poduct for uniqueness under Category
	 * @param category
	 * @return true, if there is not other product with the same name
	 */
	boolean validateProduct(Product product);
	
//	T getById(Long id, Class<T> T);
	Category findCategoryById(Long id);
	/**
	 * Check manager's email and login and role.
	 * @param user
	 * @return
	 */
	boolean validateManager(User user);
	
	List<Order> findAllOrders();

	void updateCategory(Category category);

	Product findProductById(Long prodId);

	OrderItem findOrderItemById(Long id);
}
