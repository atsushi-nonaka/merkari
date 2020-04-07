package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Category;
import com.example.service.CategoryService;

/**
 * カテゴリー選択を表示させるコントローラー.
 * 
 * @author nonaa
 *
 */
@RestController
@RequestMapping(value="")
public class CategoryApiController {
	
	@Autowired
	private CategoryService service;
	
	/**
	 * 中カテゴリーのoptionを表示させる.
	 * 
	 * @param parentId 大カテゴリーID
	 * @return 中カテゴリーリストを格納しているマップ
	 */
	@RequestMapping(value="/select_parent_category", method=RequestMethod.POST)
	public Map<String, List<Category>> displayChildCategory(Integer parentId){
		Map<String, List<Category>> map = new HashMap<>();
		List<Category>childCategoryList = service.findChildCategory(parentId);
		map.put("childCategoryList", childCategoryList);
		return map;
	}
	
	@RequestMapping(value="/select_child_category", method=RequestMethod.POST)
	public Map<String, List<Category>> displayGrandChildCategory(Integer childId){
		Map<String, List<Category>> map = new HashMap<>();
		List<Category>grandChildCategoryList = service.findChildCategory(childId);
		map.put("grandChildCategoryList", grandChildCategoryList);
		return map;
	}
}
