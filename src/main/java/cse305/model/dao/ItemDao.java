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

}
