package cse305.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cse305.web.model.UserModel;

public class EmployeeRowMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserModel employee = new UserModel();

		employee.setUserId(rs.getInt("employee_id"));
		employee.setSupervisorId(rs.getString("supervisor_id"));
		employee.setRole(rs.getString("role"));
		employee.setEmail(rs.getString("email"));
		employee.setFirstName(rs.getString("first_name"));
		employee.setLastName(rs.getString("last_name"));
		employee.setPassword(rs.getString("password"));

		return employee;
	}

}
