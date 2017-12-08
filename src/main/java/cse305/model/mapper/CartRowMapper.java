package cse305.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cse305.model.entities.Cart;

public class CartRowMapper implements RowMapper<Cart> {

	@Override
	public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cart cart = new Cart();

		cart.setCustomerId(rs.getInt("customer_id"));
		cart.setItemId(rs.getInt("item_id"));
		cart.setQuantity(rs.getInt("quantity"));

		return cart;
	}

}
