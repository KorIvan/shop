package com.tsystems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsystems.model.Attribute;
import com.tsystems.model.Category;
import com.tsystems.model.Product;
import com.tsystems.repository.ProductRepository;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	private ProductRepository productRepository;

	public String createProduct(Product product) {
//		
		if (productRepository.validateProduct(product)) {
			if (productRepository.createProduct(product))
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
		if (productRepository.validateCategory(category)) {
			if (productRepository.createCategory(category))
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
		return productRepository.findAllCategories();
	}

	public Category getCategoryById(Long id) {
		return productRepository.findCategoryById(id);
	}

	public List<Attribute> getAllAttributesOfCategory(Long categoryId) {
		return productRepository.findCategoryById(categoryId).getAttributesForCategory();
	}

//	public List<?> findAll(Class<?> toSearch) {
//		productRepository.findAllCategories();
//		return null;
//	}

}
