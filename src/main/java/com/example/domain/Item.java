package com.example.domain;

/**
 * 商品情報を表すエンティティ.
 * 
 * @author nonaa
 *
 */
public class Item {
	/** 商品ID */
	private Integer id;
	/** 商品名 */
	private String name;
	/** 状態 */
	private Integer condition;
	/** カテゴリーID */
	private Integer categoryId;
	/** 銘柄 */
	private String brand;
	/** 価格 */
	private Integer price;
	/** 発送 */
	private Integer shipping;
	/** 説明 */
	private String description;
	/** カテゴリー */
	private Category category;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getShipping() {
		return shipping;
	}

	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", condition=" + condition + ", categoryId=" + categoryId
				+ ", brand=" + brand + ", price=" + price + ", shipping=" + shipping + ", description=" + description
				+ ", category=" + category + "]";
	}

}
