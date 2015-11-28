package com.tsystems.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsystems.model.Attribute;
import com.tsystems.model.Category;
import com.tsystems.model.Product;
import com.tsystems.service.ManagerService;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	@Autowired
	private ManagerService managerService;

	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public ModelAndView createProduct() {
		ModelAndView model = new ModelAndView("product");
		model.addObject("categories", managerService.findAllCategories());
		Product product = new Product();
		model.addObject("product", product);
		return model;
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ModelAndView saveProduct(@Valid @ModelAttribute("product") Product product,
			@ModelAttribute("category") Category category, BindingResult result) {
		product.setCategory(category);
		ModelAndView model = new ModelAndView("product");
		
		// if result has errors
		if (result.hasErrors()) {
			model.addObject("message", "Sorry, error ocured.");
			return model;
		} else {
			
			model.addObject("message", managerService.createProduct(product));
		}
		return model;
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Category> getAllCategories() {
		return managerService.findAllCategories();
		// List<Category> categories = new ArrayList<Category>();
		// Category a = new Category();
		// a.setId(1);
		// a.setName("TV");
		// a.setDescription("ollo d ");
		// Category b = new Category();
		// b.setName("Kitchen");
		// b.setDescription("lllbbbbbb");
		// b.setId(2);
		// categories.add(a);
		// categories.add(b);
		// return categories;
	}

	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public ModelAndView createCategory() {
		ModelAndView model = new ModelAndView("category");
		Category cat = new Category();
		List<Attribute> attributes = new LinkedList<Attribute>();
		attributes.add(new Attribute());
		cat.setAttributesForCategory(attributes);

		// cat.setName("Kitchen");
		// cat.setDescription("Technic for kitchen");
		//
		// Attribute a = new Attribute();
		// a.setName("Model");
		// a.setDescription("about models");
		// Attribute b = new Attribute();
		// b.setName("Size");
		// b.setDescription("size in dimensions");
		// attributes.add(a);
		// attributes.add(b);
		// cat.setAttributesForCategory(attributes);
		model.addObject("category", cat);

		return model;
	}

	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public ModelAndView postCategory(@Valid @ModelAttribute("category") Category category, BindingResult result,
			@RequestParam String action, HttpServletRequest request) {
		System.out.println(action);
		String[] toDelete = request.getParameterValues("toDelete");
		ModelAndView model = new ModelAndView("category");
		if (action.equalsIgnoreCase("Save"))
			managerService.createCategory(category);
		if (action.equalsIgnoreCase("Add Attribute")) {
			category.getAttributesForCategory().add(new Attribute());
			model.addObject("category", category);
		}
		if (toDelete != null && action.equalsIgnoreCase("Delete")) {
			for (int i = toDelete.length - 1; i >= 0; i--) {
				category.getAttributesForCategory().remove(Integer.parseInt(toDelete[i]));
			}
			model.addObject("category", category);
		}
		return model;

	}
}
