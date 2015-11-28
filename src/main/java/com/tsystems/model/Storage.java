package com.tsystems.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product products;
	
	
	@NotNull(message = "This field must be filled in!")
	@Min(value = 0, message = "Amount can't be less than 0!")
	private Integer amount;


	
	public Integer getAmount() {
		return amount;
	}


	public void setAmount(Integer amount) {
		this.amount = amount;
	}


	public Long getId() {
		return id;
	}


	
}
