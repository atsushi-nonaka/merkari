package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Category;
import com.example.domain.Item;
import com.example.form.ItemForm;
import com.example.service.CategoryService;
import com.example.service.ItemService;

/**
 * 商品についてのコントローラー.
 * 
 * @author nonaa
 *
 */
@Controller
@RequestMapping("")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CategoryService categoryService;
	
	/** 1ページに表示する商品数=10 */
	private final static int VIEW_SIZE = 30;
	
	/**
	 * 商品リストページを表示させる.
	 * 
	 * @param model リクエストスコープ
	 * @return 商品リストページ
	 */
	@RequestMapping("show_list")
	public String showList(Model model, Integer page, ItemForm form) {
		//ページング機能追加
		if(page == null) {
			//ページ数の指定がない場合は1ページ目を表示させる
			page = 1;
		}
		List<Item>itemList = itemService.findAll();
		System.out.println(form);
		if(form.getParentCategoryId() != null) {
			if(!(form.getParentCategoryId().equals("0"))) {
				itemList = itemService.findByParentId(Integer.parseInt(form.getParentCategoryId()));
			}
			
			if(!(form.getChildCategoryId().equals("0"))) {
				itemList = itemService.findByChildId(Integer.parseInt(form.getChildCategoryId()));
			}
			
			if(!(form.getGrandChildCategoryId().equals("0"))) {
				itemList = itemService.findByGrandChildId(Integer.parseInt(form.getGrandChildCategoryId()));
			}				
		}
		
		//表示させたいページ数、ページサイズ、商品リストを渡し1ページに表示させる商品リストを絞り込み
		Page<Item> itemPage = itemService.showListPaging(page, VIEW_SIZE, itemList);
		model.addAttribute("itemPage", itemPage);
		model.addAttribute("itemPageMax", itemPage.getTotalPages());
		model.addAttribute("page", page);
		showParentCategory(model);
		return "list";
	}
	
	/**
	 * 商品詳細ページに遷移する.
	 * 
	 * @param id 商品ID
	 * @param model リクエストスコープ
	 * @return 詳細ぺージ
	 */
	@RequestMapping("/detail")
	public String showDetail(Integer id, Model model) {
		Item item = itemService.findByItemId(id);
		model.addAttribute("item", item);
		return "detail";
	}
	
	/**
	 * 商品編集ページの遷移する.
	 * 
	 * @param form 商品フォーム
	 * @param model リクエストスコープ
	 * @return 編集ページ
	 */
	@RequestMapping("/to_edit")
	public String showEdit(Integer id, Model model) {
		model.addAttribute("id", id);
		showParentCategory(model);
		return "edit";
	}
	
	/**
	 * 商品の編集を行う.
	 * 
	 * @param form 商品フォーム
	 * @return 商品リスト
	 */
	@RequestMapping("/edit")
	public String edit(ItemForm form, Model model) {
		itemService.update(form);
		return "redirect:/show_list";
	}
	
	/**
	 * 大カテゴリーリストを表示させる。
	 * 
	 * @param model
	 */
	public void showParentCategory(Model model) {
		List<Category> parentCategoryList = categoryService.findParentCategory();
		model.addAttribute("parentCategoryList", parentCategoryList);
	}
}
