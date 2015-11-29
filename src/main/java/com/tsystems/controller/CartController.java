package com.tsystems.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tsystems.model.Product;

@Controller
@Scope("session")
public class CartController {
	private ShoppingCart cart = new ShoppingCart();

	@RequestMapping(value="/addToCart", method=RequestMethod.GET)
	public ModelAndView addToCart(@ModelAttribute("product") Product product, @ModelAttribute("amount") Integer amount) {
		ModelAndView model = new ModelAndView("cart");
		cart.putProductToCart(new Product(), 4);
		cart.putProductToCart(new Product(), 7);
		model.addObject("cart", cart);
		model.addObject("lol", "String ;ol");
		return model;}
}
