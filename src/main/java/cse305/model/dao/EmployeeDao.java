package cse305.model.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import cse305.model.entities.Customer;
import cse305.model.entities.Employee;
import cse305.model.entities.Item;
import cse305.model.mapper.CustomerRowMapper;
import cse305.model.mapper.EmployeeRowMapper;
import cse305.model.mapper.ItemRowMapper;
import cse305.web.model.UserModel;

public class EmployeeDao extends Dao {

	SimpleDriverDataSource datasource = getDatasource();

	/**
	 * Gets customer details
	 * 
	 * @author Shawn
	 */
	public UserModel getEmployeeDetailsById(int customerId) {
		JdbcTemplate template = new JdbcTemplate(datasource);

		String sql = "SELECT * FROM employee WHERE employee_id =" + customerId;

		EmployeeRowMapper mapper = new EmployeeRowMapper();
		UserModel employee = (UserModel)template.query(sql, mapper);

		return employee;
	}
	
	/**
	 * get customer by name
	 * 
	 * @author Shawn
	 */
	public UserModel getEmployeeDetailsByUsername(String username, String password) {
		JdbcTemplate template = new JdbcTemplate(datasource);

		String sql = "SELECT * FROM employee WHERE email = '" + username 
				+ "' AND password = '" + password + "'";

		EmployeeRowMapper mapper = new EmployeeRowMapper();
		List<UserModel> rs = template.query(sql, mapper);

		if(rs.size() > 0) {
			return rs.get(0);
		} else {
			return null;
		}
	}
}
