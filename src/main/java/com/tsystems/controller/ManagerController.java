package com.tsystems.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public ModelAndView createProduct(){
		ModelAndView model=new ModelAndView("product");
		Product product=new Product();
		model.addObject("product", product);
		
		return model;
	}
	@RequestMapping(value="/categories",method=RequestMethod.GET,produces = "application/json")
	public @ResponseBody List<Category> getAllCategories(){
//		return managerService.findAllCategories();
		List<Category> categories=new ArrayList<Category>();
		Category a=new Category();
		a.setName("TV");a.setDescription("ollo d ");
		Category b=new Category();
		b.setName("Kitchen");b.setDescription("lllbbbbbb");
		categories.add(a);
		categories.add(b);
		return categories;
	}
	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public ModelAndView createCategory() {
		ModelAndView model = new ModelAndView("category");
		Category cat = new Category();
		cat.setName("Kitchen");
		cat.setDescription("Technic for kitchen");
		List<Attribute> attributes = new LinkedList<Attribute>();
		Attribute a = new Attribute();
		a.setName("Model");
		a.setDescription("about models");
		Attribute b = new Attribute();
		b.setName("Size");
		b.setDescription("size in dimensions");
		attributes.add(a);
		attributes.add(b);
		cat.setAttributesForCategory(attributes);
		model.addObject("category", cat);
//		model.addObject("attributes",attributes);

		return model;
	}

	@RequestMapping(value = "addCategory", method = RequestMethod.POST)
	public ModelAndView postCategory(@Valid @ModelAttribute("category") Category category,
			BindingResult result) {
		ModelAndView model = new ModelAndView("category");
		return model;

	}
}
