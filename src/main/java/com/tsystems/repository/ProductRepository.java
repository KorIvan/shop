package com.tsystems.repository;

import java.util.List;

import com.tsystems.model.Product;

public interface ProductRepository {


	/**
	 * Retrieve all Products under selected Category
	 * 
	 * @param categoryId
	 * @return List<Product>
	 */
	List<Product> findProductsByCategory(Long categoryId);

	Product findProductById(Long prodId);

	void updateProduct(Product prod);

	void createProduct(Product product);

	/**
	 * Check poduct for uniqueness under Category
	 * 
	 * @param category
	 * @return true, if there is not other product with the same name
	 */
	boolean validateProduct(Product product);
}
