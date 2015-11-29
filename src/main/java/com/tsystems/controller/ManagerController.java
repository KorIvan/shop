package com.tsystems.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsystems.model.Attribute;
import com.tsystems.model.Category;
import com.tsystems.model.CategoryEditor;
import com.tsystems.model.Product;
import com.tsystems.model.Properties;
import com.tsystems.service.ManagerService;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	@Autowired
	private ManagerService managerService;
	@Autowired
	private CategoryEditor categoryEditor;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Category.class, this.categoryEditor);
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public ModelAndView createProduct() {
		ModelAndView model = new ModelAndView("product");
		model.addObject("categories", managerService.findAllCategories());
		Product product = new Product();
		List<Properties> properties = new LinkedList<Properties>();
		product.setProperties(properties);
		model.addObject("product", product);
		model.addObject("title", "New product");
		return model;
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ModelAndView saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
		ModelAndView model = new ModelAndView("product");
		model.addObject("title", "New product");
		if (result.hasErrors()) {
			model.addObject("message", "Sorry, error ocured.");
			return model;
		} else {
			// product.setProperties(properties);
			model.addObject("message", managerService.createProduct(product));
		}
		return model;
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Category> getAllCategories() {
		return managerService.findAllCategories();
	}

	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public ModelAndView createCategory() {
		ModelAndView model = new ModelAndView("category");
		Category cat = new Category();
		List<Attribute> attributes = new LinkedList<Attribute>();
		attributes.add(new Attribute());
		cat.setAttributesForCategory(attributes);
		model.addObject("title", "New category");
		model.addObject("category", cat);

		return model;
	}

	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public ModelAndView postCategory(@Valid @ModelAttribute("category") Category category, BindingResult result,
			@RequestParam String action, HttpServletRequest request) {
		String[] toDelete = request.getParameterValues("toDelete");
		ModelAndView model = new ModelAndView("category");
		model.addObject("title", "New category");

		if (result.hasErrors() && !result.hasFieldErrors("type")) {
			model.addObject("message", "Sorry, error ocured.");
			return model;
		}
		if (action.equalsIgnoreCase("Save")) {
			model.addObject("message", managerService.createCategory(category));
		}
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
