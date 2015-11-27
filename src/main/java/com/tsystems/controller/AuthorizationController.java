package com.tsystems.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tsystems.model.Person;

@Controller
public class AuthorizationController {

	@RequestMapping(value="/authorization", method=RequestMethod.GET)
	public String getAuth(@ModelAttribute("person") Person person){
		return "auth";
	}
	
	@RequestMapping(value="/authorization", method=RequestMethod.POST)
	public String getAuth(@Valid @ModelAttribute("person") Person person, HttpSession session,
			BindingResult result){

		if (result.hasErrors()) {
			return "auth";
		} else {
			System.out.println("Check login and password!");
		}

		return "loggedIn";
	}

}
