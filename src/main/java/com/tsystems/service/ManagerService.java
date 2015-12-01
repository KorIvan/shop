package com.tsystems.service;

import java.util.List;

import com.tsystems.model.Attribute;
import com.tsystems.model.Category;
import com.tsystems.model.Product;
import com.tsystems.model.User;

public interface ManagerService {
	String createProduct(Product product);

	boolean updateProduct(Product product);

	String createCategory(Category category);

	boolean updateCategory(Category category);

	List<Category> findAllCategories();

	Category getCategoryById(Long id);

	String createAttribute(Attribute attribute);

	boolean updateAttribute(Attribute attribute);

	List<Attribute> getAllAttributesOfCategory(Long parseLong);
	
	boolean validateManager(User user);
}
