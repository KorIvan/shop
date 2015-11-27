package com.tsystems.repository;

import com.tsystems.model.Address;
/**
 * AddressRepository provides CRUD-operations with objects of Address class. 
 * @author Ivan
 *
 */
public interface AddressRepository {
	Address readAddress(Integer id);
	boolean createAddress(Address address);
	boolean updateAddress(Address address);
//	boolean deleteAddress(Address address);
}
