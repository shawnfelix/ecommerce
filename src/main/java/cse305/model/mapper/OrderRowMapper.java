package cse305.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cse305.model.entities.Order;
import cse305.model.entities.Payment;
import cse305.model.entities.Shipment;
import cse305.web.model.OrderModel;

public class OrderRowMapper implements RowMapper<OrderModel>{

	@Override
	public OrderModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		Shipment ship = new Shipment();
		ship.setCost(rs.getDouble("cost"));
		ship.setDetails(rs.getString("details"));
		ship.setMailingAddress(rs.getString("mailing_address"));;
		ship.setOrderId(rs.getInt("order_id"));
		
		Payment payment = new Payment();
		payment.setCardExp(rs.getString("card_exp"));
		payment.setCardNumber(rs.getString("card_number"));
		payment.setOrderId(rs.getInt("order_id"));
		
		OrderModel order = new OrderModel();
		
		order.setCustomerId(rs.getInt("customer_id"));
		order.setTotal(rs.getDouble("total"));
		order.setOrderId(rs.getInt("order_id"));
		order.setCartId(rs.getInt("cart_id"));
		order.setIsActive(rs.getInt("is_active"));
		
		order.setPayment(payment);
		order.setShipment(ship);
		
		return order;
	}

}
