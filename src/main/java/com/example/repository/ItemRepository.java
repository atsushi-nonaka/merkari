package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.domain.Category;
import com.example.domain.Item;

/**
 * 商品テーブルを操作するレポジトリ.
 * 
 * @author nonaa
 *
 */
@Repository
public class ItemRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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
					+ "FROM items i INNER JOIN category c ON i.category = c.id ";
		jdbcTemplate.setFetchSize(35000);
		List<Item> itemList = jdbcTemplate.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}
	
}
