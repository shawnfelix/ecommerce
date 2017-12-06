package cse305.model.dao;

import java.sql.SQLException;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class Dao {
	
	public SimpleDriverDataSource getDatasource() {
		try {
		SimpleDriverDataSource datasource = new SimpleDriverDataSource();
        datasource.setDriver(new com.mysql.jdbc.Driver());
        datasource.setUrl("jdbc:mysql://107.180.43.16:3306/CSE305");
        datasource.setUsername("user305");
        datasource.setPassword("305project");
        
        return datasource;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
