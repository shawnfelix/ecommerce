package cse305.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cse305.model.entities.Item;

public class ItemRowMapper implements RowMapper<Item>{

	@Override
	public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
		Item item = new Item();
		
		item.setItemId(rs.getInt("item_id"));
		item.setName(rs.getString("name"));
		item.setPrice(rs.getDouble("price"));
		item.setSellerId(rs.getInt("seller_id"));
		item.setType(rs.getString("type"));
		
		return item;
	}

	
}
