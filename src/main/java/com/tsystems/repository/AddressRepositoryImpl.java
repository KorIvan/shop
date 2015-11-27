package com.tsystems.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.tsystems.model.Address;

@Repository("addressRepository")
public class AddressRepositoryImpl implements AddressRepository {
	@PersistenceContext
	private EntityManager em;

	public Address readAddress(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean createAddress(Address address) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateAddress(Address address) {
		// TODO Auto-generated method stub
		return false;
	}

}
