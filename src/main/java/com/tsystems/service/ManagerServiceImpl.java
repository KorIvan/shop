package com.tsystems.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsystems.model.Attribute;
import com.tsystems.model.Category;
import com.tsystems.model.Order;
import com.tsystems.model.OrderItem;
import com.tsystems.model.Person;
import com.tsystems.model.Product;
import com.tsystems.model.Statistics;
import com.tsystems.model.User;
import com.tsystems.repository.ManagerRepository;
import com.tsystems.repository.StatisticsRepository;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private StatisticsRepository statisticsRepository;

	public String createProduct(Product product) {
		if (managerRepository.validateProduct(product)) {
			managerRepository.createProduct(product);
			return "Product created.";
		} else
			return String.format("Sorry, \"%s\" already exists in this category.", product.getName());
	}

	public void updateProduct(Product product) {
		managerRepository.updateProduct(product);
	}

	public String createCategory(Category category) {
		if (managerRepository.validateCategory(category)) {
			managerRepository.createCategory(category);
			return "Category created.";
		} else
			return String.format("Sorry, category \"%s\" already exists.", category.getName());
	}

	public void updateCategory(Category category) {
		managerRepository.updateCategory(category);
	}

	public List<Category> findAllCategories() {
		return managerRepository.findAllCategories();
	}

	public Category getCategoryById(Long id) {
		return managerRepository.findCategoryById(id);
	}

	public List<Attribute> getAllAttributesOfCategory(Long categoryId) {
		return managerRepository.findCategoryById(categoryId).getAttributesForCategory();
	}

	public boolean validateManager(User user) {
		return managerRepository.validateManager(user);

	}

	public List<Order> findAllOrders() {
		return managerRepository.findAllOrders();
	}

	public Product getProductById(Long prodId) {
		return managerRepository.findProductById(prodId);
	}

	public OrderItem getOrderItemById(Long id) {
		return managerRepository.findOrderItemById(id);
	}

	// public List<?> findAll(Class<?> toSearch) {
	// productRepository.findAllCategories();
	// return null;
	// }

	public Statistics gatherStatistics() {
		return null;
	}

	private Map<Integer, Product> calculateTop10Products() {

		return null;
	}

	private Map<Integer, Person> calculateTop10Clients() {
		return null;
	}

	@Override
	public Float calculateWeekIncome(Calendar week) {
		week.set(Calendar.DAY_OF_WEEK, week.getFirstDayOfWeek());
		System.out.println(week.getFirstDayOfWeek()+" is first day of week!");
		Date from=new Date(week.getTime().getTime());
		week.add(Calendar.WEEK_OF_YEAR, 1);
		Date to=new Date(week.getTime().getTime());
		List<Order> paidOrders=statisticsRepository.getPaidOrders(from,to);
		float total=0;
		for(Order o:paidOrders){
			total+=o.getCost();
		}
		return new Float(total);
	}
	
	@Override
	public Float calculateWeekIncome(Date from, Date to) {
		List<Order> paidOrders=statisticsRepository.getPaidOrders(from,to);
		float total=0;
		for(Order o:paidOrders){
			total+=o.getCost();
		}
		return new Float(total);
	}

	private Float calculateMonthIncome() {
		return 0f;
	}

	private Float calculateTotalIncome() {
		return 0f;
	}

}
