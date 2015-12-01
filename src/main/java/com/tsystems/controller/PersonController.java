package com.tsystems.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsystems.model.Cart;
import com.tsystems.model.Category;
import com.tsystems.model.Person;
import com.tsystems.model.Product;
import com.tsystems.service.PersonService;

@Controller
public class PersonController {
	@Autowired
	private PersonService clientService;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String getRegistrationForm(@ModelAttribute("person") Person person, Model model) {
		model.addAttribute("title", "Registration");
		return "registration";
	}

	/**
	 * Client's registration
	 * 
	 * @param person
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registrate(@Valid @ModelAttribute("person") Person person, BindingResult result) {
		ModelAndView model = new ModelAndView("registration");
		model.addObject("title", "Registration");
		// if result has errors
		if (result.hasErrors() && !result.hasFieldErrors("type")) {
			model.addObject("message", "Sorry, error ocured.");
			return model;
		} else {
			model.addObject("message", clientService.createClient(person));
		}
		return model;
	}

	@RequestMapping(value="/catalog",method=RequestMethod.GET)
	public ModelAndView getCatalog(){
		ModelAndView model=new ModelAndView("catalog");
		return model;
	}
	@RequestMapping(value = "/hello")
	public String getHello(Model model) {
		model.addAttribute("registration", "Registration");
		return "hello";
	}

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public String sayHello(Model model) {

		model.addAttribute("greeting", "Hello World");

		return "hello";
	}
	@RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Product> getCategoryProducts(@PathVariable() String categoryId) {
		
		return clientService.getCategoryById(Long.parseLong(categoryId));
	}
	@RequestMapping(value = "/categories", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Category> getAllCategories() {
		return clientService.findAllCategories();
	}
	@RequestMapping(value="order", method=RequestMethod.GET)
	public ModelAndView makeOrder(HttpSession session){
		Cart cart=(Cart)session.getAttribute("cart");
		ModelAndView model=new ModelAndView("order");
		clientService.purchaseOrder(cart);
		return model;
	}
}
