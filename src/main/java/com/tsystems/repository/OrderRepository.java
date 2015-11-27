package com.tsystems.repository;

import com.tsystems.model.Order;
/**
 * OrderRepository provides CRUD-operations with objects of Order class. 
 * @author Ivan
 *
 */
public interface OrderRepository {
	Order readOrderByClientId(Integer clientId);
	Order readOrderByAddressId(Integer addressId);
	boolean createOrder(Order order);
	boolean updateOrder(Order order);
//	boolean deleteOrder(Order order);
}
