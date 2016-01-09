package com.tsystems.repository;

import java.util.List;

import com.tsystems.model.Address;

/**
 * AddressRepository provides CRUD-operations with objects of Address class.
 * 
 * @author Ivan
 *
 */
public interface AddressRepository {

	boolean updateAddress(Address address);

	List<Address> findAllAddresses(Long clientId);

	Address findAddressById(Long id);
}
