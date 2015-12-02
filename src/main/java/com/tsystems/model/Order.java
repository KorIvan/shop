package com.tsystems.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@OneToOne(fetch=FetchType.EAGER)
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
	@OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL},mappedBy="order")
//	@JoinTable(name = "ORDER_PRODUCT", joinColumns = { @JoinColumn(name = "order_id") }, inverseJoinColumns = {
//			@JoinColumn(name = "product_id") })
	private List<OrderItem> orderItems;

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



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((deliveryMethod == null) ? 0 : deliveryMethod.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Order other = (Order) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (deliveryMethod != other.deliveryMethod)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getClient() {
		return client;
	}

	public void setClient(Person client) {
		this.client = client;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public PaymentMethod getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(PaymentMethod payMethod) {
		this.payMethod = payMethod;
	}

	public DeliveryMethod getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public java.util.Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(java.util.Date creationDate) {
		this.creationDate = creationDate;
	}

	public java.util.Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(java.util.Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getCommentsForOrder() {
		return commentsForOrder;
	}

	public void setCommentsForOrder(String commentsForOrder) {
		this.commentsForOrder = commentsForOrder;
	}


}
