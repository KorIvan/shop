package com.tsystems.controller;

import java.util.LinkedList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsystems.model.Cart;
import com.tsystems.model.CartItem;

@Controller
@Scope("session")
public class CartController {
	private Cart cart = new Cart();

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView getCart() {
		ModelAndView model = new ModelAndView("cart");
		model.addObject("cart", cart);
		model.addObject("title", "Your cart");
		return model;
	}

	@RequestMapping(value = "/cartContent", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Cart getCategoryProducts() {
		return cart;
	}

	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	public ModelAndView setCart(@RequestBody CartItem cartItem) {
		ModelAndView model = new ModelAndView("cart");
		cart.setCartItem(cartItem);
		model.addObject("title", "Your cart");
		model.addObject("cart", cart);
		return model;
	}

	// @RequestMapping(value = "/addToCart", method = RequestMethod.GET)
	// public ModelAndView addToCart() {
	// ModelAndView model = new ModelAndView("cart");
	// // cart.putProductToCart(new Product(), 4);
	// // cart.putProductToCart(new Product(), 7);
	// model.addObject("lol", "String ;ol");
	// return model;
	// }

	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public ModelAndView putToCart(@RequestBody CartItem cartItem) {
		System.out.println(cartItem.getProduct().getName() + " id:" + cartItem.getProduct().getId() + " amount:"
				+ cartItem.getAmount());
		if (cart.getItemList() == null) {
			cart.setItemList(new LinkedList<CartItem>());
		}
		if (cart.getItemList().contains(cartItem)) {
			cart.addToCartItem(cartItem);
		} else
			cart.getItemList().add(cartItem);
		ModelAndView model = new ModelAndView("cart");
		model.addObject("cart", cart);
		model.addObject("lol", "String ;ol");
		return model;
	}
}
