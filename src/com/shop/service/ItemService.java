package com.shop.service;

import java.util.ArrayList;

import com.shop.dao.ItemDAO;
import com.shop.vo.ItemVO;

public class ItemService {
	private static ItemService service = new ItemService();
	public ItemDAO dao = ItemDAO.getInstance();
	
	public static ItemService getInstance() {
		return service;
	}
	
	public ArrayList<ItemVO> ItemList() {
		ArrayList<ItemVO> list = dao.ItemList();
		return list;
	}
	public int getPrice(int itemId) {
	    ItemVO item = dao.getItem(itemId);
	    return item.getPrice();
	}
	public ItemVO getItem(int itemId) {
	    ItemVO item = dao.getItem(itemId);
	    return item;
	}

}
