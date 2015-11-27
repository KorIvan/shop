package com.tsystems.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
	@GeneratedValue()	
	@Column(unique=true,nullable=false)
	private Long id;

	@NotNull(message = "This field must be filled in!")
	private String name;
	
	@NotNull(message = "This field must be filled in!")
	@Column(name="current_price")
	private Float currentPrice;
	
	@NotNull(message = "This field must be filled in!")
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REFRESH})
	private Category category;
	
//	@NotNull
//	@OneToMany(cascade=CascadeType.PERSIST)
//	@JoinTable(name = "PRODUCT_PROPERTY", joinColumns =  @JoinColumn(name = "product_id") )
//	@MapKeyColumn (name="property_name")
//	private Map<Property,PropertyBody> properties;
	@OneToMany(mappedBy="product")
	private List<Properties> properties;
	
	@NotNull(message = "This field must be filled in!")
	@DecimalMin(value = "0.001", message = "Minimal weight is 0.001 gram.")
	private Float weigth;
	
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

	public Float getWeigth() {
		return weigth;
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



	public void setWeigth(Float weigth) {
		this.weigth = weigth;
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


	
}
