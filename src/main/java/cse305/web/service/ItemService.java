package cse305.web.service;

import java.util.List;

import cse305.model.dao.ItemDao;
import cse305.model.entities.Item;

public class ItemService {

	public List<Item> loadAllItems() {
		ItemDao dao = new ItemDao();
		List<Item> items = dao.getAllItems();
		return items;
	}
	
	public Item getItemDetails(int id) {
		ItemDao dao = new ItemDao();
		return dao.getItemById(id);
	}
}
