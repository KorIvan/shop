package com.tsystems.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PERSON")
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
//	@Column(columnDefinition="CLIENT")
	private PersonType type;

	@Pattern(regexp = "[A-Z]{2,99}", message = "First name must contain at least 2 literal characters!")
	// @org.hibernate.validator.constraints.
	
	@Column(name = "first_name")
	@NotNull
	private String firstName;

	@Pattern(regexp = "[A-Z]{2,99}", message = "Last name must contain at least 2 literal characters!")
	@Column(name = "last_name")
	@NotNull
	private String lastName;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@NotNull(message="Set birthdate!")
	private Date birthdate;

	@Column(unique = true) 
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Invalid email address!")
	@NotNull(message="Set email!")
	private String email;

	@Pattern(regexp = ".{6,100}", message = "Password must contain at least 5 characters")
	@NotNull
	private String password;

	// private java.util.Da
	@OneToMany(mappedBy = "client")
	private List<Address> addresses;

	private boolean enabled;

	public Long getId() {
		return id;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PersonType getType() {
		return type;
	}

	public void setType(PersonType type) {
		this.type = type;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
