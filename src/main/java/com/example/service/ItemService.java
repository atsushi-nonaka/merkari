package com.example.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.form.ItemForm;
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
	
	/**
	 * 商品名による検索.
	 * 
	 * @param itemName 商品名
	 * @return 商品リスト
	 */
	public List<Item> findByItemName(String itemName){
		List<Item> itemList = repository.findByItemName(itemName);
		return itemList;
	}
	
	/**
	 * 大カテゴリーIDから商品の検索を行う.
	 * 
	 * @param parentCategoryId 親カテゴリーID
	 * @return 商品リスト
	 */
	public List<Item> findByParentId(Integer parentCategoryId){
		List<Item> itemList = repository.findByParentCategory(parentCategoryId);
		return itemList;
	}
	
	/**
	 * 中カテゴリーIDから商品の検索を行う.
	 * 
	 * @param parentCategoryId 親カテゴリーID
	 * @return 商品リスト
	 */
	public List<Item> findByChildId(Integer childCategoryId){
		List<Item> itemList = repository.findByChildCategory(childCategoryId);
		return itemList;
	}
	
	/**
	 * 小カテゴリーIDから商品の検索を行う.
	 * 
	 * @param parentCategoryId 親カテゴリーID
	 * @return 商品リスト
	 */
	public List<Item> findByGrandChildId(Integer grandChildCategoryId){
		List<Item> itemList = repository.findByGrandChildCategory(grandChildCategoryId);
		return itemList;
	}
	
	/**
	 * 銘柄から商品の検索を行う.
	 * 
	 * @param brand 銘柄
	 * @return
	 */
	public List<Item> findByBrand(String brand){
		List<Item> itemList = repository.findByBrand(brand);
		return itemList;
	}
	
	/**
	 * 商品IDから該当する商品を検索する.
	 * 
	 * @param id 商品ID
	 * @return 商品情報
	 */
	public Item findByItemId(Integer id) {
		Item item = repository.findByItemId(id);
		return item;
	}
	
	/**
	 * 商品の更新を行う.
	 * 
	 * @param item 商品情報
	 */
	public void update(ItemForm form) {
		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		item.setId(Integer.parseInt(form.getId()));
		item.setCondition(Integer.parseInt(form.getCondition()));
		item.setCategoryId(Integer.parseInt(form.getCategory()));
		item.setPrice(Integer.parseInt(form.getPrice()));
		System.out.println(item);
		repository.update(item);
	}
	
	/**
	 * 該当ページごとに商品を表示させる.
	 * 
	 * @param page ページ番号
	 * @param size 1ページ当たりの商品数
	 * @param itemList 商品リスト
	 * @return 該当ページごとの商品情報
	 */
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
		Page<Item> itemPage = new PageImpl<Item>(list, PageRequest.of(page, size), itemList.size());			
		return itemPage;
	}
}
