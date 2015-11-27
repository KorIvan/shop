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
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	public String createCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
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

//	public List<?> findAll(Class<?> toSearch) {
//		productRepository.findAllCategories();
//		return null;
//	}

}
