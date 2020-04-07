package com.example.form;

/**
 * 商品検索情報のフォーム.
 * 
 * @author nonaa
 *
 */
public class ItemForm {
	/** 商品ID */
	private String id;
	/** 商品価格 */
	private String price;
	/** 商品カテゴリー */
	private String category;
	/** 商品コンディション */
	private String condition;
	/** 商品説明 */
	private String description;
	/** 大カテゴリーID */
	private String parentCategoryId;
	/** 中カテゴリーID */
	private String childCategoryId;
	/** 小カテゴリーID */
	private String grandChildCategoryId;
	/** 商品名 */
	private String name;
	/** 銘柄 */
	private String brand;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(String parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public String getChildCategoryId() {
		return childCategoryId;
	}

	public void setChildCategoryId(String childCategoryId) {
		this.childCategoryId = childCategoryId;
	}

	public String getGrandChildCategoryId() {
		return grandChildCategoryId;
	}

	public void setGrandChildCategoryId(String grandChildCategoryId) {
		this.grandChildCategoryId = grandChildCategoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "ItemForm [id=" + id + ", price=" + price + ", category=" + category + ", condition=" + condition
				+ ", description=" + description + ", parentCategoryId=" + parentCategoryId + ", childCategoryId="
				+ childCategoryId + ", grandChildCategoryId=" + grandChildCategoryId + ", name=" + name + ", brand="
				+ brand + "]";
	}

}
