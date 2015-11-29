package com.tsystems.repository;

import java.util.List;

import com.tsystems.model.Person;
import com.tsystems.model.Product;
/**
 * PersonRepository provides CRUD-operations with objects of Person class. 
 * @author Ivan
 *
 */
public interface PersonRepository {
	Person readPerson(Integer id);
	boolean createClient(Person client);
	/**
	 * Check uniqueness of Client's email
	 * @param client
	 * @return true, if email is vacant; false, if email is occupied.
	 */
	boolean validateClient(Person client);
	boolean updatePerson(Person client);
//	boolean deletePerson(Person client);
	List<Product> getProductsByCategory(Long categoryId);
}
