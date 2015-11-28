package com.tsystems.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Category of Products. Every Category has defined set of Attributes.
 * Category's Products possess those Attributes and have values for them.
 * 
 * @author Ivan Kornelyuk
 *
 */
@Entity
@Table(name = "CATEGORY")
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private Integer id;

//	@Column(name = "parent_id")
//	private Integer parentId;

	@NotNull
	private String name;

	private String description;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	 @JoinTable
	  (
	      name="ATTRIBUTES_FOR_CATEGORY",
	      joinColumns= @JoinColumn(name="category_id"),
	      inverseJoinColumns= @JoinColumn(name="attribute_id")
	  )
	private List<Attribute> attributesForCategory;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Deprecated
	public void setId(Integer id) {
		this.id = id;
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

	public List<Attribute> getAttributesForCategory() {
		return attributesForCategory;
	}

	public void setAttributesForCategory(List<Attribute> attributesForCategory) {
		this.attributesForCategory = attributesForCategory;
	}
}
