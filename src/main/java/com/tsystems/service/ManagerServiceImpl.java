package com.tsystems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsystems.model.Attribute;
import com.tsystems.model.Category;
import com.tsystems.model.Order;
import com.tsystems.model.OrderItem;
import com.tsystems.model.OrderStatus;
import com.tsystems.model.Person;
import com.tsystems.model.Product;
import com.tsystems.repository.AddressRepository;
import com.tsystems.repository.CategoryRepository;
import com.tsystems.repository.OrderRepository;
import com.tsystems.repository.PersonRepository;
import com.tsystems.repository.ProductRepository;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired 
	private AddressRepository addressRepository;

	public String createProduct(Product product) {
		if (productRepository.validateProduct(product)) {
			productRepository.createProduct(product);
			return "Product created.";
		} else
			return String.format("Sorry, \"%s\" already exists in this category.", product.getName());
	}

	public void updateProduct(Product product) {
		productRepository.updateProduct(product);
	}

	public String createCategory(Category category) {
		if (categoryRepository.validateCategory(category)) {
			categoryRepository.createCategory(category);
			return "Category created.";
		} else
			return String.format("Sorry, category \"%s\" already exists.", category.getName());
	}

	public void updateCategory(Category category) {
		categoryRepository.updateCategory(category);
	}

	public List<Category> findAllCategories() {
		return categoryRepository.findAllCategories();
	}

	public Category getCategoryById(Long id) {
		return categoryRepository.findCategoryById(id);
	}

	public List<Attribute> getAllAttributesOfCategory(Long categoryId) {
		return categoryRepository.findCategoryById(categoryId).getAttributesForCategory();
	}
@Deprecated
	public boolean validateManager(Person user) {
//		return personRepository.validateManager(user);
return false;
	}

	public List<Order> findAllOrders() {
		return orderRepository.findAllOrders();
	}

	public Product getProductById(Long prodId) {
		return productRepository.findProductById(prodId);
	}

	public OrderItem getOrderItemById(Long id) {
		return orderRepository.findOrderItemById(id);
	}

	@Override
	public Order getOrderById(Long orderId) {
		return orderRepository.findOrderById(orderId);
	}

	@Override
	public String updateOrder(Order order) {
		if(order.getPaid()){
			if(order.getStatus().equals(OrderStatus.PAYMENT_PENDING)){
				return "Order is already paid.";
			}
		}
		orderRepository.updateOrder(order);
		return null;
	}

	// public List<?> findAll(Class<?> toSearch) {
	// productRepository.findAllCategories();
	// return null;
	// }



}
