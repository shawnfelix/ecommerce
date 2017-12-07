package cse305.model.entities;

import java.util.Date;

public class Payment {
	Integer orderId;
	String type;
	String cardNumber;
	Date cardExp;
	
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Date getCardExp() {
		return cardExp;
	}
	public void setCardExp(Date cardExp) {
		this.cardExp = cardExp;
	}
}
