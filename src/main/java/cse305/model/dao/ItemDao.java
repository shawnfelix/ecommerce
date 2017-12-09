package cse305.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import cse305.model.entities.Inventory;
import cse305.model.entities.Item;
import cse305.model.mapper.ItemRowMapper;

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
	public void addNewItem(Inventory inventory, Item item) {
		if (inventory.getItemId() == item.getItemId()) {
			JdbcTemplate template = new JdbcTemplate(datasource);

			String addToInventory = String.format("INSERT INTO inventory value (%d,%d);", inventory.getItemId(),
					inventory.getQuantity());
			String addItem = String.format("INSERT INTO item value(%d,%s,%.2f,%d,%s);", item.getItemId(),
					item.getName(), item.getPrice(), item.getSellerId(), item.getType());

			template.execute(addToInventory);
			template.execute(addItem);
		}
	}

	/**
	 * Creates a new item in the inventory
	 * 
	 * @author Mark
	 */
	public void deleteItemByID(Item item) {
		JdbcTemplate template = new JdbcTemplate(datasource);

		String deleteFromInventory = String.format("delete from inventory where item_id=%d;", item.getItemId());
		String deleteFromItem = String.format("delete from item where item_id=%d;", item.getItemId());

		template.execute(deleteFromInventory);
		template.execute(deleteFromItem);
	}

	/**
	 * Gets all items that have the given name
	 * 
	 * @author Mark
	 */
	public List<Item> getAllItemsWithName(String name) {
		JdbcTemplate template = new JdbcTemplate(datasource);

		String sql = "SELECT * FROM item where name=" + name;

		ItemRowMapper mapper = new ItemRowMapper();
		List<Item> items = template.query(sql, mapper);

		return items;
	}

}
