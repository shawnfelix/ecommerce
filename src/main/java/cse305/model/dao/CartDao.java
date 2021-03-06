package cse305.model.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import cse305.model.entities.CartItem;
import cse305.model.mapper.CartRowMapper;

public class CartDao extends Dao {

	SimpleDriverDataSource datasource = getDatasource();

	/**
	 * Gets all items in the cart
	 * 
	 * @author Shawn
	 */
	public List<CartItem> getCartByCustomerId(int customerId) {
		JdbcTemplate template = new JdbcTemplate(datasource);

		String sql = "SELECT * FROM cart where customer_id = " + customerId;

		CartRowMapper mapper = new CartRowMapper();
		List<CartItem> cart = template.query(sql, mapper);

		return cart;
	}
}
