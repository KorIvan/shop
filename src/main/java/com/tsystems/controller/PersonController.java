package com.tsystems.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.tsystems.model.Person;
import com.tsystems.service.PersonService;

@Controller
@SessionAttributes("cart")
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
	 * @param person
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registrate(@Valid @ModelAttribute("person") Person person, BindingResult result) {
		System.out.println(result.hasFieldErrors("type"));
		ModelAndView model = new ModelAndView("registration");
		model.addObject("title", "Registration");
		//if result has errors
		if (result.hasErrors() && !result.hasFieldErrors("type")) {
			model.addObject("message", "Sorry, error ocured.");
			return model;
		} else {
			model.addObject("message", clientService.createClient(person));
		}
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

	@RequestMapping(value = "/calendar")
	public String calendar(Model model) {
		return "calendar";
	}
}
