package com.tsystems.model;

/**
 * CartIem consists of Product and product's amount.
 * 
 * @author Ivan Kornelyuk
 *
 */
public class CartItem {

	private Product product;
	private Integer amount;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void addAmountOfItem(int add) {
		if (add > 0)
			this.amount += add;
	}

	public void removeAmountOfItem(int remove) {
		if (remove > 0) {
			if (this.amount - remove < 0)
				this.amount = 0;
			else
				this.amount -= remove;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

}
