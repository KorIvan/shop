package com.tsystems.service;

import com.tsystems.model.Category;

public interface CategoryService {
Category findCategoryByName(String name);
Category findCategoryById(Long id);
}
