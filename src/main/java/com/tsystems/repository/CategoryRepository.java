package com.tsystems.repository;

import java.util.List;

import com.tsystems.model.Category;

public interface CategoryRepository {
	/**
	 * Retrieve all existing Categories
	 * @return List<Category>
	 */
	List<Category> findAllCategories();
	
	void createCategory(Category category);
	/**
	 * Check category for uniqueness
	 * @param category
	 * @return true, if there is not other category with the same name
	 */
	boolean validateCategory(Category category);
	
	Category findCategoryById(Long id);
	void updateCategory(Category category);
}
