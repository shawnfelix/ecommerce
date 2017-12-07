package cse305.model.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import cse305.model.entities.Customer;
import cse305.model.entities.Shipment;
import cse305.model.mapper.CustomerRowMapper;
import cse305.model.mapper.ShipmentRowMapper;

public class ShipmentDao extends Dao {

	SimpleDriverDataSource datasource = getDatasource();

	/**
	 * Gets customer details
	 * 
	 * @author Shawn
	 */
	public Shipment getShipmentDetailsByOrderId(int orderId) {
		JdbcTemplate template = new JdbcTemplate(datasource);

		String sql = "SELECT * FROM shipment WHERE order_id =" + orderId;

		ShipmentRowMapper mapper = new ShipmentRowMapper();
		Shipment shipment = (Shipment) template.query(sql, mapper);

		return shipment;
	}
}
