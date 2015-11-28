package com.tsystems.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Client's address. Client can have multiple addresses. Address consists of
 * zip-code, country, city, street, building, apartment. If Client chose self
 * delivery method, he does'not have Address.
 * 
 * @author Ivan Kornelyuk
 * @version
 * 
 */
@Entity
@Table(name = "ADDRESS")
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	@NotNull(message = "This field must be filled in!")
	private String zip;
	@NotNull(message = "This field must be filled in!")
	@Pattern(regexp = "[A-Z]{1}[a-z]{1,99}", message = "Country name must contain at least 2 literal character and starts with upper case")
	private String country;
	@NotNull(message = "This field must be filled in!")
	@Pattern(regexp = "[A-Z]{1}[a-z]{1,99}", message = "City name must contain at least 2 literal character and starts with upper case")
	private String city;
	@NotNull(message = "This field must be filled in!")
	private String street;
	@NotNull(message = "This field must be filled in!")
	private String building;
	@NotNull(message = "This field must be filled in!")
	private String apartment;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Person client;

	public String getApartment() {
		return apartment;
	}

	public String getBuilding() {
		return building;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public Long getId() {
		return id;
	}

	public String getStreet() {
		return street;
	}

	public String getZip() {
		return zip;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Deprecated
	public void setId(Long id) {
		this.id = id;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}
