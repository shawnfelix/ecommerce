package cse305.web.model;

import java.util.List;

import cse305.model.entities.CartItem;
import cse305.model.entities.Payment;
import cse305.model.entities.Shipment;

public class OrderModel {

	Integer orderId;
	Integer customerId;
	Double total;
	Double subtotal;
	Integer cartId;
	Integer shipmentId;
	
	Shipment shipment;
	Payment payment;
	
	List<CartItem> cartItems;
	

	public double getSubtotal() {
		double subtotal = 0;
		for(CartItem i: cartItems) {
			subtotal += i.getPrice() * i.getQuantity();
		}
		
		this.subtotal = subtotal;
		return subtotal;
	}
	
	public double getFinalTotal() {
		return subtotal + (subtotal * 0.08) + 5.95;
	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(Integer shipmentId) {
		this.shipmentId = shipmentId;
	}

}
