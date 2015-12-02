package com.tsystems.repository;

import java.util.List;

import com.tsystems.model.Address;
import com.tsystems.model.Category;
import com.tsystems.model.Order;
import com.tsystems.model.Person;
import com.tsystems.model.Product;
import com.tsystems.model.User;
/**
 * PersonRepository provides CRUD-operations with objects of Person class. 
 * @author Ivan
 *
 */
public interface PersonRepository {
	Person readPerson(Long id);
	void createClient(Person client);
	/**
	 * Check uniqueness of Client's email
	 * @param client
	 * @return true, if email is vacant; false, if email is occupied.
	 */
	boolean validateClient(Person client);
	/**
	 * 
	 * @param client
	 * @return true, if Person updated successfully
	 */
	void updatePerson(Person client);
//	boolean deletePerson(Person client);
	/**
	 * Retrieve all Products under selected Category
	 * @param categoryId
	 * @return List<Product>
	 */
	List<Product> getProductsByCategory(Long categoryId);
	/**
	 * Retrieve all existing Categories
	 * @return List<Category>
	 */
	List<Category> findAllCategories();
	/**
	 * Vaildate client for authorization
	 * @param user
	 * @return
	 */
	boolean validateClient(User user);
	void createOrder(Order order);
	List<Address> findAllAddresses(Long clientId);
	List<Order> findAllOrders(Long clientId);
	/**
	 * Search in Client's Orders Order with deliveryMethod=Unknown, payMethod=Unknown.
	 * If there is one, return true.
	 * @param clientId
	 * @return
	 */
	boolean hasUnfinishedOrder(Long clientId);
	
	void updateOrder(Order order);
	/**
	 * Method call deletes cancelled order
	 * @param order
	 */
	void deleteOrder(Order order);
	
	Address findAddressById(Long id);
}
