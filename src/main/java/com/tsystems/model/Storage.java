package com.tsystems.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
/**
 * Storage keeps Product's amount.
 * @author float
 *
 */
@Entity
@Table(name="STORAGE")
public class Storage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=false,nullable=false)
	private Long id;
//	
//	@OneToMany()
//	@JoinTable
//	  (
//	      name="PRODUCTS_AT_STORAGES",
//	      joinColumns= @JoinColumn(name="product_id"),
//	      inverseJoinColumns= @JoinColumn(name="storage_id")
//	  )
//	private Set<Product> products;
//	
	
	@NotNull(message = "This field must be filled in!")
	@Min(value = 0, message = "Amount can't be less than 0!")
	private Integer amount;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


//	public Set<Product> getProducts() {
//		return products;
//	}
//
//
//	public void setProducts(Set<Product> products) {
//		this.products = products;
//	}


	public Integer getAmount() {
		return amount;
	}


	public void setAmount(Integer amount) {
		this.amount = amount;
	}




	
}
