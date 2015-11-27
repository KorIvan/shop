package com.tsystems.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.tsystems.model.Order;

@Repository("orderRepository")
public class OrderRepositoryImpl implements OrderRepository {
	@PersistenceContext
	private EntityManager em;

	public Order readOrderByClientId(Integer clientId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Order readOrderByAddressId(Integer addressId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean createOrder(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateOrder(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

}
