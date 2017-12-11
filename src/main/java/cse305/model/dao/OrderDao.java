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
	
	public void checkout(int customerId, double total) {
		JdbcTemplate template = new JdbcTemplate(datasource);

		//set cart to active and save total cost
		String sql = "UPDATE orders SET is_active = 0, total = " + total + " WHERE is_active = 1 AND "
				+ "customer_id = " + customerId;
		template.update(sql);
		
		
	}
	
	public int getActiveOrderCartId(int customerId) {
		JdbcTemplate template = new JdbcTemplate(datasource);

		String sql = "SELECT cart_id FROM orders WHERE is_active = 1 AND "
				+ "customer_id = " + customerId;
		
		Integer cartId = template.queryForObject(sql, Integer.class);
		
		return cartId;
	}
	/**
	 * remove item from cart
	 */
	public OrderModel removeItemFromOrder(int customerId, int cartId, int itemId) {
		JdbcTemplate template = new JdbcTemplate(datasource);

		String sql = "DELETE FROM cart WHERE cart_id = '" + cartId + "' "
		 		+ "AND item_id = '" + itemId + "';";
		 
		template.update(sql);
		 
		return getActiveOrderModel(customerId);
	}
	
	public OrderModel getActiveOrderModel(int customerId) {
		JdbcTemplate template = new JdbcTemplate(datasource);
		
		String sql = "SELECT * FROM orders o" 
				+ " LEFT JOIN payment p ON o.order_id = p.order_id"
				+ " LEFT JOIN shipment s ON o.order_id = s.order_id"
				+ " WHERE customer_id ='" + customerId + "'"
				+ " AND is_active ='1';";
		
	    OrderRowMapper mapper = new OrderRowMapper();
	    OrderModel model = template.queryForObject(sql, mapper);
	    
    	if(model != null) {
	    	//get all cart items
			String itemsql = "SELECT * FROM cart c"
					+ " LEFT JOIN item i ON c.item_id = i.item_id"
					+ " WHERE c.cart_id = '" + model.getCartId() + "';";
			
			CartRowMapper cartRowMapper = new CartRowMapper();
			List<CartItem> cartItems = template.query(itemsql, cartRowMapper);
			model.setCartItems(cartItems);
			
			return model;
    	} else {
    		return null;
    	}
	}
    	
	/**
	 * gets 10 most recent orders
	 * @return
	 */
	public List<OrderModel> getOrders(int customerId, int limit){
		JdbcTemplate template = new JdbcTemplate(datasource);
		
		String sql = "SELECT * FROM orders o"
				+ " LEFT JOIN payment p ON o.order_id = p.order_id"
				+ " LEFT JOIN shipment s ON o.order_id = s.order_id"
				+ " WHERE customer_id = '" + customerId
				+ "' LIMIT " + limit + ";";
		
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

	
	/**
	 * adds order record
	 */
	public OrderModel addOrderRecord(int customerId, int cartId, int isActive) {
		JdbcTemplate template = new JdbcTemplate(datasource);

		String sql = "SELECT * FROM orders o" 
				+ " LEFT JOIN payment p ON o.order_id = p.order_id"
				+ " LEFT JOIN shipment s ON o.order_id = s.order_id"
				+ " WHERE customer_id ='" + customerId + "'"
				+ " AND is_active ='" + isActive + "';";
		
	    OrderRowMapper mapper = new OrderRowMapper();
	    try {
	    	OrderModel model = template.queryForObject(sql, mapper);
	    	
	    	if(model != null) {
		    	//get all cart items
				String itemsql = "SELECT * FROM cart c"
						+ " LEFT JOIN item i ON c.item_id = i.item_id"
						+ " WHERE c.cart_id = '" + model.getCartId() + "';";
				
				CartRowMapper cartRowMapper = new CartRowMapper();
				List<CartItem> cartItems = template.query(itemsql, cartRowMapper);
				model.setCartItems(cartItems);
				
				return model;
		    } else {
		    	throw new Exception();
		    }
	    } catch(Exception e) {
		    	//create new model
				String createsql = "INSERT INTO orders (customer_id, cart_id, is_active)"
						+ " VALUES ('" + customerId + "', '" + cartId + "'," + isActive + ");";
				template.update(createsql);
				
				
				OrderModel model = template.queryForObject(sql, mapper);
				
				return model;
		    }
	}
	
	
	/**
	 * adds item to customer cart
	 * 
	 */
	public int addItemToCustomerCart(int customerId, int itemId, int quantity) {
		JdbcTemplate template = new JdbcTemplate(datasource);
		
		String sql = "SELECT * FROM orders o" 
				+ " LEFT JOIN payment p ON o.order_id = p.order_id"
				+ " LEFT JOIN shipment s ON o.order_id = s.order_id"
				+ " WHERE customer_id ='" + customerId + "'" 
				+ " AND is_active = 1;";
		 OrderRowMapper mapper = new OrderRowMapper();
		 try {
		   	//use old cart
			OrderModel model = template.queryForObject(sql, mapper);
			
			if(model != null) {
				String updateOrInsertSql = "INSERT INTO cart (quantity, cart_id, item_id)"
						+ " VALUES(" + quantity + ", " + model.getCartId() + ", " + itemId + ")"
						+ " ON DUPLICATE KEY UPDATE quantity ="+ quantity;
						
				template.update(updateOrInsertSql);
				return model.getCartId();
			} else {
				throw new Exception();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			//create new cart
			String createsql = "SELECT cart_id FROM cart ORDER BY cart_id DESC LIMIT 1;";
			Integer cartId = template.queryForObject(sql, Integer.class);
			cartId++;
			
			sql = "INSERT INTO cart (cart_id,item_id, quantity)"
					+ " VALUES('" + cartId + "', '" + itemId + "', '" + quantity +"');";
			template.update(sql);
			
			return cartId;
		}
	}
}
