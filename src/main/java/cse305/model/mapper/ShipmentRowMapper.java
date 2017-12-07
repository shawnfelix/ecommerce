package cse305.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cse305.model.entities.Shipment;

public class ShipmentRowMapper implements RowMapper<Shipment>{

	@Override
	public Shipment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Shipment ship = new Shipment();
		
		ship.setCost(rs.getDouble("cost"));
		ship.setDetails(rs.getString("details"));
		ship.setMailingAddress(rs.getString("mailing_address"));
		ship.setOrderId(rs.getInt("order_id"));
		ship.setType(rs.getString("type"));
		
		return ship;
	}
}
