package cse305.model.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import cse305.model.entities.Customer;
import cse305.model.entities.Item;
import cse305.model.mapper.CustomerRowMapper;
import cse305.model.mapper.ItemRowMapper;

public class CustomerDao extends Dao{
	
	SimpleDriverDataSource datasource = getDatasource();
	
	/**
	 * Gets customer details
	 * @author Shawn
	 */
	public Customer getCustomerDetailsById(int customerId){
		JdbcTemplate template = new JdbcTemplate(datasource);
		
		String sql = "SELECT * FROM customer WHERE customer_id =" + customerId;
		
		CustomerRowMapper mapper = new CustomerRowMapper();
		Customer customer = (Customer)template.query(sql, mapper);
		
		return customer;
	}
}
