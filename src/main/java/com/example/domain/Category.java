package com.example.domain;

/**
 * カテゴリー情報を表すエンティティ.
 * 
 * @author nonaa
 *
 */
public class Category {
	/** カテゴリーID */
	private Integer id;
	/** 親ID(中カテゴリーの親：大カテゴリー, 小カテゴリーの親：中カテゴリー) */
	private Integer parent;
	/** カテゴリー名 */
	private String name;
	/** 商品名（小カテゴリー） */
	private String nameAll;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameAll() {
		return nameAll;
	}

	public void setNameAll(String nameAll) {
		this.nameAll = nameAll;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", parent=" + parent + ", name=" + name + ", nameAll=" + nameAll + "]";
	}

}
