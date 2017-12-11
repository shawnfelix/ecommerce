package cse305.web.service;

import java.util.List;

import cse305.model.dao.ItemDao;
import cse305.model.entities.Item;
import cse305.model.entities.Review;
import cse305.web.form.SearchForm;

public class ItemService {

	public List<Item> loadAllItems() {
		ItemDao dao = new ItemDao();
		List<Item> items = dao.getAllItems();
		return items;
	}
	
	public List<Item> browseFilterItems(SearchForm form) {
		ItemDao dao = new ItemDao();
		
		return dao.getFilteredItems(form.getItemName().trim(), form.getMaxPrice().trim());
		
	}
	
	public Item getItemDetails(int id) {
		ItemDao dao = new ItemDao();
		return dao.getItemById(id);
	}
	
	public List<Review> addReview(int productId, String reviewDetails, int customerId) {
		ItemDao dao = new ItemDao();
		dao.addReview(productId, reviewDetails, customerId);
		
		return dao.getReviews(productId, 5);
	}
	
	public List<Review> getReviews(int productId, int limit){
		ItemDao dao = new ItemDao();
		
		return dao.getReviews(productId, limit);
	}
	
	public void addNewItem(Item item, int employeeId) {
		ItemDao dao = new ItemDao();
		
		dao.addNewItem(item, employeeId);
	}
	
	public void removeItem(int itemId) {
		ItemDao dao = new ItemDao();
		
		dao.deleteItemByID(itemId);
	}
}
