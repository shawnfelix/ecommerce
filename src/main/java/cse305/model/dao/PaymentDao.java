package cse305.model.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import cse305.model.entities.Payment;
import cse305.model.mapper.PaymentRowMapper;

public class PaymentDao extends Dao {

	SimpleDriverDataSource datasource = getDatasource();

	/**
	 * Gets all payment methods by ID
	 * 
	 * @author Mark
	 */
	public List<Payment> getPaymentMethods(int customerID) {
		JdbcTemplate template = new JdbcTemplate(datasource);

		// String sql = "SELECT * FROM payment WHERE order_id=" + customerID;
		String sql = "SELECT * FROM payment";

		PaymentRowMapper mapper = new PaymentRowMapper();
		List<Payment> payments = template.query(sql, mapper);

		return payments;
	}
}
