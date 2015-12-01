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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
/**
 * Product has Category and Properties which can be modified.
 * @author Ivan Kornelyuk
 *
 */
@Entity
@Table(name = "PRODUCT")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true,nullable=false)
	private Long id;

	@NotNull(message = "This field must be filled in!")
	private String name;
	
	@NotNull(message = "This field must be filled in!")
	@Column(name="current_price")
	private Float currentPrice;
	
	@NotNull(message = "This field must be filled in!")
	@ManyToOne()
	private Category category;
	
//	@NotNull
//	@OneToMany(cascade=CascadeType.PERSIST)
//	@JoinTable(name = "PRODUCT_PROPERTY", joinColumns =  @JoinColumn(name = "product_id") )
//	@MapKeyColumn (name="property_name")
//	private Map<Property,PropertyBody> properties;
//	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL) //in json does not load
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)

	private List<Properties> properties;
	
	@NotNull(message = "This field must be filled in!")
	@DecimalMin(value = "0.001", message = "Minimal weight is 0.001 gram.")
	private Float weight;
	
	@NotNull(message = "This field must be filled in!")
	@DecimalMin(value = "0.001", message = "Minimal bulk is 0.001 cm.")
	private Float bulk;
	
	private String description;

	public Category getCategory() {
		return category;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Float getCurrentPrice() {
		return currentPrice;
	}

	public Float getBulk() {
		return bulk;
	}

	public Float getWeight() {
		return weight;
	}

	

	public void setCategory(Category category) {
		this.category = category;
	}

	@Deprecated public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCurrentPrice(Float currentPrice) {
		this.currentPrice = currentPrice;
	}

	public void setBulk(Float bulk) {
		this.bulk = bulk;
	}



	public void setWeight(Float weigth) {
		this.weight = weigth;
	}

	public List<Properties> getProperties() {
		return properties;
	}

	public void setProperties(List<Properties> properties) {
		this.properties = properties;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bulk == null) ? 0 : bulk.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((currentPrice == null) ? 0 : currentPrice.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		Product other = (Product) obj;
		if (bulk == null) {
			if (other.bulk != null)
				return false;
		} else if (!bulk.equals(other.bulk))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (currentPrice == null) {
			if (other.currentPrice != null)
				return false;
		} else if (!currentPrice.equals(other.currentPrice))
			return false;
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
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}


	
}
