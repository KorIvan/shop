package com.tsystems.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.tsystems.model.Person;
import com.tsystems.model.PersonType;
/**
 * ClientRepository provides CRUD-operations with objects of Person class. 
 * @author Ivan
 *
 */
public interface PersonRepository {
	/**
	 * Check manager's email and login and role.
	 * @param user
	 * @return
	 */
	Person readPerson(Long id);
	void createPerson(Person client);
	/**
	 * Check uniqueness of Person's email
	 * @param client
	 * @return true, if email is vacant; false, if email is occupied.
	 */
	boolean validatePerson(Person client);
	/**
	 * 
	 * @param client
	 * @return true, if Person updated successfully
	 */
	void updatePerson(Person client);
//	boolean deletePerson(Person client);
	Person findClientByEmail(String email);
	List<Person> getPersons(PersonType type);
	

	
	
}
