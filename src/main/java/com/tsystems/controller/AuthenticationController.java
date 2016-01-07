package com.tsystems.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class AuthenticationController {

//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String logInGet(@ModelAttribute("user") User user) {
//		return "auth";
//	}
//
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String destroySession(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("logged out");
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied() {
		return "accessDenied";
	}
}
