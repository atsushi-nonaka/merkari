package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Category;
import com.example.repository.CategoryRepository;

/**
 * カテゴリーテーブルを操作するサービス.
 * 
 * @author nonaa
 *
 */
@Service
@Transactional
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	/**
	 * 大カテゴリーの検索.
	 * 
	 * @return 大カテゴリーリスト
	 */
	public List<Category> findParentCategory(){
		List<Category> parentCategoryList = repository.findParentCategory();
		return parentCategoryList;
	}
	
	/**
	 * 中カテゴリーの検索.
	 * 
	 * @param parentId 大カテゴリーID
	 * @return 大カテゴリーIDの一致した中カテゴリーリスト
	 */
	public List<Category> findChildCategory(Integer parentId){
		List<Category> childCategoryList = repository.findChildCategory(parentId);
		return childCategoryList;
	}
	
	/**
	 * 小カテゴリーの検索.
	 * 
	 * @param childId 中カテゴリー
	 * @return 中カテゴリーIDの一致した小カテゴリーリスト
	 */
	public List<Category> findGrandChildCategory(Integer childId){
		List<Category> grandChildCategoryList = repository.findGrandChildCategory(childId);
		return grandChildCategoryList;
	}
}
