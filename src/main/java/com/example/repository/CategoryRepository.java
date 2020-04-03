package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.example.domain.Category;

public class CategoryRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * カテゴリーを格納するローマッパー.
	 */
	private final static RowMapper<Category> CATEGORY_ROW_MAPPER = (rs, i) ->{
		Category category = new Category();
		category.setId(rs.getInt("id"));
		category.setParent(rs.getInt("parent"));
		category.setName(rs.getString("name"));
		category.setNameAll(rs.getString("name_all"));
		return category;
	};
	
	/**
	 * 大カテゴリーの検索.
	 * 
	 * @return 大カテゴリーリスト
	 */
	public List<Category> findParentCategory(){
		String sql = "SELECT id, parent, name, name_all "
					+ "FROM category "
					+ "WHERE parent IS NULL AND name_all IS NULL";
		List<Category> categoryList = template.query(sql, CATEGORY_ROW_MAPPER);
		return categoryList;
	}
	
	/**
	 * 中カテゴリーの検索.
	 * 
	 * @param parentId 大カテゴリーID
	 * @return 大カテゴリーIDの一致した中カテゴリーリスト
	 */
	public List<Category> findChildCategory(Integer parentId){
		String sql = "SELECT id, parent, name, name_all "
				+ "FROM category "
				+ "WHERE parent = :parentId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("parentId", parentId);
		List<Category> categoryList = template.query(sql, param, CATEGORY_ROW_MAPPER);
		return categoryList;
	}
	
	/**
	 * 小カテゴリーの検索.
	 * 
	 * @param parentId 中カテゴリーID
	 * @return 中カテゴリーの一致した小カテゴリーリスト
	 */
	public List<Category> findGrandChildCategory(Integer childId){
		String sql = "SELECT id, parent, name, name_all "
				+ "FROM category "
				+ "WHERE parent = :childId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("childId", childId);
		List<Category> categoryList = template.query(sql, param, CATEGORY_ROW_MAPPER);
		return categoryList;
	}
	
	
	
}
