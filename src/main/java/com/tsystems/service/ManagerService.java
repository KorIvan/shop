package com.tsystems.service;

import java.util.List;

import com.tsystems.model.Attribute;
import com.tsystems.model.Category;
import com.tsystems.model.Product;

public interface ManagerService {
	String createProduct(Product product);
	boolean updateProduct(Product product);
	String createCategory(Category category);
	boolean updateCategory(Category category);
//	List<?> findAll(Class<?> toSearch);
	List<Category> findAllCategories();
	String createAttribute(Attribute attribute);
	boolean updateAttribute(Attribute attribute);
}
