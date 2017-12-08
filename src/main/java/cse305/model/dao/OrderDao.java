package cse305.model.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import cse305.model.entities.CartItem;
import cse305.model.mapper.CartRowMapper;
import cse305.model.mapper.OrderRowMapper;
import cse305.web.model.OrderModel;

public class OrderDao extends Dao{

	SimpleDriverDataSource datasource = getDatasource();
	
	/**
	 * gets 10 most recent orders
	 * @return
	 */
	public List<OrderModel> getRecentOrders(int customerId){
		JdbcTemplate template = new JdbcTemplate(datasource);
		
		String sql = "SELECT * FROM orders o"
				+ " LEFT JOIN payment p ON o.order_id = p.order_id"
				+ " LEFT JOIN shipment s ON o.order_id = s.order_id"
				+ " WHERE customer_id = '" + customerId
				+ "' LIMIT 10;";
		
		OrderRowMapper mapper = new OrderRowMapper();
		
		List<OrderModel> orders = template.query(sql, mapper);
		//TODO inefficient - too many db calls
		for(OrderModel i: orders) {
			//get all cart items
			String itemsql = "SELECT * FROM cart c"
					+ " LEFT JOIN item i ON c.item_id = i.item_id"
					+ " WHERE c.cart_id = '" + i.getCartId() + "';";
			
			CartRowMapper cartRowMapper = new CartRowMapper();
			List<CartItem> cartItems = template.query(itemsql, cartRowMapper);
			i.setCartItems(cartItems);
			
		}
		
		return orders;
	}
}
