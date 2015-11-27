package com.tsystems.service;

import com.tsystems.model.Attribute;
import com.tsystems.model.Category;
import com.tsystems.model.Product;

public interface ProductService {
	/**
	 * Sales manager creates new Product
	 * 
	 * @param newProduct
	 * @return true, if created successfully
	 */
	boolean creatProduct(Product newProduct);

	/**
	 * Sales manager modifies Properties of existing Product
	 * 
	 * @param product
	 * @return true, if updated successfully
	 */
	boolean updateProduct(Product product);


	
	/**
	 * Sales manager creates new Category
	 * 
	 * @param newCategory
	 * @return true, if created successfully
	 */
	boolean createCategory(Category newCategory);

	/**
	 * Sales manager can modify existing Category: \n1)change name and
	 * description;\n 2)change set of Attributes for given Category;
	 * 
	 * @param category
	 * @return true, if updated successfully
	 */
	boolean updateCategory(Category category);

	/**
	 * Sales manager creates new Attribute for given Category
	 * @param newAttribute, category
	 * @return true, if created successfully
	 */
	boolean createAttribute(Attribute newAttribute, Category category);

	/**
	 * Sales manager can modify Attribute's properties, such as:
	 * 1)name and description;
	 * 2)Category;
	 * If Category==null, then Attribute's Category'll not be changed.
	 * @param attribute, category
	 * @return true, if updated successfully
	 */
	boolean updateAttribute(Attribute attribute, Category category);

}
