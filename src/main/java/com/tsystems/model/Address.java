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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apartment == null) ? 0 : apartment.hashCode());
		result = prime * result + ((building == null) ? 0 : building.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
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
		Address other = (Address) obj;
		if (apartment == null) {
			if (other.apartment != null)
				return false;
		} else if (!apartment.equals(other.apartment))
			return false;
		if (building == null) {
			if (other.building != null)
				return false;
		} else if (!building.equals(other.building))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}

	public Person getClient() {
		return client;
	}

	public void setClient(Person client) {
		this.client = client;
	}

}
