package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ItemService;

/**
 * 商品についてのコントローラー.
 * 
 * @author nonaa
 *
 */
@Controller
public class ItemController {
	
	@Autowired
	private ItemService service;
	
	/** 1ページに表示する商品数=10 */
	private final static int VIEW_SIZE = 30;
	
	/**
	 * 商品リストページを表示させる.
	 * 
	 * @param model リクエストスコープ
	 * @return 商品リストページ
	 */
	@RequestMapping("show_list")
	public String showList(Model model, Integer page) {
		
		//ページング機能追加
		if(page == null) {
			//ページ数の指定がない場合は1ページ目を表示させる
			page = 1;
		}
		
		List<Item>itemList = service.findAll();
		//表示させたいページ数、ページサイズ、商品リストを渡し1ページに表示させる商品リストを絞り込み
		Page<Item> itemPage = service.showListPaging(page, VIEW_SIZE, itemList);
		model.addAttribute("itemPage", itemPage);
		model.addAttribute("itemPageMax", itemPage.getTotalPages());
		
		//ページングのリンクに使うページ数をスコープに格納
//		List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
//		model.addAttribute("pageNumbers", pageNumbers);
		model.addAttribute("page", page);
		System.out.println(page);
		
		return "list";
	}
	
//	/**
//	 * ページングのリンクに使うページ数をスコープに格納
//	 * 
//	 * @param model リクエストスコープ
//	 * @param itemPage ページング情報
//	 * @return ページ番号
//	 */
//	private List<Integer> calcPageNumbers(Model model, Page<Item>itemPage){
//		int totalPages = itemPage.getTotalPages();
//		List<Integer> pageNumbers = null;
//		if(totalPages > 0) {
//			pageNumbers = new ArrayList<>();
//			for(int i = 1; i <= totalPages; i++) {
//				pageNumbers.add(i);
//			}
//		}
//		return pageNumbers;
//	}
}
