package cse305.model.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import cse305.model.entities.Customer;
import cse305.model.entities.Item;
import cse305.model.mapper.CustomerRowMapper;
import cse305.model.mapper.ItemRowMapper;
import cse305.web.model.UserModel;

public class CustomerDao extends Dao {

	SimpleDriverDataSource datasource = getDatasource();

	/**
	 * Gets customer details
	 * 
	 * @author Shawn
	 */
	public UserModel getCustomerDetailsById(int customerId) {
		JdbcTemplate template = new JdbcTemplate(datasource);

		String sql = "SELECT * FROM customer WHERE customer_id = " + customerId;

		CustomerRowMapper mapper = new CustomerRowMapper();
		UserModel customer = (UserModel)template.query(sql, mapper);

		return customer;
	}
	
	/**
	 * get customer by name
	 * 
	 * @author Shawn
	 */
	public UserModel getCustomerDetailsByUsername(String username, String password) {
		JdbcTemplate template = new JdbcTemplate(datasource);

		String sql = "SELECT * FROM customer WHERE email = '" + username 
				+ "' AND password = '" + password + "'";

		CustomerRowMapper mapper = new CustomerRowMapper();
		List<UserModel> rs = template.query(sql, mapper);

		if(rs.size() > 0) {
			return rs.get(0);
		} else {
			return null;
		}
	}
}
