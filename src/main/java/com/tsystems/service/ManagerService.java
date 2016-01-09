package com.tsystems.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tsystems.model.Attribute;
import com.tsystems.model.Category;
import com.tsystems.model.Order;
import com.tsystems.model.OrderItem;
import com.tsystems.model.Product;
import com.tsystems.model.User;

public interface ManagerService {
	/**
	 * Before vreation check objects of current category.
	 * @param product
	 * @return message, if product created, or not
	 */
	String createProduct(Product product);
	
	void updateProduct(Product product);

	String createCategory(Category category);

	void updateCategory(Category category);

	List<Category> findAllCategories();

	Category getCategoryById(Long id);

//	String createAttribute(Attribute attribute);
//
//	void updateAttribute(Attribute attribute);

	List<Attribute> getAllAttributesOfCategory(Long parseLong);
	
//	boolean validateManager(User user);
	List<Order> findAllOrders();
	Product getProductById(Long prodId);

	OrderItem getOrderItemById(Long id);

	Order getOrderById(Long parseLong);
	
	String updateOrder(Order order);
}


