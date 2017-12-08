package cse305.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cse305.model.entities.CartItem;

public class CartRowMapper implements RowMapper<CartItem> {

	@Override
	public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		CartItem cart = new CartItem();

		//cart.setCustomerId(rs.getInt("customer_id"));
		cart.setItemId(rs.getInt("item_id"));
		cart.setQuantity(rs.getInt("quantity"));
		cart.setCartId(rs.getInt("cart_id"));
		//cart.setOrderId(rs.getInt("order_id"));
		cart.setPrice(rs.getDouble("price"));
		cart.setName(rs.getString("name"));
		cart.setSellerId(rs.getInt("seller_id"));

		return cart;
	}

}
