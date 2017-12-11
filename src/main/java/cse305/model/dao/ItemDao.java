package cse305.model.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import cse305.model.entities.Inventory;
import cse305.model.entities.Item;
import cse305.model.entities.Review;
import cse305.model.mapper.ItemRowMapper;
import cse305.model.mapper.ReviewRowMapper;

public class ItemDao extends Dao {

	SimpleDriverDataSource datasource = getDatasource();

	/**
	 * Gets all items for sale
	 * 
	 * @author Shawn
	 */
	public List<Item> getAllItems() {
		JdbcTemplate template = new JdbcTemplate(datasource);

		String sql = "SELECT * FROM item";

		ItemRowMapper mapper = new ItemRowMapper();
		List<Item> items = template.query(sql, mapper);

		return items;
	}
	
	/**
	 * get items with search filter
	 */
	public List<Item> getFilteredItems(String name, String maxPrice) {
		JdbcTemplate template = new JdbcTemplate(datasource);
		String sql = "SELECT * FROM item ";
		
		
		if(name != "")
			sql +=  "WHERE name LIKE '%" + name + "%' ";
		if(maxPrice != "") {
			if(name != "")
				sql += "AND ";
			else
				sql += "WHERE ";
			sql += "price <= " + maxPrice;
		}

		ItemRowMapper mapper = new ItemRowMapper();
		List<Item> items = template.query(sql, mapper);

		return items;
	}

	/**
	 * gets item by id
	 * 
	 * @author Shawn
	 * 
	 */
	public Item getItemById(int id) {
		JdbcTemplate template = new JdbcTemplate(datasource);

		String sql = "SELECT * FROM item WHERE item.item_id =" + id;

		ItemRowMapper mapper = new ItemRowMapper();
		Item items = template.query(sql, mapper).get(0);

		return items;
	}

	/**
	 * Creates a new item in the inventory
	 * 
	 * @author Mark
	 */
	public void addNewItem(Item item, int employeeId) {
		JdbcTemplate template = new JdbcTemplate(datasource);

		String sql = "INSERT INTO item VALUES (null, '" + item.getName()
			+ "', '" + item.getPrice() + "', '" + employeeId + "', 'any'" + ");";
		
		template.update(sql);
	}

	/**
	 * Creates a new item in the inventory
	 * 
	 * @author Mark
	 */
	public void deleteItemByID(int itemId) {
		JdbcTemplate template = new JdbcTemplate(datasource);

		String sql = "DELETE FROM cart WHERE item_id =" + itemId;
		
		template.update(sql);
		
		sql = "DELETE FROM item WHERE item_id =" + itemId;
		
		template.update(sql);
		
	}

	/**
	 * Gets all items that have the given name
	 * 
	 * @author Mark
	 */
	public List<Item> getAllItemsWithName(String name) {
		JdbcTemplate template = new JdbcTemplate(datasource);

		String sql = String.format("SELECT * FROM item where name=%s;", name);

		ItemRowMapper mapper = new ItemRowMapper();
		List<Item> items = template.query(sql, mapper);

		return items;
	}
	
	
	/**
	 * Gets reviews for a certain item
	 * 
	 */
	public List<Review> getReviews(int itemId, int limit){
		JdbcTemplate template = new JdbcTemplate(datasource);
		
		String sql = "SELECT * FROM review WHERE item_id = " + itemId
				+ " LIMIT " + limit;
		
		ReviewRowMapper mapper = new ReviewRowMapper();
		List<Review> reviews = template.query(sql, mapper);
		
		return reviews;
		
	}
	
	public void addReview(int itemId, String reviewDetails, int customerId) {
		JdbcTemplate template = new JdbcTemplate(datasource);
		
		String sql = "INSERT INTO review values(null, '" + reviewDetails + "', '" + customerId 
				+ "', '" + itemId + "');";
		
		template.update(sql);
	}

}
