package com.tsystems.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * Order consist of set of Products, Address, Client and If Client chose self
 * delivery method, then Order's Address is null.
 * 
 * @author Ivan Kornelyuk
 *
 */
@Entity
@Table(name = "ORDERS")
public class Order {

	@Id
	@GeneratedValue()
	@Column(unique = true, nullable = false)
	private Long id;

	@OneToOne
	@NotNull(message = "This field can't be null!")
	private Person client;

	@OneToOne
	private Address address;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "This field must be filled in!")
	@Column(name = "payment_method")
	private PaymentMethod payMethod;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "This field must be filled in!")
	@Column(name = "delivery_method")
	private DeliveryMethod deliveryMethod;

	@NotNull(message = "This field must be filled in!")
	@ManyToMany
	@JoinTable(name = "ORDER_PRODUCT", joinColumns = { @JoinColumn(name = "order_id") }, inverseJoinColumns = {
			@JoinColumn(name = "product_id") })
	private List<Product> products;

	private Boolean paid;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "This field must be filled in!")
	@Column(name = "order_status")
	private OrderStatus status;

	@NotNull(message = "This field can't be null!")
	@DecimalMin(value = "0.01", message = "Order's cost can't be equal 0!")
	private Float cost;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date")
	private java.util.Date creationDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "delivery_date")
	private java.util.Date deliveryDate;
	
	@Column(name = "comment_for_order")
	private String commentsForOrder;

	public Person getClient() {
		return client;
	}

	public Address getAddress() {
		return address;
	}

	public PaymentMethod getPayMethod() {
		return payMethod;
	}

	public DeliveryMethod getDeliveryMethod() {
		return deliveryMethod;
	}

	public List<Product> getProducts() {
		return products;
	}

	public Boolean getPaid() {
		return paid;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setClient(Person client) {
		this.client = client;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setPayMethod(PaymentMethod payMethod) {
		this.payMethod = payMethod;
	}

	public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	@Deprecated
	public void setId(Long id) {
		this.id = id;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

}
