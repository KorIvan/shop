package com.tsystems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsystems.model.Attribute;
import com.tsystems.model.Category;
import com.tsystems.model.Product;
import com.tsystems.model.User;
import com.tsystems.repository.ManagerRepository;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	private ManagerRepository managerRepository;

	public String createProduct(Product product) {
		//
		if (managerRepository.validateProduct(product)) {
			if (managerRepository.createProduct(product))
				return "Product created.";
			else
				return "Sorry, we have problems.";
		} else
			return String.format("Sorry, \"%s\" already exists in this category.", product.getName());
	}

	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	public String createCategory(Category category) {
		if (managerRepository.validateCategory(category)) {
			if (managerRepository.createCategory(category))
				return "Category created.";
			else
				return "Sorry, we have problems.";
		} else
			return String.format("Sorry, category \"%s\" already exists.", category.getName());
	}

	public boolean updateCategory(Category category) {
		// TODO Auto-generated method stub
		return false;
	}

	public String createAttribute(Attribute attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateAttribute(Attribute attribute) {
		// TODO Auto-generated method stub
		return false;
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

	// public List<?> findAll(Class<?> toSearch) {
	// productRepository.findAllCategories();
	// return null;
	// }

}
