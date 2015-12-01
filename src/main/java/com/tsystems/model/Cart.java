package com.tsystems.model;

import java.util.List;

/**
 * Shopping cart consists of CartItems.
 * 
 * @author Ivan Kornelyuk
 *
 */
public class Cart {
	private List<CartItem> itemList;

	public List<CartItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<CartItem> itemList) {
		this.itemList = itemList;
	}

	/**
	 * Add items of Porduct to Order
	 * 
	 * @param cartItem
	 */
	public void addToCartItem(CartItem cartItem) {
		int indexOfCartItem = itemList.indexOf(cartItem);
		itemList.get(indexOfCartItem).addAmountOfItem(cartItem.getAmount());
	}

	/**
	 * Set items of Product from Order
	 * 
	 * @param cartItem
	 */
	public void setCartItem(CartItem cartItem) {
		int indexOfCartItem = itemList.indexOf(cartItem);
		itemList.get(indexOfCartItem).setAmount(cartItem.getAmount());
	}
}
