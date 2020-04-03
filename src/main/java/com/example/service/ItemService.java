package com.example.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

/**
 * 商品の情報を操作するサービス.
 * 
 * @author nonaa
 *
 */
@Service
@Transactional
public class ItemService {
	
	@Autowired
	private ItemRepository repository;
	
	/**
	 * 商品の全件検索を行う.
	 * 
	 * @return 商品の全件リスト
	 */
	public List<Item> findAll(){
		List<Item> itemList = repository.findAll();
		return itemList;
	}
	
	public Page<Item> showListPaging(int page, int size, List<Item> itemList){
		//表示させたいページ数を-1しなければうまく動かない
		page--;
		//どのアイテムから表示させるかというカウント値
		int startItemCount = page * size;
		//絞り込んだ後の商品リストが入る変数
		List<Item> list;
		
		if(itemList.size() < startItemCount) {
			list = Collections.emptyList();
		}else {
			//該当ページに表示させる商品一覧を作成
			int toIndex = Math.min(startItemCount + size, itemList.size());
			list = itemList.subList(startItemCount, toIndex);
		}
		
		//上記で作成した該当ページに表示させる商品一覧をページングできる形に変換して返す
		Page<Item> itemPage
			= new PageImpl<Item>(list, PageRequest.of(page, size), itemList.size());
		return itemPage;
	}
}
