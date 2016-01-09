package com.tsystems.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.tsystems.model.Order;
import com.tsystems.model.OrderItem;
/**
 * OrderRepository provides CRUD-operations with objects of Order class. 
 * @author Ivan
 *
 */
public interface OrderRepository {
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
	void cancelOrder(Order order);
	void createOrder(Order order);
	List<Order> findAllOrders(Long clientId);



	List<Order> findAllOrders();

	OrderItem findOrderItemById(Long id);

	Order findOrderById(Long orderId);
	/**
	 * Return List of paid Orders
	 * @return
	 */
	List<Order> getPaidOrders();

	List<Order> getPaidOrders(Date from, Date to);

}
