package com.tsystems.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
/**
 * Attribute is a unit of Product's Properties.
 * Attribute refers to Category.
 * @author Kornelyuk Ivan
 *
 */
@Entity
@Table(name="ATTRIBUTE")
public class Attribute {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@NotNull(message = "This field must be filled in!")
	private String name;

	private String description;
	
	@OneToMany(mappedBy="attributes")
	private List<Properties> properties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

}
