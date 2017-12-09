package cse305.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

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
}
