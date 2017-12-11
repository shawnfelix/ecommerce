package cse305.model.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import cse305.model.entities.Customer;
import cse305.model.entities.Item;
import cse305.model.mapper.CustomerRowMapper;
import cse305.model.mapper.ItemRowMapper;
import cse305.web.form.ManageAccountForm;
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
		UserModel customer = (UserModel)template.query(sql, mapper).get(0);

		return customer;
	}
	
	/**
	 * get customer by name
	 * 
	 * @author Shawn
	 */
	public UserModel getCustomerDetailsByUsernamePass(String username, String password) {
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
	
	/**
	 * get customer by userId
	 * 
	 * @author Shawn
	 */
	public UserModel getCustomerDetailsByUserId(int userId) {
		JdbcTemplate template = new JdbcTemplate(datasource);

		String sql = "SELECT * FROM customer WHERE user_id = '" + userId;

		CustomerRowMapper mapper = new CustomerRowMapper();
		List<UserModel> rs = template.query(sql, mapper);

		if(rs.size() > 0) {
			return rs.get(0);
		} else {
			return null;
		}
	}
	
	public void setCustomerDetails(ManageAccountForm form, int userId) {
		JdbcTemplate template = new JdbcTemplate(datasource);
		
		
		String sql = "INSERT INTO customer (customer_id, first_name, last_name, email, phone, mail_address, password)"
				+ " VALUES ('" + userId + "', '" + form.getFirstName() + "', '" 
				+ form.getLastName() + "', '" + form.getEmailAddress() + "', '" + form.getPhoneNumber() + "', '"
				+ form.getAddress() + "', '" + form.getPassword()
				+ "') ON DUPLICATE KEY UPDATE first_name = '" + form.getFirstName() + "', last_name = '"
				+ form.getLastName() + "', email = '" + form.getEmailAddress() + "', phone = '" + form.getPhoneNumber() 
				+ "', mail_address = '" + form.getAddress() + "', password = '" + form.getPassword() + "';";
		template.update(sql);
	}
}
