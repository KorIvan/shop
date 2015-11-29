package com.tsystems.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tsystems.model.Product;

public class ShoppingCart {
	private Map<Product,Integer> cart=new HashMap<Product,Integer>();
	public void putProductToCart(Product product, Integer amount){
		cart.put(product, amount);
	}
	public Map<Product, Integer> getCart() {
		return cart;
	}
	public void setCart(Map<Product, Integer> cart) {
		this.cart = cart;
	}
	

}
