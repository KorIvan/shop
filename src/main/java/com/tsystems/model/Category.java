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
	private Long id;

//	@Column(name = "parent_id")
//	private Integer parentId;

	@NotNull
	private String name;

	private String description;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY) //in json does not load

	 @JoinTable
	  (
	      name="ATTRIBUTES_FOR_CATEGORY",
	      joinColumns= @JoinColumn(name="category_id"),
	      inverseJoinColumns= @JoinColumn(name="attribute_id")
	  )
	private List<Attribute> attributesForCategory;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Deprecated
	public void setId(Long id) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
