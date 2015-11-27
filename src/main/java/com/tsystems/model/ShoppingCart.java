package com.tsystems.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ShoppingCart {
	@NotNull
	private Product product;
	@Min(value=1,message="Amount to buy can't be less 1!")
	private Integer amountToBuy;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getAmountToBuy() {
		return amountToBuy;
	}

	public void setAmountToBuy(Integer amountToBuy) {
		this.amountToBuy = amountToBuy;
	}

}
