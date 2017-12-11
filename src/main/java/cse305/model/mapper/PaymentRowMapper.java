package cse305.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cse305.model.entities.Payment;

public class PaymentRowMapper implements RowMapper<Payment> {

	@Override
	public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Payment payment = new Payment();

		payment.setPaymentId(rs.getInt("order_id"));
		payment.setType(rs.getString("type"));
		payment.setCardNumber(rs.getString("card_number"));
		payment.setCardExp(rs.getString("card_exp"));

		return payment;
	}

}
