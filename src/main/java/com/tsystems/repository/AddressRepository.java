package com.tsystems.repository;

import java.net.ConnectException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.tsystems.model.Address;

/**
 * AddressRepository provides CRUD-operations with objects of Address class.
 * 
 * @author Ivan
 *
 */
public interface AddressRepository {

	List<Address> findAllAddresses(Long clientId) throws ConnectException;

	Address findAddressById(Long id) throws EntityNotFoundException,ConnectException;

	void createAddress(Address address) throws ConnectException;

	void updateAddress(Address address) throws ConnectException,EntityNotFoundException;
}
