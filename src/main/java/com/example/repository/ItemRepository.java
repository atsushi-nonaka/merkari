package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Category;
import com.example.domain.Item;
import com.example.form.ItemForm;

/**
 * 商品テーブルを操作するレポジトリ.
 * 
 * @author nonaa
 *
 */
@Repository
public class ItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**　商品の最大表示個数 */
	private final static Integer MAX_ITEM_COUNT = 600;
	
	/**
	 * 商品を格納するローマッパー.
	 */
	private final static RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) ->{
		Item item = new Item();
		item.setId(rs.getInt("i_id"));
		item.setName(rs.getString("i_name"));
		item.setCondition(rs.getInt("i_condition"));
		item.setCategoryId(rs.getInt("i_category"));
		item.setBrand(rs.getString("i_brand"));
		item.setPrice(rs.getInt("i_price"));
		item.setShipping(rs.getInt("i_shipping"));
		item.setDescription(rs.getString("i_description"));
		
    	Category category = new Category();
		category.setId(rs.getInt("c_id"));
		category.setName(rs.getString("c_name"));
		category.setNameAll(rs.getString("c_name_all"));
		category.setParent(rs.getInt("c_parent"));
		item.setCategory(category);
		return item;
	};
	
	
	/**
	 * 商品の全件検索を行う.
	 * 
	 * @return 商品の全件リスト
	 */
	public List<Item> findAll(){
		String sql = "SELECT DISTINCT i.id i_id, i.name i_name, i.condition i_condition, i.category i_category, i.brand i_brand, i.price i_price, i.shipping i_shipping, i.description i_description, "
					+ "c.id c_id, c.name c_name, c.name_all c_name_all, c.parent c_parent "
					+ "FROM items i INNER JOIN category c ON i.category = c.id "
					+ "ORDER BY i.name LIMIT 600";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * 商品名から商品の検索をする（あいまい検索）
	 * 
	 * @param itemName 商品名
	 * @return 商品リスト
	 */
	public List<Item> findByItemName(String itemName){
		String sql = "SELECT DISTINCT i.id i_id, i.name i_name, i.condition i_condition, i.category i_category, i.brand i_brand, i.price i_price, i.shipping i_shipping, i.description i_description, "
					+ "c.id c_id, c.name c_name, c.name_all c_name_all, c.parent c_parent "
					+ "FROM items i INNER JOIN category c ON i.category = c.id "
					+ "WHERE i.name ILIKE :itemName "
					+ "ORDER BY i.name LIMIT 600";
		SqlParameterSource param = new MapSqlParameterSource().addValue("itemName", "%" + itemName + "%").addValue("maxItemCount", MAX_ITEM_COUNT);
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * 大カテゴリー選択で商品の検索を行う.
	 * 
	 * @param parentCategoryId 親カテゴリーID
	 * @return 商品リスト
	 */
	public List<Item> findByParentCategory(Integer parentCategoryId){
		String sql = "SELECT DISTINCT i.id i_id, i.name i_name, i.condition i_condition, i.category i_category, i.brand i_brand, i.price i_price, i.shipping i_shipping, i.description i_description, "
				+ "ca.id c_id, ca.parent c_parent, ca.name c_name, ca.name_all c_name_all, "
				+ "cb.id c_id, cb.parent c_parent, cb.name c_name, cb.name_all c_name_all "
				+ "FROM category ca INNER JOIN category cb "
				+ "ON ca.id = cb.parent "
				+ "INNER JOIN items i "
				+ "ON i.category = cb.id "
				+ "WHERE ca.parent = :parentCategoryId "
				+ "ORDER BY i.name LIMIT 600";
		SqlParameterSource param = new MapSqlParameterSource().addValue("parentCategoryId", parentCategoryId).addValue("maxItemCount", MAX_ITEM_COUNT);
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		System.out.println(itemList);
		return itemList;
	}
	
	/**
	 * 中カテゴリー選択で商品の検索を行う.
	 * 
	 * @param parentCategoryId 親カテゴリーID
	 * @return 商品リスト
	 */
	public List<Item> findByChildCategory(Integer childCategoryId){
		String sql = "SELECT DISTINCT i.id i_id, i.name i_name, i.condition i_condition, i.category i_category, i.brand i_brand, i.price i_price, i.shipping i_shipping, i.description i_description, "
					+ "c.id c_id, c.name c_name, c.name_all c_name_all, c.parent c_parent "
					+ "FROM items i INNER JOIN category c ON i.category = c.id "
					+ "WHERE c.parent = :childCategoryId "
					+ "ORDER BY i.name LIMIT 600";
		SqlParameterSource param = new MapSqlParameterSource().addValue("childCategoryId", childCategoryId).addValue("maxItemCount", MAX_ITEM_COUNT);
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * 小カテゴリー選択で商品の検索を行う.
	 * 
	 * @param parentCategoryId 親カテゴリーID
	 * @return 商品リスト
	 */
	public List<Item> findByGrandChildCategory(Integer grandChildCategoryId){
		String sql = "SELECT DISTINCT i.id i_id, i.name i_name, i.condition i_condition, i.category i_category, i.brand i_brand, i.price i_price, i.shipping i_shipping, i.description i_description, "
				+ "c.id c_id, c.name c_name, c.name_all c_name_all, c.parent c_parent "
				+ "FROM items i INNER JOIN category c ON i.category = c.id "
				+ "WHERE c.id = :grandChildCategoryId "
				+ "ORDER BY i.name LIMIT 600";
		SqlParameterSource param = new MapSqlParameterSource().addValue("grandChildCategoryId", grandChildCategoryId).addValue("maxItemCount", MAX_ITEM_COUNT);
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * 銘柄から商品を検索する.
	 * 
	 * @param brand 銘柄
	 * @return 商品リスト
	 */
	public List<Item> findByBrand(String brand){
		String sql = "SELECT DISTINCT i.id i_id, i.name i_name, i.condition i_condition, i.category i_category, i.brand i_brand, i.price i_price, i.shipping i_shipping, i.description i_description, "
					+ "c.id c_id, c.name c_name, c.name_all c_name_all, c.parent c_parent "
					+ "FROM items i INNER JOIN category c ON i.category = c.id "
					+ "WHERE i.brand LIKE :brand "
					+ "ORDER BY i.name LIMIT 600";
		SqlParameterSource param = new MapSqlParameterSource().addValue("brand", "%" + brand + "%");
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * 商品IDから検索する.
	 * 
	 * @param id 商品ID
	 * @return 商品情報
	 */
	public Item findByItemId(Integer id) {
		String sql = "SELECT DISTINCT i.id i_id, i.name i_name, i.condition i_condition, i.category i_category, i.brand i_brand, i.price i_price, i.shipping i_shipping, i.description i_description, "
					+ "c.id c_id, c.name c_name, c.name_all c_name_all, c.parent c_parent "
					+ "FROM items i INNER JOIN category c ON i.category = c.id "
					+ "WHERE i.id = :id ";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		return item;
	}
	
	/**
	 * 商品の更新を行う.
	 * 
	 * @param item 商品情報
	 */
	public void update(Item item) {
		String sql = "UPDATE items  "
					+ "SET name = :name, price = :price, category = :categoryId, brand = :brand, condition = :condition, description = :description "
					+ "WHERE id = :id";
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		template.update(sql, param);
	}
	
}
