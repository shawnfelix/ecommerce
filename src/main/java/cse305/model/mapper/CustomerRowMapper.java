package cse305.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cse305.model.entities.Customer;
import cse305.web.model.UserModel;

public class CustomerRowMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserModel customer = new UserModel();

		customer.setUserId(rs.getInt("customer_id"));
		customer.setEmail(rs.getString("email"));
		customer.setFirstName(rs.getString("first_name"));
		customer.setLastName(rs.getString("last_name"));
		customer.setMailAddress(rs.getString("mail_address"));
		customer.setPassword(rs.getString("password"));
		
		
		return customer;
	}

}
