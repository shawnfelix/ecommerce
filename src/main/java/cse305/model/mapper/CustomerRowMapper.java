package cse305.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cse305.model.entities.Customer;

public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		
		customer.setCustomerId(rs.getInt("customer_id"));
		customer.setEmail("email");
		customer.setFirstName("first_name");
		customer.setLastName("last_name");
		customer.setMailAddress("mail_address");
		
		return customer;
	}

}
