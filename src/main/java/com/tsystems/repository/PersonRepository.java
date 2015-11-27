package com.tsystems.repository;

import com.tsystems.model.Person;
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
}
